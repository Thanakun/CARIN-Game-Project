package server.game.Game.GameData.Parser.Grammars;

import org.springframework.beans.factory.annotation.Autowired;
import server.game.Game.GameData.Model.PositionMap;
import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Model.OrganismStorage;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class SensorExpression implements Expression{
    private String commandType;
    private Direction nearby_direction;

    public SensorExpression(String commandType,Direction direction){
        this.commandType = commandType;
        this.nearby_direction = direction;
    }
    @Override
    public synchronized int eval(Organism actor,Map<String, Integer> binding
            , PositionMap positionMap, OrganismStorage organismStorage) {
        //calculate directin for nearby command
        int nearby_direction_value = 0;
        if(nearby_direction!=null){
            nearby_direction_value = nearby_direction.eval(actor,binding
                    ,  positionMap,  organismStorage);
            nearby_direction_value%=10;  //get direction 1-8
        }

        int[] bound = positionMap.getMapDimension(); //{x,y}
        int[] current = positionMap.getOrganismPosition(actor.getId());
        if(current==null)return 0;
        //find most far side in x and y axis
        Integer[] all_distance= {current[0]-1,current[1]-1,bound[0]-current[0],bound[1]-current[1]};   //distance from current position to border all 4 side;not include current itself
        Arrays.sort(all_distance, Collections.reverseOrder());
        int max_distance = all_distance[0];

        int x_change=1,y_change=1;
        for(int distance =1;distance<=max_distance;distance++){  // distance from current position
            for(int rotate = 1;rotate<=8;rotate++){  //1-8 left,upright... clockwise
                //defind  how many to go that direction and distance in xy dimension
                switch (rotate){
                    case 1: x_change=0;y_change=1*distance;break; //up
                    case 2: x_change=1*distance;y_change=1*distance;break; //upright
                    case 3:  x_change=1*distance;y_change=0;break; //right
                    case 4:  x_change=1*distance;y_change=-1*distance;break; //downright
                    case 5:  x_change=0;y_change=-1*distance;break; //down
                    case 6:  x_change=-1*distance;y_change=-1*distance;break; //downleft
                    case 7:  x_change=-1*distance;y_change=0;break; //left
                    case 8:  x_change=-1*distance;y_change=1*distance;break; //upleft
                }
                //check if that position has Organism
                int[] considering_position = new int[]{current[0]+x_change,current[1]+y_change};
                if(positionMap.hasOrganism(considering_position)){  //input for method is the considering position
                    Organism organism_found = organismStorage.getById(positionMap.getOrganismAt(considering_position));
                    if(commandType.equals("virus")){
                        if(organism_found.getCategory().equals("Virus")){
                            return distance*10+rotate;
                        }

                    }
                    else if(commandType.equals("antibody")){
                        if(organism_found.getCategory().equals("Antibody")){
                            return distance*10+rotate;
                        }
                    }
                    else if(commandType.equals("nearby")){
                        if(nearby_direction_value==rotate){
                            if(organism_found.getCategory().equals("Virus")){
                                return distance*10+1;  //nearby is virus
                            }
                            else{
                                return distance*10+2;  //nearby is antibody
                            }
                        }


                    }
                }

            }
        }

        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(commandType);
        if(nearby_direction!=null){
            nearby_direction.prettyPrint(s);
        }
    }
    /** SensorExpression → virus | antibody | nearby Direction */
}

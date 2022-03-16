package server.game.Game.GameData.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.game.Game.GameConfig;
import java.util.LinkedHashMap;
import java.util.Map;

@Component

public class PositionMap {
    private static PositionMap instance;

    private static LinkedHashMap<String,int[]> allOrganism_Position;   //key is Organism's Id value is its position
    private int max_x;
    private int max_y;

    @Autowired
    private GameConfig gameConfig;

    private PositionMap(){
        allOrganism_Position = new LinkedHashMap<>();

    }

    public static PositionMap getInstance(){
        if(instance==null){
            instance = new PositionMap();
        }
        return instance;
    }

    public void setConfigValue(){
        setMax_x(gameConfig.getM());
        setMax_y(gameConfig.getN());
    }
    public Map<String,int[]> getPositionMap(){
        return allOrganism_Position;
    }
    public void resetPositionMap(){
        allOrganism_Position = new LinkedHashMap<>();
    }
    public int[] getMapDimension(){
        return  new int[]{max_x,max_y};
    }


    public synchronized boolean updateOrganismPosition(String target_Id,int[] position){   //update Organism location
        if(!hasOrganism(position) &&
                (position[0]>=0)&&(position[0]<max_x)
        && (position[1]>=0)&&(position[1]<max_y)){   //if that position is empty
            allOrganism_Position.put(target_Id,position);
            System.out.println(target_Id+" are at :"+position[0]+" "+position[1]);
            return true;
        }
        else{                       //if that position not empty, update position fail
            return false;
        }
    }
    public int[] getOrganismPosition(String target_Id){  //get position of input Organism
        return allOrganism_Position.get(target_Id);
    }

    public boolean hasOrganism(int[] position){    // true if that position has an Organism
            for(int[] pos:allOrganism_Position.values()){
                if((pos[0]==position[0])&&(pos[1]==position[1]))
                {
                    return true;
                }
            }
            return false;
    }

    public String getOrganismAt(int[] position){              //get Organism at the specified position if there exist
        if(hasOrganism(position)) {
            for (String organ_Id : allOrganism_Position.keySet()) {
                int[] pos = allOrganism_Position.get(organ_Id);
                if ((pos[0] == position[0]) && (pos[1] == position[1])) {
                    return organ_Id;
                }
            }
        }
        return "not found";
    }

    public void removeOrganismPosition(Organism target){
        allOrganism_Position.remove(target.getId());
    }

    public void setMax_x(int max_x) {
        this.max_x = max_x;
    }

    public void setMax_y(int max_y) {
        this.max_y = max_y;
    }
}

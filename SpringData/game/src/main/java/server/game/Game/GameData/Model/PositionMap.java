package server.game.Game.GameData.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@PropertySource("classpath:GameDataProperties.properties")
public class PositionMap {
    private static PositionMap instance;
    private static LinkedHashMap<String,int[]> allOrganism_Position;   //key is Organism value is its position
    //variable in positionMap
    @Value("${max_x}")
    private int max_x;
    @Value("${max_y}")
    private int max_y;

    private PositionMap(){
        allOrganism_Position = new LinkedHashMap<>();
    }

    public static PositionMap getInstance(){
        if(instance==null){
            instance = new PositionMap();
        }
        return instance;
    }

    public int[] getMapDimension(){
        return  new int[]{max_x,max_y};
    }

    public synchronized void resetMap(){
        instance = new PositionMap();
    }

    public synchronized boolean updateOrganismPosition(String target_Id,int[] position){   //update Organism location
        if(!hasOrganism(position)){   //if that posiotion is empty
            allOrganism_Position.put(target_Id,position);
            System.out.println(target_Id+" are at :"+position[0]+" "+position[1]);
            return true;
        }
        else{                       //if that position not empty, update postition fail
            return false;
        }
    }
    public int[] getOrganismPosition(String target_Id){  //get position of input Organism
        return allOrganism_Position.get(target_Id);
    }

    public boolean hasOrganism(int[] position){    // true if that position has an Organism
        if(allOrganism_Position.containsValue(position)){
            return true;
        }
        else return false;
    }

    public String getOrganismAt(int[] position){              //get Organism at the specified position if there exist
        for(String organ_Id:allOrganism_Position.keySet()){
            if(allOrganism_Position.get(organ_Id).equals(position)){
                return organ_Id;
            }
        }
        return "not found";
    }

    
}
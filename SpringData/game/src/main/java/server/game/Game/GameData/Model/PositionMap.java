package server.game.Game.GameData.Model;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class PositionMap {
    private static PositionMap instance;
    private LinkedHashMap<String,int[]> allOrganism_Position;   //key is Organism value is its position
    private int max_x;
    private int max_y;

    private PositionMap(){
        allOrganism_Position = new LinkedHashMap<>();
        max_x = 100;
        max_y = 100;
    }

    public static PositionMap getInstance(){
        if(instance==null){
            instance = new PositionMap();
        }
        return instance;
    }
    public  int[] getMapDimension(){
        return new int[]{max_x,max_y};    // max width and max height of map
    }
    public synchronized void resetMap(){
        instance = new PositionMap();
    }

    public synchronized boolean updateOrganismPosition(String target_Id,int[] position){   //update Organism location
        if(!hasOrganism(position)){   //if that posiotion is empty
            allOrganism_Position.put(target_Id,position);
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
        return null;
    }

    
}

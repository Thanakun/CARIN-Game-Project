package Model;

import java.util.LinkedHashMap;

public class PositionMap {
    private static PositionMap instance;
    private LinkedHashMap<Organism,int[]> allEntity_Position;   //key is Organism value is its position
    private int max_x;
    private int max_y;

    private PositionMap(){
        allEntity_Position = new LinkedHashMap<>();
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
    public  void resetMap(){
        instance = new PositionMap();
    }

    public void updateOrganismPosition(Organism target,int[] position){   //update Organism location
        if(!hasOrganism(position)){   //if that posiotion is empty
            allEntity_Position.put(target,position);
        }

    }
    public int[] getOrganismPosition(Organism target){  //get position of input Organism
        return allEntity_Position.get(target);
    }

    public boolean hasOrganism(int[] position){    // true if that position has an Organism
        if(allEntity_Position.containsValue(position)){
            return true;
        }
        else return false;
    }

    public Organism getOrganismAt(int[] position){              //get Organism at the specified position if there exist
        for(Organism organ:allEntity_Position.keySet()){
            if(allEntity_Position.get(organ).equals(position)){
                return organ;
            }
        }
        return null;
    }

    
}

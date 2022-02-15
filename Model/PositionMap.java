package Model;

import java.util.LinkedHashMap;

public class PositionMap {
    private static PositionMap instance;
    private LinkedHashMap<Organism,int[]> allEntity_Position;   //key is Organism value is its position

    private PositionMap(){
        allEntity_Position = new LinkedHashMap<>();
    }

    public static PositionMap getInstance(){
        if(instance==null){
            instance = new PositionMap();
        }
        return instance;
    }

    public static void resetMap(){
        instance = new PositionMap();
    }

    public void updateMap(Organism target){
        int[] position = target.getPosition();
        if(!hasEntity(position)){   //if that posiotion is empty
            allEntity_Position.put(target,position);
        }

    }

    public boolean hasEntity(int[] position){
        if(allEntity_Position.containsValue(position)){
            return true;
        }
        else return false;
    }

    public Organism getEntity(int[] position){
        for(Organism organ:allEntity_Position.keySet()){
            if(allEntity_Position.get(organ).equals(position)){
                return organ;
            }
        }
        return null;
    }

    
}

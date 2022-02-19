package server.game.Game.GameData.Model;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class OrganismStorage {
    private static OrganismStorage instance;
    private static LinkedHashMap<String,Organism> allVirus;
    private static LinkedHashMap<String,Organism> allAntivirus;
    private static int antibody_Id_count;
    private static int virus_Id_count;

    private OrganismStorage(){
        allAntivirus = new LinkedHashMap<>();
        allVirus = new LinkedHashMap<>();
        antibody_Id_count = 0;
        virus_Id_count = 0;
    }
    public static OrganismStorage getInstance(){
        if(instance==null){
            instance = new OrganismStorage();
        }
    return instance;
    }

    public synchronized void addOrganism(Organism target){   //add target Organism to allVirus or allAntivirus by type, and count each type
        if(target.getCategory().equals("Virus")){
            if(!allVirus.containsValue(target)){  //if target not already in container
                virus_Id_count++;
                allVirus.put(target.getId(),target);
            }

        }
        else{
            if(!allAntivirus.containsValue(target)){
                antibody_Id_count++;
                allAntivirus.put(target.getId(),target);
            }

        }
    }

    public synchronized void removeOrganism(Organism target){   //add target Organism to allVirus or allAntivirus by type, and count each type
        if(target.getCategory().equals("Virus") && allVirus.containsValue(target)){
            allVirus.remove(target.getId());
        } else if (target.getCategory().equals("Antibody") && allVirus.containsValue(target)){
            allAntivirus.remove(target.getId());
        }
    }

    public Organism getById(String Id){  //get Organism by input Id
        char type = Id.charAt(0);
        if(type=='V'){
            return allVirus.get(Id);
        }
        else if(type=='A'){
            return allAntivirus.get(Id);
        }
        return null;
    }

    public int getAntibody_count(){
        return antibody_Id_count;
    }
    public int getVirus_count(){
        return virus_Id_count;
    }

    public LinkedHashMap<String, Organism> getallAntibody() {
        return allAntivirus;
    }

    public LinkedHashMap<String, Organism> getallVirus() {
        return allVirus;
    }

}

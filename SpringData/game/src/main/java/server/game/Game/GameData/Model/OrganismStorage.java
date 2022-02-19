package server.game.Game.GameData.Model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
@PropertySource("classpath:GameDataProperties.properties")
public class OrganismStorage {
    private static OrganismStorage instance;
    private static LinkedHashMap<String,Organism> allVirus;
    private static LinkedHashMap<String,Organism> allAntivirus;
    private static int antibody_Id_count;
    private static int antibody_killed;
    private static int virus_Id_count;
    @Value("${max_virus_amount}")
    private   int max_virus_amount;
    private static int virus_killed;

    private OrganismStorage(){
        allAntivirus = new LinkedHashMap<>();
        allVirus = new LinkedHashMap<>();
        antibody_Id_count = 0;
        antibody_killed =0;
        virus_Id_count = 0;

        virus_killed = 0;
    }
    public static OrganismStorage getInstance(){
        if(instance==null){
            instance = new OrganismStorage();
        }
    return instance;
    }

    public void resetStorage(){
        allAntivirus = new LinkedHashMap<>();
        allVirus = new LinkedHashMap<>();
        antibody_Id_count = 0;
        virus_Id_count = 0;
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
            PositionMap.getInstance().removeOrganismPosition(target);
            virus_killed++;

        } else if (target.getCategory().equals("Antibody") && allAntivirus.containsValue(target)){
            allAntivirus.remove(target.getId());
            PositionMap.getInstance().removeOrganismPosition(target);
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

    public  synchronized LinkedHashMap<String, Organism> getallAntibody() {
        return allAntivirus;
    }

    public synchronized LinkedHashMap<String, Organism> getallVirus() {
        return allVirus;
    }

    public int getAntibodyAmount(){return allAntivirus.size();}
    public int getVirusAmount(){return allVirus.size();}

    public  int getMax_virus_amount() {
        return max_virus_amount;
    }

    public  int getVirus_killed() {
        return virus_killed;
    }
    public int getAntibody_killed(){
        return antibody_killed;
    }
}

package server.game.Game.GameData.Model;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import server.game.Game.GameConfig;

import java.util.LinkedHashMap;

@Component

public class OrganismStorage {
    private static OrganismStorage instance;
    private static LinkedHashMap<String,Organism> allVirus;
    private static LinkedHashMap<String,Organism> allAntivirus;
    private static int antibody_Id_count;
    private static int antibody_killed;
    private static int virus_Id_count;

    private   int max_virus_amount;
    private static int virus_killed;
    @Autowired
    private PositionMap positionMap;
    @Autowired
    private GameConfig gameConfig;


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

    public void setConfigValue(){
        setMax_virus_amount(gameConfig.getMax_virus_amount());
    }

    public void setMax_virus_amount(int max_virus_amount) {
        this.max_virus_amount = max_virus_amount;
    }

    public void resetStorage(){
        allAntivirus = new LinkedHashMap<>();
        allVirus = new LinkedHashMap<>();
        antibody_Id_count = 0;
        virus_Id_count = 0;
        antibody_killed =0;
        virus_killed = 0;
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
            positionMap.removeOrganismPosition(target);
            virus_killed++; //virus kill player gain credit

        } else if (target.getCategory().equals("Antibody") && allAntivirus.containsValue(target)){
            allAntivirus.remove(target.getId());
            positionMap.removeOrganismPosition(target);
            antibody_killed++;
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

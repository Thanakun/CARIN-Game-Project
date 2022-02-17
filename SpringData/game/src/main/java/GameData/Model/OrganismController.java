package GameData.Model;

import java.util.LinkedHashMap;

public class OrganismController {
    private static OrganismController instance;
    private static LinkedHashMap<String,Organism> allVirus;
    private static LinkedHashMap<String,Organism> allAntivirus;
    private static int antibody_count;
    private static int virus_count;
    private static int currentID_Virus = 1;
    private static int currentID_Antibody = 1;

    private OrganismController(){
        allAntivirus = new LinkedHashMap<>();
        allVirus = new LinkedHashMap<>();
        antibody_count = 0;
        virus_count = 0;
    }
    public static OrganismController getInstance(){
        if(instance==null){
            instance = new OrganismController();
        }
    return instance;
    }

    public void addOrganism(Organism target){   //add target Organism to allVirus or allAntivirus by type, and count each type
        if(target.getCategory().equals("Virus")){
            virus_count++;
            allVirus.put(target.getId(),target);
            currentID_Virus++;
        }
        else{
            antibody_count++;
            allAntivirus.put(target.getId(),target);
            currentID_Antibody++;
        }
    }

    public void removeOrganism(Organism target){   //add target Organism to allVirus or allAntivirus by type, and count each type
        if(target.getCategory().equals("Virus") && allVirus.containsValue(target)){
            allVirus.remove(target.getId());
            virus_count--;
        } else if (target.getCategory().equals("Antibody") && allVirus.containsValue(target)){
            allAntivirus.remove(target.getId());
            antibody_count--;
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
        return antibody_count;
    }
    public int getVirus_count(){
        return virus_count;
    }

    public LinkedHashMap<String, Organism> getallAntivirus() {
        return allAntivirus;
    }

    public LinkedHashMap<String, Organism> getallVirus() {
        return allVirus;
    }

    public int getCurrentID_Virus() {
        return currentID_Virus;
    }

    public int getCurrentID_Antibody() {
        return currentID_Antibody;
    }
}

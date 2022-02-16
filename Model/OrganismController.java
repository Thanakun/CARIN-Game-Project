package Model;

import java.util.LinkedHashMap;

public class OrganismController {
    private static OrganismController instance;
    private static LinkedHashMap<String,Organism> allVirus;
    private static LinkedHashMap<String,Organism> allAntivirus;
    private static int antibody_count;
    private static int virus_count;


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
        }
        else{
            antibody_count++;
            allAntivirus.put(target.getId(),target);
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


}

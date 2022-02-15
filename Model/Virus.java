package Model;


public class Virus extends Entity implements Organism{
    /** Attack Antigen From Random */

    public Virus(String id){
        this.Id = id;
        this.type = "Virus";
    }
}

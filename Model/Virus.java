package Model;


public class Virus extends Entity implements Organism{
    /** Attack Antigen From Random */

    public Virus(String id){
        this.Id = id;
        this.category = new String("Virus");
        this.type = "second"; // types (3) : first , second , third
        this.HP = 100;
        this.position[0] = 0;
        this.position[1] = 0;
        OrganiMap.addAntigen(this);
    }
}

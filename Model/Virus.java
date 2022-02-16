package Model;


import java.util.Random;

public class Virus extends Entity implements Organism{
    /** Attack Antigen From Random */

    public Virus(String id){   // for virus Id format is "V??????" ?is any number or character
        this.Id = id;
        this.category = new String("Virus");
        this.type = "second"; // types (3) : first , second , third
        this.HP = 100;
        OrganiControl.addOrganism(this);
        firstSpawnLocationInit();
    }
}

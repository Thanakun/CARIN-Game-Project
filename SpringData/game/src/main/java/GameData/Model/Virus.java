package GameData.Model;


import java.util.Random;

public class Virus extends Entity implements Organism{
    /** Attack Antigen From Random */

    public Virus(String id, String type){   // for virus Id format is "V??????" ?is any number or character
        this.Id = id;
        this.category = new String("Virus");
        this.type = type; // types (3) : first , second , third
        this.HP = 100;
        OrganiControl.addOrganism(this);
        firstSpawnLocationInit();
    }

    public void afterAttacked(int damage) {
        HP += damage*0.15;
    }

    public void overcome() {
        OrganiControl.addOrganism(new Virus("V" + Integer.toString(OrganiControl.getCurrentID_Virus()), this.type));
    }
}

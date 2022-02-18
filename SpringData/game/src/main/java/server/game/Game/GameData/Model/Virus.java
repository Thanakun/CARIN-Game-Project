package server.game.Game.GameData.Model;


public class Virus extends Entity implements Organism{
    /** Attack Antigen From Random */

    public Virus(String id, int type){   // for virus Id format is "V??????" ?is any number or character
        this.Id = id;
        this.category = new String("Virus");
        this.type = type; // types (3) : 1 , 2 , 3
        this.HP = 100;
        OrganiControl.addOrganism(this);
        firstSpawnLocationInit();
    }

    public void afterAttacked(int damage) {
        HP += damage*0.15;
    }

    public void overcome() {
        OrganiControl.addOrganism(new Virus("V" + Integer.toString(OrganiControl.getVirus_count()), this.type));
    }
}

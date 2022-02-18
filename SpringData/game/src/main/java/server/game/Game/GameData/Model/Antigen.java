package server.game.Game.GameData.Model;


public class Antigen extends Entity implements Organism {

    public Antigen(String id,int type){ // for antivirus Id format is "A??????" ?is any number or character
        this.Id = id;
        this.category = new String("Antibody");
        this.type = type; // types (3) : first , second , third
        this.HP = 100;
        OrganiControl.addOrganism(this);
    }

    public void overcome() {
        HP += HP*0.35;
    }

}

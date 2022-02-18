package server.game.Game.GameData.Model;



public class Antibody extends Entity implements Organism {

    public Antibody(String id, int type,int[] location_init){ // for antivirus Id format is "A??????" ?is any number or character
        this.Id = id;
        this.category = new String("Antibody");
        this.type = type; // types (3) : first , second , third
        this.HP = 100;
        positionMap.updateOrganismPosition(this.Id,this.position);
        organismStorage.addOrganism(this);

    }

    public void overcome() {
        HP += HP*0.35;
    }


}

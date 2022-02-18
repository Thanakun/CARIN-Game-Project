package server.game.Game.GameData.Model;




public class Antibody extends Entity implements Organism {


    public Antibody(String id, int type,int[] location_init,PositionMap positionMap,OrganismStorage organismStorage){ // for antivirus Id format is "A??????" ?is any number or character
        this.Id = id;
        this.category = new String("Antibody");
        this.type = type; // types (3) : first , second , third
        this.positionMap = positionMap;
        this.organismStorage = organismStorage;

        positionMap.updateOrganismPosition(this.Id,location_init);  //update on map before change in class
        this.position = location_init;

        organismStorage.addOrganism(this);

    }

    public void overcome() {
        HP += HP*0.35;
    }


}

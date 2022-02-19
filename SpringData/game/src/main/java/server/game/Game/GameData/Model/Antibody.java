package server.game.Game.GameData.Model;




public class Antibody extends Entity implements Organism {
    private int move_cost;
    private int buy_cost;

    public Antibody(String id, int type,int[] location_init,PositionMap positionMap
            ,OrganismStorage organismStorage){ // for antivirus Id format is "A??????" ?is any number or character
        this.Id = id;
        this.category = new String("Antibody");
        this.type = type; // types (3) : first , second , third
        this.positionMap = positionMap;
        this.organismStorage = organismStorage;
        if(!positionMap.hasOrganism(location_init)) {
            positionMap.updateOrganismPosition(this.Id, location_init);  //update on map before change in class
            this.position = location_init;
            organismStorage.addOrganism(this);
        }

    }

    public void overcome() {
        HP += HP*0.35;
    }

    public void setCost(int move_cost,int buy_cost){
        this.move_cost = type*move_cost;
        this.buy_cost = type*buy_cost;
    }

    public int getBuyCost(){
        return this.buy_cost;
    }

}

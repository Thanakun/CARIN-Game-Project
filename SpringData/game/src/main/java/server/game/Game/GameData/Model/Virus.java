package server.game.Game.GameData.Model;


public class Virus extends Entity implements Organism{

    public Virus(String id, int type,int[] location_init,PositionMap positionMap,
                 OrganismStorage organismStorage,VirusControl virusControl){   // for virus Id format is "V??????" ?is any number
        this.Id = id;
        this.category = "Virus";
        this.type = type; // types (3) : 1 , 2 , 3
        this.positionMap = positionMap;
        this.organismStorage = organismStorage;
        this.virusControl = virusControl;

        if(!positionMap.hasOrganism(location_init)) {
            positionMap.updateOrganismPosition(this.Id, location_init);  //update on map before change on class
            this.position = location_init;

            organismStorage.addOrganism(this);
        }

    }

    public void afterAttacked() {
        HP +=gain;
        if(HP>Max_HP)HP=Max_HP;
    }

    public void dieEffect(){
        virusControl.dieAddCredit(this.type);
        organismStorage.increaseVirusKilled();
    }



}

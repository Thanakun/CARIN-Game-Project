package server.game.Game.GameData.Model;




import java.util.Random;


public class Virus extends Entity implements Organism{
    /** Attack Antigen From Random */
    private Random rand= new Random();

    public Virus(String id, int type,int[] location_init,PositionMap positionMap,
                 OrganismStorage organismStorage,VirusControl virusControl){   // for virus Id format is "V??????" ?is any number or character
        this.Id = id;
        this.category = new String("Virus");
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

    public void afterAttacked(int damage) {
        HP += damage*0.15;
    }

    public void dieEffect(){
        virusControl.dieAddCredit(this.type);
    }

    public void overcome() {
      //  organismStorage.addOrganism(new Virus("V" + Integer.toString(organismStorage.getVirus_count()), this.type,this.position));
    }

}

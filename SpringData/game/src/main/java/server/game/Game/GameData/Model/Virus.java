package server.game.Game.GameData.Model;



import java.util.Random;


public class Virus extends Entity implements Organism{
    /** Attack Antigen From Random */
    private Random rand;

    public Virus(String id, int type){   // for virus Id format is "V??????" ?is any number or character
        this.Id = id;
        this.category = new String("Virus");
        this.type = type; // types (3) : 1 , 2 , 3
        this.HP = 100;
        organismStorage.addOrganism(this);
        firstSpawnLocationInit();
        rand = new Random();
    }

    public void afterAttacked(int damage) {
        HP += damage*0.15;
    }

    public void overcome() {
        organismStorage.addOrganism(new Virus("V" + Integer.toString(organismStorage.getVirus_count()), this.type));
    }

    public void firstSpawnLocationInit(){    // to spawn first time at virus constructor

        int[] maxbound= positionMap.getMapDimension();
        int x_posi = rand.nextInt(maxbound[0]+1);
        int y_posi = rand.nextInt(maxbound[1]+1);
        this.position[0] = x_posi;
        this.position[1] = y_posi;
        while(!positionMap.updateOrganismPosition(this.Id,this.position)){  //loop until successfuly add this in map; prevent spawn at not empty position
            x_posi = rand.nextInt(maxbound[0]+1);
            y_posi = rand.nextInt(maxbound[1]+1);
            this.position[0] = x_posi;
            this.position[1] = y_posi;
        }
    }
}

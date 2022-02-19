package server.game.Game.GameData.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import server.game.Game.GameData.Controller.UserControl;
import server.game.Game.Type.AntibodyReq;
import server.game.Game.Type.Request;

@Component
@PropertySource("classpath:GameDataProperties.properties")
public class Player {
    private static Player instance;
    @Value("${init_credits}")
    private int credit;
    @Autowired
    private AntibodyControl antibodyControl;
    @Autowired
    private OrganismStorage organismStorage;
    @Autowired
    private PositionMap positionMap;

    private Player(){}
    public static Player getInstance(){
        if(instance==null){
            instance = new Player();
        }
        return instance;
    }

    public synchronized void computeAntibodyInput(Request req){
       AntibodyReq antibodyReq=(AntibodyReq)req;

        if(antibodyReq.getGenetic().equals("")){ // new create use default genetic
            placeNewAntibody(antibodyReq.getType(),antibodyReq.getLocation());
        }
        else{ //update antibody that already exist
            antibodyControl.updateAntibodyGenetic(antibodyReq.getTargetId(),antibodyReq.getGenetic());
        }
    }

    public synchronized void placeNewAntibody(int type,int[] location) {
            Antibody newAntibody = antibodyControl.spawnNewAntibody(type, location);
            if (newAntibody != null) {  //true if can spawn new antibody at that location
                if (credit - newAntibody.getBuyCost() < 0) { //not enough credit
                    System.out.println("not enough credit");
                    organismStorage.removeOrganism(newAntibody);
                    positionMap.removeOrganismPosition(newAntibody);
                } else { //can place successfully
                    credit -= newAntibody.getBuyCost();
                    System.out.println("Remaining credit:"+credit);
                    System.out.println("buy antibody complete");
                }

            } else {
                System.out.println("can not place new antibody");
            }
        }


}

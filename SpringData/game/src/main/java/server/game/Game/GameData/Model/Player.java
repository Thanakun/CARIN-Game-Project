package server.game.Game.GameData.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import server.game.Game.GameConfig;
import server.game.Game.GameData.Controller.UserControl;
import server.game.Game.Type.AntibodyReq;
import server.game.Game.Type.Request;

@Component

public class Player {
    private static Player instance;

    private int init_credit;

    private int kill_virus_credit_gain;
    @Autowired
    private AntibodyControl antibodyControl;
    @Autowired
    private OrganismStorage organismStorage;
    @Autowired
    private PositionMap positionMap;
    @Autowired
    private GameConfig gameConfig;

    private int current_credit=0;

    private Player(){

    }
    public static Player getInstance(){
        if(instance==null){
            instance = new Player();
        }
        return instance;
    }
    public void setConfigValue(){
        setInit_credit(gameConfig.getInit_credits());
        setKill_virus_credit_gain(gameConfig.getKilled_virus_credit_gain());
        current_credit = init_credit;
    }

    public void setCurrentCredit(int amount){
        current_credit = amount;
    }
    public int getInitCredit(){
        return init_credit;
    }
    public int getCurrentCredit(){
        return current_credit;
    }
    public  void resetCredit(){
        current_credit=init_credit;
    }
    public void addCredit(int virus_type){
        current_credit+=virus_type*kill_virus_credit_gain;

    }

    public synchronized void computeAntibodyInput(Request req){
       AntibodyReq antibodyReq=(AntibodyReq)req;
        System.out.println("Antibody request------------");

        if(antibodyReq.getCmd().equals("buy")){
            placeNewAntibody(antibodyReq.getType(),antibodyReq.getLocation());
        }
        else if(antibodyReq.getCmd().equals("move")){
            antibodyControl.moveAntibody(antibodyReq.getTargetId(),antibodyReq.getLocation());
        }
        else if(antibodyReq.getCmd().equals("setup")){
            System.out.println("genetic form post"+antibodyReq.getGenetic());
             antibodyControl.setGeneticCode(antibodyReq.getGenetic());
            antibodyControl.setSetGeneticState(true);
        }
        else{
            System.out.println("antibody request error");
        }

    }

    public synchronized void placeNewAntibody(int type,int[] location) {
            Antibody newAntibody = antibodyControl.spawnNewAntibody(type, location);
            if (newAntibody != null) {  //true if can spawn new antibody at that location
                if (current_credit - newAntibody.getBuyCost() < 0) { //not enough credit
                    System.out.println("not enough credit");
                    organismStorage.removeOrganism(newAntibody);
                    positionMap.removeOrganismPosition(newAntibody);
                } else { //can place successfully
                    current_credit -= newAntibody.getBuyCost();
                    System.out.println("Remaining credit:"+current_credit);
                    System.out.println("buy antibody complete");
                }

            } else {
                System.out.println("can not place new antibody");
            }
        }

    public void setInit_credit(int init_credit) {
        this.init_credit = init_credit;
    }

    public void setKill_virus_credit_gain(int kill_virus_credit_gain) {
        this.kill_virus_credit_gain = kill_virus_credit_gain;
    }
}

package server.game.Game.GameData.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import server.game.Game.GameConfig;
import server.game.Game.GameData.Parser.Parser;
import server.game.Game.Type.AntibodyReq;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;


@Component

public class AntibodyControl {

    public static AntibodyControl instance;
    @Autowired
    private OrganismStorage organismStorage;
    @Autowired
    private PositionMap positionMap;
    @Autowired
    private GameConfig gameConfig;

    private String default_geneticCode;

    //initial value for set up virus status

    private int init_hp;

    private int init_atk;

    private int init_gain;

    private int init_move_cost;

    private int init_antibody_cost;






    private AntibodyControl(){
        default_geneticCode =
                "virusLoc = virus\n" +
                        "if (virusLoc / 10 - 1)\n" +
                        "then \n" +
                        "  { }"
                        + "else\n"+
                       " if (virusLoc)\n" +
                        "then \n" +
                        "  if (virusLoc % 10 - 7) then shoot upleft\n" +
                        "  else if (virusLoc % 10 - 6) then shoot left\n" +
                        "  else if (virusLoc % 10 - 5) then shoot downleft\n" +
                        "  else if (virusLoc % 10 - 4) then shoot down\n" +
                        "  else if (virusLoc % 10 - 3) then shoot downright\n" +
                        "  else if (virusLoc % 10 - 2) then shoot right\n" +
                        "  else if (virusLoc % 10 - 1) then shoot upright\n" +
                        "  else shoot up\n";

    }
    public static AntibodyControl getInstance(){
        if(instance==null){
            instance = new AntibodyControl();
        }
        return instance;
    }
    public void setConfigValue(){
        setInit_antibody_cost(gameConfig.getAntibody_cost());
        setInit_atk(gameConfig.getAntibody_atk());
        setInit_gain(gameConfig.getAntibody_gain());
        setInit_hp(gameConfig.getAntibody_hp());
        setInit_move_cost(gameConfig.getAntibody_move_cost());
    }

    public Antibody spawnNewAntibody(int type, int[] location){  //return true if can place at that location
    int[] maxBound = positionMap.getMapDimension();
        if(!positionMap.hasOrganism(location) &&
                (location[0]>=0 && location[0]<maxBound[0] && location[1]>=0 && location[1]<maxBound[1])){  //not found any organism at location and wanted place is within map range
            System.out.println("create new Antibody");
            Antibody newAntibody = new Antibody(
                    "A"+ organismStorage.getAntibody_count()
                    ,type,location
            ,positionMap,organismStorage);
            newAntibody.setGeneticCode(this.default_geneticCode);
            newAntibody.setStatus(init_hp,init_atk,init_gain);    //set up status and genetic code
            newAntibody.setCost(init_move_cost,init_antibody_cost);
            organismStorage.addOrganism(newAntibody);
            return newAntibody;
        }
        else{
            return null;
        }
    }

    public void updateAntibodyGenetic(String target_Id,String genetic){
        Antibody target = (Antibody) organismStorage.getallAntibody().get(target_Id);
        if(target!=null){
            target.setGeneticCode(genetic);
        }
       else {
            System.out.println("can't update Antibody that not exist");
        }
    }

    public synchronized void activeAllAntibody(){
        LinkedHashMap<String,Organism> allAntibody =  organismStorage.getallAntibody();
        List<String> allKey = new LinkedList<>(allAntibody.keySet());
        for(String id:allKey){
            Antibody antibody = (Antibody) allAntibody.get(id);
            System.out.println("Antibody id:"+id+" is active at:"+antibody.getPosition()[0]
            +"  "+antibody.getPosition()[1]);
            Parser parser = new Parser(antibody,new LinkedHashMap<>(),positionMap,organismStorage);
            parser.evauateAll();
        }
    }

    public int getMinimumCost(){
        return init_antibody_cost;
    }

    public void setInit_hp(int init_hp) {
        this.init_hp = init_hp;
    }

    public void setInit_atk(int init_atk) {
        this.init_atk = init_atk;
    }

    public void setInit_gain(int init_gain) {
        this.init_gain = init_gain;
    }

    public void setInit_move_cost(int init_move_cost) {
        this.init_move_cost = init_move_cost;
    }

    public void setInit_antibody_cost(int init_antibody_cost) {
        this.init_antibody_cost = init_antibody_cost;
    }
}

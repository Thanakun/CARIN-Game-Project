package server.game.Game.GameData.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import server.game.Game.GameData.Parser.Parser;

import java.util.LinkedHashMap;


@Component
@PropertySource("classpath:GameDataProperties.properties")
public class AntibodyControl {

    public static AntibodyControl instance;
    @Autowired
    private OrganismStorage organismStorage;
    @Autowired
    private PositionMap positionMap;

    private String default_geneticCode;

    //initial value for set up virus status
    @Value("${init_antibody_hp}")
    private int init_hp;
    @Value("${init_antibody_atk}")
    private int init_atk;
    @Value("${init_antibody_gain}")
    private int init_gain;


    private AntibodyControl(){
        default_geneticCode =
                "virusLoc = virus\n" +
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

    public boolean spawnNewAntibody(int type,int[] location){  //return true if can place at that location

        if(!positionMap.hasOrganism(location)){  //not found any organism at location , it is empty
            System.out.println("create new Antibody");
            Antibody newAntibody = new Antibody(
                    "A"+ organismStorage.getAntibody_count()
                    ,type,location
            ,positionMap,organismStorage);
            newAntibody.setGeneticCode(this.default_geneticCode);
            newAntibody.setStatus(init_hp,init_atk,init_gain);    //set up status and genetic code
            organismStorage.addOrganism(newAntibody);
            return true;
        }
        else{
            return false;
        }
    }

    public void activeAllAntibody(){
        LinkedHashMap<String,Organism> allAntibody =  organismStorage.getallAntibody();
        for(String id:allAntibody.keySet()){
            System.out.println("Antibody id:"+id+" is active");
            Parser parser = new Parser(allAntibody.get(id));
            parser.evauateAll();
        }
    }
}

package server.game.Game.GameData.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import server.game.Game.GameData.Parser.Parser;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
@PropertySource("classpath:GameDataProperties.properties")
public class VirusControl {
    /** Controll All Virus */
    public static VirusControl instance;
    @Autowired
    private PositionMap positionMap;
    @Autowired
    private OrganismStorage organismStorage;
    @Value("${virus_rate:0.5}")
    private float virus_rate; //virus spawn rate
    private Random random;
    private String default_geneticCode;


    //initial value for set up virus status
    @Value("${init_virus_hp}")
    private int init_hp;
    @Value("${init_virus_atk}")
    private int init_atk;
    @Value("${init_virus_gain}")
    private int init_gain;



    private VirusControl(){
        random = new Random();
        default_geneticCode =
                "antibodyLoc = antibody\n" +
                "if (antibodyLoc / 10 - 1)\n" +
                "then \n" +
                "  if (antibodyLoc % 10 - 7) then move upleft\n" +
                "  else if (antibodyLoc % 10 - 6) then move left\n" +
                "  else if (antibodyLoc % 10 - 5) then move downleft\n" +
                "  else if (antibodyLoc % 10 - 4) then move down\n" +
                "  else if (antibodyLoc % 10 - 3) then move downright\n" +
                "  else if (antibodyLoc % 10 - 2) then move right\n" +
                "  else if (antibodyLoc % 10 - 1) then move upright\n" +
                "  else move up\n" +
                "else if (antibodyLoc)\n" +
                "then \n" +
                "  if (antibodyLoc % 10 - 7) then shoot upleft\n" +
                "  else if (antibodyLoc % 10 - 6) then shoot left\n" +
                "  else if (antibodyLoc % 10 - 5) then shoot downleft\n" +
                "  else if (antibodyLoc % 10 - 4) then shoot down\n" +
                "  else if (antibodyLoc % 10 - 3) then shoot downright\n" +
                "  else if (antibodyLoc % 10 - 2) then shoot right\n" +
                "  else if (antibodyLoc % 10 - 1) then shoot upright\n" +
                "  else shoot up\n" +
                "else \n" +
                "{\n" +
                "  dir = random % 8\n" +
                "  if (dir - 6) then move upleft\n" +
                "  else if (dir - 5) then move left\n" +
                "  else if (dir - 4) then move downleft\n" +
                "  else if (dir - 3) then move down\n" +
                "  else if (dir - 2) then move downright\n" +
                "  else if (dir - 1) then move right\n" +
                "  else if (dir) then move upright\n" +
                "  else move up\n" +
                "}\n";
    }
    public static VirusControl getInstance(){
        if(instance==null){
            instance = new VirusControl();
        }
        return instance;
    }

    public void spawnNewVirus(){
        if(organismStorage.getMax_virus_amount()>
                (organismStorage.getVirus_killed()+organismStorage.getVirusAmount())){ //check if virus is not exceed limit
        float ran = random.nextFloat();
        System.out.println("random="+ran);
        if(ran<=virus_rate) {
            System.out.println("create new virus");
            Virus newVirus = new Virus(
                    "V" + organismStorage.getVirus_count()
                    , random.nextInt(3) + 1
                    , firstSpawnLocationInit()
                    , positionMap, organismStorage, this);
            newVirus.setGeneticCode(this.default_geneticCode);
            newVirus.setStatus(init_hp, init_atk, init_gain);    //set up status and genetic code
            organismStorage.addOrganism(newVirus);
        }
        }
    }

    public void spawnNewVirusAfterkill(int type){
        float ran = random.nextFloat();
        System.out.println("random="+ran);
            System.out.println("create new virus");
            Virus newVirus = new Virus(
                    "V"+ organismStorage.getVirus_count()
                    ,type
                    ,firstSpawnLocationInit()
                    ,positionMap,organismStorage,this);
            newVirus.setGeneticCode(this.default_geneticCode);
            newVirus.setStatus(init_hp,init_atk,init_gain);    //set up status and genetic code
            organismStorage.addOrganism(newVirus);
    }

    public int[] firstSpawnLocationInit(){    // to spawn first time at virus constructor
        int[] maxbound= positionMap.getMapDimension();
        int x_posi = random.nextInt(maxbound[0]);
        int y_posi = random.nextInt(maxbound[1]);

        while(positionMap.hasOrganism(new int[]{x_posi,y_posi})){  //loop until successfuly add this in map; prevent spawn at not empty position
            x_posi = random.nextInt(maxbound[0]+1);
            y_posi = random.nextInt(maxbound[1]+1);
        }
        return new int[]{x_posi,y_posi};
    }

    public synchronized void activeAllVirus(){
      LinkedHashMap<String,Organism> allVirus =  organismStorage.getallVirus();
     List<String> allKey = new LinkedList<>(allVirus.keySet());
      for(String id:allKey){
          System.out.println("Virus id:"+id+" is active");
          Organism current_virus = allVirus.get(id);
              Parser parser = new Parser(current_virus,new LinkedHashMap<>(),positionMap,organismStorage);
              parser.evauateAll();
      }
    }

}

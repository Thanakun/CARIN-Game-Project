package server.game.Game.GameData.Model;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Random;

@Component
@PropertySource("classpath:GameDataProperties.properties")
public class VirusControl {
    /** Controll All Virus */
    public static VirusControl instance;
    @Autowired
    private OrganismController organismController;
    @Value("${virus_rate:0.5}")
    private float virus_rate; //virus spawn rate
    private Random random;

    private VirusControl(){
        random = new Random();
    }
    public static VirusControl getInstance(){
        if(instance==null){
            instance = new VirusControl();
        }
        return instance;
    }

    public void spawnNewVirus(){
        float ran = random.nextFloat();
        System.out.println("random="+ran);
        if(ran<=virus_rate){
            System.out.println("create new virus");
            organismController.addOrganism(new Virus(
                  "V"+ organismController.getVirus_count()
            ,random.nextInt(3)+1));  //random type
        }
    }

    public void activeAllVirus(){
      LinkedHashMap<String,Organism> allVirus =  organismController.getallVirus();
      for(String id:allVirus.keySet()){
          System.out.println("Virus id:"+id+" is active");
      }
    }

}

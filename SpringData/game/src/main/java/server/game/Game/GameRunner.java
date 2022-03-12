package server.game.Game;

import com.fasterxml.jackson.databind.ObjectReader;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import server.game.Game.GameData.Controller.Menu;
import server.game.Game.GameData.Model.*;
import org.springframework.stereotype.Component;

@Component
public class GameRunner extends Thread {
    @Autowired
    private Timer timer;
    @Autowired
    private VirusControl virusControl;
    @Autowired
    private PositionMap positionMap;
    @Autowired
    private AntibodyControl antibodyControl;
    @Autowired
    private Menu menu;
    @Autowired
    private OrganismStorage organismStorage;
    @Autowired
    private Player player;

    public void main() {
        //set up
        antibodyControl.setConfigValue();
        organismStorage.setConfigValue();
        player.setConfigValue();
        positionMap.setConfigValue();
        virusControl.setConfigValue();

        while(true){
            try{

                String gameState = menu.getGameState();
                if(gameState.equals("MAIN_MENU")){
                    System.out.println("IN MAIN MENU");

                    Thread.sleep(10);
                }
                else if(gameState.equals("START")){

                    System.out.println("Loading...");
                    player.resetCredit();
                    timer.resetTime();
                    positionMap.resetPositionMap();
                    organismStorage.resetStorage();
                    menu.setGameState("PLAYING");

                }
                else if(gameState.equals("PLAYING")) {
                      timer.on();
                        System.out.println("--------------------------");
                        //don't know why but some time it mismatch with time from api, api is precise
                        System.out.println("Time Now: "+timer.getTime_count()+" active?"+timer.getTimerStatus());

                        antibodyControl.activeAllAntibody();
                        virusControl.activeAllVirus();
                        virusControl.spawnNewVirus();
                        checkGame();
                        Thread.sleep(timer.getTimePass());


                }
                else if(gameState.equals("PAUSE")){
                    timer.off();
                    Thread.sleep(10);
                }
                else if(gameState.equals("WIN")){
                    System.out.println("*********You Win********* ");
                    timer.off();
                    Thread.sleep(10);

                }
                else if(gameState.equals("LOSE")){
                    System.out.println("*********You Lose********* ");
                    timer.off();
                    Thread.sleep(10);

                }


            }catch (InterruptedException e){
                System.out.println("main interrupted");
            }
        }

    }


    public void checkGame() {
       if(organismStorage.getVirus_killed()==organismStorage.getMax_virus_amount()){
           System.out.println(organismStorage.getMax_virus_amount()+" max virus amount");
           // all virus get killed
           menu.setGameState("WIN");
           player.resetCredit();
           timer.resetTime();
           positionMap.resetPositionMap();
           organismStorage.resetStorage();
           System.out.println("Player win, All virus die");
       }
       else if(organismStorage.getAntibody_killed()>0 && (organismStorage.getAntibodyAmount()==0)
               &&(player.getCurrentCredit()<antibodyControl.getMinimumCost()) ){
           //all antibody die and don't have enough credit
           menu.setGameState("LOSE");
           player.resetCredit();
           timer.resetTime();
           positionMap.resetPositionMap();
           organismStorage.resetStorage();
           System.out.println("Player lose, All antibody die");
       }
       else if((organismStorage.getVirusAmount()==organismStorage.getMax_virus_amount())&&
       organismStorage.getAntibodyAmount()==0){
           //all virus spawn but dont have any antibody on field
           menu.setGameState("LOSE");
           player.resetCredit();
           timer.resetTime();
           positionMap.resetPositionMap();
           organismStorage.resetStorage();
           System.out.println("Player lose, All virus has spawned");
       }
    }

    @Override
    public void run(){

        main();
    }

}

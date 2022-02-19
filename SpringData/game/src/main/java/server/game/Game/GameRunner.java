package server.game.Game;

import com.fasterxml.jackson.databind.ObjectReader;
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

    public void main() {
        int count = 0;
        while(true){
            try{
                String gameState = menu.getGameState();
                if(gameState.equals("MAIN_MENU")){
                    System.out.println("IN MAIN MENU");
                    Thread.sleep(100);
                }
                else if(gameState.equals("START")){
                    System.out.println("Loading...");
                    timer.resetTime();
                    positionMap.resetPositionMap();
                    organismStorage.resetStorage();
                    menu.setGameState("PLAYING");
                    Thread.sleep(1000);

                }
                else if(gameState.equals("PLAYING")) {
                    System.out.println("--------------------------");
                    System.out.println("main loop number :" + count++);
                    timer.on();

                    antibodyControl.activeAllAntibody();
                       virusControl.activeAllVirus();
                         virusControl.spawnNewVirus();
                    Thread.sleep(timer.getTimePass());
                }
                else if(gameState.equals("PAUSE")){
                    timer.off();
                    Thread.sleep(100);
                }
                else if(gameState.equals("END")){
                    System.out.println("*********Game End********* ");
                    menu.setGameState("MAIN_MENU");
                    Thread.sleep(100);

                }


            }catch (InterruptedException e){
                System.out.println("main interrupted");
            }
        }

    }

    @Override
    public void run(){

        main();
    }

}

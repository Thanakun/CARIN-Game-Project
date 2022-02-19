package server.game.Game;

import org.springframework.beans.factory.annotation.Autowired;
import server.game.Game.GameData.Controller.Menu;
import server.game.Game.GameData.Model.AntibodyControl;
import server.game.Game.GameData.Model.PositionMap;
import server.game.Game.GameData.Model.Timer;
import org.springframework.stereotype.Component;
import server.game.Game.GameData.Model.VirusControl;

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

    public void main() {
        int count = 0;
        while(true){
            try{
                String gameState = menu.getGameState();
                if(gameState.equals("MAIN_MENU")){
                    System.out.println("IN MAIN MENU");
                    Thread.sleep(100);
                }
                else if(gameState.equals("PLAYING")) {
                    System.out.println("--------------------------");
                    System.out.println("main loop number :" + count++);
                    timer.on();

                    antibodyControl.activeAllAntibody();
                    //  antibodyControl.spawnNewAntibody();
                    //   virusControl.activeAllVirus();
                    //     virusControl.spawnNewVirus();
                    Thread.sleep(timer.getTimePass());
                }
                else if(gameState.equals("PAUSE")){
                    timer.off();
                    Thread.sleep(100);
                }
                else if(gameState.equals("END")){
                    System.out.println("*********Game End********* ");
                    timer.interrupt();
                    this.interrupt();
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

package server.game.Game;

import org.springframework.beans.factory.annotation.Autowired;
import server.game.Game.GameData.Model.Timer;
import org.springframework.stereotype.Component;
import server.game.Game.GameData.Model.VirusControl;

@Component
public class GameRunner extends Thread {
    @Autowired
    private Timer timer;
    @Autowired
    private VirusControl virusControl;

    public void main() {
        int count = 0;
        while(true){
            try{
                System.out.println("--------------------------");
                System.out.println("main loop number :"+count++);
                timer.on();
                virusControl.activeAllVirus();
                virusControl.spawnNewVirus();
                Thread.sleep(timer.getTimePass());

            }catch (InterruptedException e){
                System.out.println("main interrupted");
            }
            System.out.println("--------------------------");
        }

    }

    @Override
    public void run(){
        main();
    }

}

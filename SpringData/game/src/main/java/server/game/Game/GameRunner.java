package server.game.Game;

import org.springframework.beans.factory.annotation.Autowired;
import server.game.Game.GameData.Model.Timer;
import org.springframework.stereotype.Component;

@Component
public class GameRunner extends Thread {
    @Autowired
    private Timer timer;



    public void main() {
        int count = 0;
        while(true){
            try{
                System.out.println("main loop number :"+count++);
                timer.on();
                Thread.sleep(2000);
                timer.off();
                Thread.sleep(2000);
                timer.on();

            }catch (InterruptedException e){
                System.out.println("main interrupted");
            }
            System.out.println("end main");
        }

    }

    @Override
    public void run(){
        main();
    }

}

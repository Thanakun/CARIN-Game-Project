package server.game.Game;

import server.game.Game.GameData.Model.Timer;
import org.springframework.stereotype.Component;

@Component
public class GameRunner extends Thread {

    public static void main(String[] args) {
        Timer timer = Timer.getInstance();
        Timer timer2 = Timer.getInstance();


        timer2.start();
        timer.on();
        try{
            Thread.sleep(2000);
            timer.increaseMultiplier();
            timer.off();
            Thread.sleep(2000);
           timer.on();
            Thread.sleep(2000);
           timer.interrupt();

        }catch (InterruptedException e){
            System.out.println("main interrupted");
        }
        System.out.println("end main");
    }

    @Override
    public void run(){
        main(null);
    }

}

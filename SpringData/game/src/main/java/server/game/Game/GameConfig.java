package server.game.Game;

import server.game.Game.GameData.Model.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfig {
    @Autowired
    private Timer timer;
   // private static PositionMap map = PositionMap.getInstance();
   // @Autowired
  //  private GameRunner gameRunner;

    @Bean
    CommandLineRunner commandLineRunner(){
        return args ->{
            timer.setDaemon(true);
            timer.start();
       // gameRunner.setDaemon(true);
       // gameRunner.start();
        };
    }
}

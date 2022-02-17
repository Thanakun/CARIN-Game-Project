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
    @Autowired
    private GameRunner gameRunner;

    @Bean
    CommandLineRunner commandLineRunner(){
        return args ->{
            gameRunner.setDaemon(true);
            gameRunner.start();
            timer.setDaemon(true);
            timer.start();

        };
    }
}

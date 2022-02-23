package server.game.Game;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import server.game.Game.GameData.Controller.UserControl;
import server.game.Game.GameData.Model.PositionMap;
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
    @Autowired
    private UserControl userControl;


    @Bean
    CommandLineRunner commandLineRunner(){
        return args ->{
            userControl.setDaemon(true);
            gameRunner.setDaemon(true);
            timer.setDaemon(true);
            gameRunner.start();
            timer.start();
            userControl.start();
        };
    }


}

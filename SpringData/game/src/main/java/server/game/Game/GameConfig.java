package server.game.Game;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import server.game.Game.GameData.Model.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@PropertySource("classpath:GameDataProperties.properties")
public class GameConfig {
    @Value("${virus_rate:0.5}")
    private float virus_rate; //virus birth rate
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
            System.out.println(virus_rate);
        };
    }
}

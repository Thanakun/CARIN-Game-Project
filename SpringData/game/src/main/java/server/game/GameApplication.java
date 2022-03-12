package server.game;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import server.game.Game.GameConfig;
import server.game.Game.GameData.Controller.UserControl;
import server.game.Game.GameData.Model.Timer;
import server.game.Game.GameRunner;


@SpringBootApplication
public class GameApplication {
	@Autowired
    private Timer timer;
    @Autowired
    private GameRunner gameRunner;
    @Autowired
    private UserControl userControl;

	public static void main(String[] args) {
		SpringApplication.run(GameApplication.class, args);
	}


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

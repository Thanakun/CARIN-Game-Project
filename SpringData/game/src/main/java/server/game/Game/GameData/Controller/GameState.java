package server.game.Game.GameData.Controller;


import org.springframework.stereotype.Component;
import server.game.Game.Type.MenuReq;
import server.game.Game.Type.Request;

@Component
public class GameState {
    private String gameState;     //MAIN_MENU SETUP START PLAYING PAUSE WIN LOSE
    private static GameState instance;

    private GameState(){
        gameState = "MAIN_MENU";
    }
    public static GameState getInstance(){
        if(instance==null){
            instance = new GameState();
        }
        return instance;
    }

    public void computeGameState(Request req){
        String wanted_state = ((MenuReq) req).getWanted_state();
        if(wanted_state.equals("PAUSE")){
            gameState = "PAUSE";
        }
        if(wanted_state.equals("SETUP")){
            gameState = "SETUP";
        }
        if(wanted_state.equals("START")){
            gameState = "START";
        }
        if(wanted_state.equals("PLAYING")){
            gameState = "PLAYING";
        }
        if(wanted_state.equals("MAIN_MENU")){
            gameState = "MAIN_MENU";
        }
    }

    public  void setGameState(String state){
        this.gameState = state;
    }

    public String getGameState(){
        return gameState;
    }

    
}

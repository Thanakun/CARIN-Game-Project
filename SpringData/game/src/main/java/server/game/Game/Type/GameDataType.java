package server.game.Game.Type;

import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Model.Timer;

import java.util.LinkedHashSet;
import java.util.Map;

public class GameDataType {
    private Timer timer;
    private int credit;
    private String gameState;
    private LinkedHashSet<Organism> allOrgan;

    public GameDataType(){}
    public GameDataType(Timer timer,int credit,String gameState,Map<String,Organism> virus,Map<String,Organism> antibody){
        this.timer = timer;
        this.credit = credit;
        this.gameState =  gameState;
        allOrgan = new LinkedHashSet<>();
        setAllOrgan(virus,antibody);
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public LinkedHashSet<Organism> getAllOrgan() {
        return allOrgan;
    }

    public void setAllOrgan(Map<String,Organism> allVirus,Map<String,Organism> allAntibody){
        allOrgan.addAll(allVirus.values());
        allOrgan.addAll(allAntibody.values());
    }
}

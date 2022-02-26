package server.game.Game.Type;

import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Model.Timer;

import java.util.LinkedHashSet;
import java.util.Map;

public class GameDataType {
    private TimerDataType timer;
    private int credit;
    private String gameState;
    private int[] dimension;
    private LinkedHashSet<OrganismDataType> allOrgan;

    public GameDataType(){}

    public GameDataType(Timer timer, int credit, String gameState, int[] dimension,
                        Map<String,Organism> virus,Map<String,Organism> antibody) {
        this.timer = new TimerDataType(timer.getTime_count(),timer.getTimePass());
        this.credit = credit;
        this.gameState = gameState;
        this.dimension = dimension;
        allOrgan = new LinkedHashSet<>();
        setAllOrgan(virus,antibody);
    }

    public TimerDataType getTimer() {
        return timer;
    }

    public void setTimer(TimerDataType timer) {
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

    public int[] getDimension() {
        return dimension;
    }

    public void setDimension(int[] dimension) {
        this.dimension = dimension;
    }

    public LinkedHashSet<OrganismDataType> getAllOrgan() {
        return allOrgan;
    }

    public void setAllOrgan(Map<String,Organism> virus,Map<String,Organism> antibody) {
      for(Organism organ:virus.values()){
          allOrgan.add(new OrganismDataType(organ.getId(),organ.getCategory(),
                  organ.getType(),organ.getHP(),organ.getMax_HP(),organ.getPosition()));
      }
        for(Organism organ:antibody.values()){
            allOrgan.add(new OrganismDataType(organ.getId(),organ.getCategory(),
                    organ.getType(),organ.getHP(),organ.getMax_HP(),organ.getPosition()));
        }
    }
}

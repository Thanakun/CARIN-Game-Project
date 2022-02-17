package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;

import java.util.Map;

public class Number implements Expression{
    private int value;
    public  Number(int value){
        this.value = value;
    }
    @Override
    public int eval(Organism actor,Map<String, Integer> binding) {
        return value;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(Integer.toString(value));
    }
}

package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Model.OrganismStorage;
import server.game.Game.GameData.Model.PositionMap;

import java.util.Map;

public class Number implements Expression{
    private int value;
    public  Number(int value){
        this.value = value;
    }
    @Override
    public int eval(Organism actor,Map<String, Integer> binding
            , PositionMap positionMap, OrganismStorage organismStorage) {
        return value;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(Integer.toString(value));
    }
}

package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;

import java.util.Map;

public interface Statement {
    public void eval(Organism actor, Map<String,Integer> binding);
    public void prettyPrint(StringBuilder s);
    /** Statement → Command | BlockStatement | IfStatement | WhileStatement */
}

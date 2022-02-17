package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;

import java.util.Map;

public interface Expression {

     public int eval(Organism actor, Map<String,Integer> binding);
     public void prettyPrint(StringBuilder s);

     /** Expression → Expression + Term | Expression - Term | Term */
     }

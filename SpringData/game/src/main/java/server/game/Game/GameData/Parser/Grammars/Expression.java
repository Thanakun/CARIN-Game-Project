package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Model.OrganismStorage;
import server.game.Game.GameData.Model.PositionMap;

import java.util.Map;

public interface Expression {

     public int eval(Organism actor, Map<String,Integer> binding
             , PositionMap positionMap, OrganismStorage organismStorage);
     public void prettyPrint(StringBuilder s);

     /** Expression → Expression + Term | Expression - Term | Term */
     }

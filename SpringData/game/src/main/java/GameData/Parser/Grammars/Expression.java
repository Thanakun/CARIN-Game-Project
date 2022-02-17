package GameData.Parser.Grammars;

import GameData.Model.Organism;

import java.util.Map;

public interface Expression {

     public int eval(Organism actor, Map<String,Integer> binding);
     public void prettyPrint(StringBuilder s);

     /** Expression → Expression + Term | Expression - Term | Term */
     }

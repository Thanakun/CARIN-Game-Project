package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public interface Expression {

     public int eval(Organism actor, Map<String,Expression> binding);
     public void prettyPrint(StringBuilder s);

     /** Expression → Expression + Term | Expression - Term | Term */
     }

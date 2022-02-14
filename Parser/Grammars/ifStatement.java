package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class ifStatement implements Statement{
     @Override
     public void eval(Organism actor, Map<String, Expression> binding) {

     }

     @Override
     public void prettyPrint(StringBuilder s) {

     }
     /** IfStatement → if ( Expression ) then Statement else Statement  */
     }

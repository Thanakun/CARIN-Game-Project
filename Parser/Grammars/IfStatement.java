package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class IfStatement implements Statement{
     private Expression condition;
     private Statement if_true;
     private Statement if_false;

     public IfStatement(Expression condition, Statement if_true, Statement if_false) {
          this.condition = condition;
          this.if_true = if_true;
          this.if_false = if_false;
     }

     @Override
     public void eval(Organism actor, Map<String, Expression> binding) {

     }

     @Override
     public void prettyPrint(StringBuilder s) {

     }
     /** IfStatement → if ( Expression ) then Statement else Statement  */
     }

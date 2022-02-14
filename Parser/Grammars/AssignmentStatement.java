package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class AssignmentStatement implements Statement {
     private String identifier;
     private Expression expr;

     public AssignmentStatement(String identifier,Expression expr){
          this.identifier = identifier;
          this.expr = expr;
     }
     @Override
     public void eval(Organism actor, Map<String, Expression> binding) {

     }

     @Override
     public void prettyPrint(StringBuilder s) {

     }
     /** AssignmentStatement → <identifier> = Expression */
     }

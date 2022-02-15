package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class AssignmentStatement implements Statement {
     private Identifier identifier;
     private Expression expr;

     public AssignmentStatement(Identifier identifier,Expression expr){
          this.identifier = identifier;
          this.expr = expr;
     }
     @Override
     public void eval(Organism actor, Map<String, Expression> binding) {

     }

     @Override
     public void prettyPrint(StringBuilder s) {
          identifier.prettyPrint(s);
          s.append("=");
          expr.prettyPrint(s);
          s.append("\n");
     }
     /** AssignmentStatement → <identifier> = Expression */
     }

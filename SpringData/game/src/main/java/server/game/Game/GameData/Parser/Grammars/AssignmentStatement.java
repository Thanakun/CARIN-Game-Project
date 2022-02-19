package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Model.OrganismStorage;
import server.game.Game.GameData.Model.PositionMap;

import java.util.Map;

public class AssignmentStatement implements Statement {
     private Identifier identifier;
     private Expression expr;

     public AssignmentStatement(Identifier identifier,Expression expr){
          this.identifier = identifier;
          this.expr = expr;
     }
     @Override
     public void eval(Organism actor, Map<String, Integer> binding
             , PositionMap positionMap, OrganismStorage organismStorage) {
          int expr_val = expr.eval(actor,binding
                  ,  positionMap,  organismStorage);
          identifier.set(binding,expr_val);
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

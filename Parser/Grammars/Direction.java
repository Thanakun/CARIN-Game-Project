package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Direction implements Expression{
     private String direction;
     public Direction(String direction){
          this.direction = direction;
     }
     @Override
     public int eval(Map<String,Expression> binding) {
     return 0;
     }

     @Override
     public void prettyPrint(StringBuilder s) {
          s.append(direction);
          s.append("\n");
     }
     /** Direction → left | right | up | down | upleft | upright | downleft | downright  */
     }

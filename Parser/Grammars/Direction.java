package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Direction implements Expression{
     private String direction;
     public Direction(String direction){
          this.direction = direction;
     }
     @Override
     public int eval(Organism actor,Map<String,Integer> binding) {
     switch (direction){
          case "up":return 11;
          case "upright":return 12;
          case "right":return 13;
          case "downright":return 14;
          case "down" : return 15;
          case "downleft" : return 16;
          case "left" :return 17;
          case "upleft":return 18;
     }
     return 0;
     }

     @Override
     public void prettyPrint(StringBuilder s) {
          s.append(direction);
          s.append("\n");
     }
     /** Direction → left | right | up | down | upleft | upright | downleft | downright  */
     }

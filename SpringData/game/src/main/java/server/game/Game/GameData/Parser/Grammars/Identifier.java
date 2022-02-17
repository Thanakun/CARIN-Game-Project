package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Parser.SyntaxError;

import java.util.Map;
import java.util.Random;

public class Identifier implements Expression{
    private String name;
    private static Random random= new Random();
    public Identifier(String name){
        this.name = name;
    }
    @Override
    public int eval(Organism actor,Map<String, Integer> binding) throws SyntaxError {
        if(binding==null){
            throw new SyntaxError();
        }
       if(binding.get(name)==null){  //init first time ever seen
           binding.put(name,0);
        }
       if(name.equals("random")){  //random 0-99
           binding.put(name,random.nextInt(100));
        }
       return binding.get(name);
    }
    public void set(Map<String,Integer> binding,int value){
        binding.put(name,value);
    }
    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
    }
}

package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Factor implements Grammar{
    @Override
    public void eval(Organism actor, Map<String,Integer> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
    /** Factor → Power ^ Factor | Power */
}

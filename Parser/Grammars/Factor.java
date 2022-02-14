package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Factor implements Expression{
    @Override
    public int eval(Organism actor, Map<String,Expression> binding) {
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
    /** Factor → Power ^ Factor | Power */
}

package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Factor implements Expression{
    @Override
    public int eval( Map<String,Expression> binding) {
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
    /** Factor → Power ^ Factor | Power */
}

package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Factor implements Grammar{
    @Override
    public void eval(Organism actor, Map<String,Expression> binding) {

    }
    /** Factor → Power ^ Factor | Power */
}

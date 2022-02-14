package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class WhileStatement implements Grammar{
    @Override
    public void eval(Organism actor, Map<String, Expression> binding) {

    }
    /** WhileStatement → while ( Expression ) Statement */
}

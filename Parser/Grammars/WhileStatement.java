package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class WhileStatement implements Statement{
    @Override
    public void eval(Organism actor, Map<String, Expression> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
    /** WhileStatement → while ( Expression ) Statement */
}

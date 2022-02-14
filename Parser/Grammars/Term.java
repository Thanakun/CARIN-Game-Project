package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Term implements Expression {
    @Override
    public int eval(Organism actor, Map<String, Expression> binding) {
    return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }

    /** Term → Term * Factor | Term / Factor | Term % Factor | Factor */
}

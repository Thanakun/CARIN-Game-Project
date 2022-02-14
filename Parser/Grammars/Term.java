package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Term implements Grammar {
    @Override
    public void eval(Organism actor, Map<String, Integer> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }

    /** Term → Term * Factor | Term / Factor | Term % Factor | Factor */
}

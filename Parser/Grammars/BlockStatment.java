package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class BlockStatment implements Grammar {
    @Override
    public void eval(Organism actor, Map<String, Expression> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
    /** BlockStatement → { Statement* } */
}

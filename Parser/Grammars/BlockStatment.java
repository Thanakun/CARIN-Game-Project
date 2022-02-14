package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class BlockStatment implements Grammar {
    @Override
    public void eval(Organism actor, Map<String, Expression> binding) {

    }
    /** BlockStatement → { Statement* } */
}

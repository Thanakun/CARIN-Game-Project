package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Power implements Expression{
    @Override
    public int eval( Map<String,Expression> binding) {
    return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }

    /** Power → <number> | <identifier> | ( Expression ) | SensorExpression */
}

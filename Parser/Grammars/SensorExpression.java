package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class SensorExpression implements Expression{
    @Override
    public int eval(Map<String, Expression> binding) {
    return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
    /** SensorExpression → virus | antibody | nearby Direction */
}

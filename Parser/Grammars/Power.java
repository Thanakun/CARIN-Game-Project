package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Power implements Grammar{
    @Override
    public void eval(Organism actor, Map<String,Integer> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }

    /** Power → <number> | <identifier> | ( Expression ) | SensorExpression */
}

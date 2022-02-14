package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Power implements Grammar{
    @Override
    public void eval(Organism actor, Map<String,Expression> binding) {

    }

    /** Power → <number> | <identifier> | ( Expression ) | SensorExpression */
}

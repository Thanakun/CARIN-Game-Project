package Parser.Grammars;

import Model.Organism;

import java.util.Map;
/**not in use**/

public class Power implements Expression{
    @Override
    public int eval(Organism actor, Map<String,Integer> binding) {
    return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }

    /** Power → <number> | <identifier> | ( Expression ) | SensorExpression */
}

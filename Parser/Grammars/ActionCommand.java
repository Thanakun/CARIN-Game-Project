package Parser.Grammars;

import Model.Organism;
import java.util.Map;

/**not in use**/

public class ActionCommand implements Statement{

    public ActionCommand(){
    }

    @Override
    public void eval(Organism actor, Map<String, Integer> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }


    /**  ActionCommand => MoveCommand| AttackCommand */
}

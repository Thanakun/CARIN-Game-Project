package Parser.Grammars;

import Model.Organism;
import java.util.Map;

public class ActionCommand implements Grammar{

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

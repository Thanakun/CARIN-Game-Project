package Parser.Grammars;

import Model.Organism;
import java.util.Map;

public class ActionCommand implements Statement{

    public ActionCommand(){
    }

    @Override
    public void eval(Organism actor, Map<String, Expression> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }


    /**  ActionCommand => MoveCommand| AttackCommand */
}

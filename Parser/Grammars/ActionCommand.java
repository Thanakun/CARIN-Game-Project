package Parser.Grammars;

import Model.Organism;
import java.util.Map;

public class ActionCommand implements Grammar{
    @Override
    public void eval(Organism actor, Map<String, Expression> binding) {

    }
    /**  ActionCommand => MoveCommand| AttackCommand */
}

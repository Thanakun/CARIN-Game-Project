package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class Statement implements Grammar{
    @Override
    public void eval(Organism actor, Map<String,Expression> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
    /** Statement → Command | BlockStatement | IfStatement | WhileStatement */
}

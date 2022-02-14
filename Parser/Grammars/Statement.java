package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public interface Statement {
    public void eval(Organism actor, Map<String,Expression> binding);
    public void prettyPrint(StringBuilder s);
    /** Statement → Command | BlockStatement | IfStatement | WhileStatement */
}

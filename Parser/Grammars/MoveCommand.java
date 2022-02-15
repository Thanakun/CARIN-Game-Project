package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class MoveCommand implements Statement {
    private Direction direction;
    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void eval(Organism actor, Map<String,Expression> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {
    s.append("move ");
    direction.prettyPrint(s);
    }
    /** MoveCommand → move Direction */
}

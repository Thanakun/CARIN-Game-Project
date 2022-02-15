package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class AttackCommand implements Statement{
    private Direction direction;
    public AttackCommand(Direction direction) {
    this.direction = direction;
    }

    @Override
    public void eval(Organism actor, Map<String, Expression> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("attack ");
        direction.prettyPrint(s);
    }
    /** AttackCommand → shoot Direction */
}

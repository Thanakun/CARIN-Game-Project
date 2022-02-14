package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class WhileStatement implements Statement{
    private Expression condition;
    private Statement if_true;

    public WhileStatement(Expression condition, Statement if_true) {
    this.condition = condition;
    this.if_true = if_true;
    }

    @Override
    public void eval(Organism actor, Map<String, Expression> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {

    }
    /** WhileStatement → while ( Expression ) Statement */
}

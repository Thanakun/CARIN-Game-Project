package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;

import java.util.Map;

public class WhileStatement implements Statement{
    private Expression condition;
    private Statement if_true;

    public WhileStatement(Expression condition, Statement if_true) {
    this.condition = condition;
    this.if_true = if_true;
    }

    @Override
    public void eval(Organism actor, Map<String, Integer> binding) {
        while(condition.eval(actor,binding)>0){
            if_true.eval(actor,binding);
        }
    }

    @Override
    public void prettyPrint(StringBuilder s) {

        s.append("while (");
        condition.prettyPrint(s);
        s.append(") ");
        s.append("\n");
        if_true.prettyPrint(s);
    }
    /** WhileStatement → while ( Expression ) Statement */
}

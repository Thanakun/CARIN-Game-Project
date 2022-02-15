package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class BlockStatment implements Statement {
    private Statement statement;
    public BlockStatment(Statement statement){
        this.statement = statement;
    }
    @Override
    public void eval(Organism actor, Map<String, Expression> binding) {

    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("{");
        s.append("\n");
        statement.prettyPrint(s);
        s.append("}");

    }
    /** BlockStatement → { Statement* } */
}

package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class BinaryExpr implements Expression {
    private Expression left;
    private Expression right;
    private String op;

    public BinaryExpr(Expression left,String op,Expression right){
        this.left = left;
        this.op  = op;
        this.right = right;
    }

    @Override
    public int eval( Map<String, Expression> binding) {
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("(");
        left.prettyPrint(s);
        s.append(op);
        right.prettyPrint(s);
        s.append(")");
    }
}

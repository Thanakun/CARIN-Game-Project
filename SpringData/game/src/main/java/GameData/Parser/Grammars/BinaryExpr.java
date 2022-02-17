package GameData.Parser.Grammars;

import GameData.Model.Organism;

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
    public int eval(Organism actor, Map<String, Integer> binding) {
        int l_value=left.eval(actor,binding);
        int r_value=right.eval(actor,binding);
        if(op.equals("+"))return l_value+r_value;
        if(op.equals("-"))return l_value-r_value;
        if(op.equals("*"))return l_value*r_value;
        if(op.equals("/"))return l_value/r_value;
        if(op.equals("%"))return l_value%r_value;
        if(op.equals("^"))return (int) Math.pow((double)l_value,(double) r_value);

        throw new RuntimeException("unknow op: "+op);
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

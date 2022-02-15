package Parser.Grammars;

import java.util.Map;

public class Identifier implements Expression{
    private String name;
    public Identifier(String name){
        this.name = name;
    }
    @Override
    public int eval(Map<String, Expression> binding) {
        return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(name);
    }
}

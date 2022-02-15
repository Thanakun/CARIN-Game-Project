package Parser.Grammars;

import Model.Organism;

import java.util.Map;

public class SensorExpression implements Expression{
    private String type;
    private Direction direction;
    public SensorExpression(String type,Direction direction){
        this.type = type;
        this.direction = direction;
    }
    @Override
    public int eval(Map<String, Expression> binding) {
    return 0;
    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append(type);
        if(direction!=null){
            direction.prettyPrint(s);
        }
    }
    /** SensorExpression → virus | antibody | nearby Direction */
}

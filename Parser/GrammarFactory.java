package Parser;
import Parser.Grammars.*;

public class GrammarFactory {
    private static GrammarFactory instance;
    private GrammarFactory(){
    }
    public static GrammarFactory getInstance(){
        if(instance==null){
           instance = new GrammarFactory();
        }
        return instance;
    }

    public Direction getDirection(String direction){
        return new Direction(direction);
    }
    public Statement getAssignment(String identifier,Expression expr){
        return new AssignmentStatement(identifier,expr);
    }


    public Statement getIfStatement(Expression condition, Statement if_true, Statement if_false) {
        return new IfStatement(condition,if_true,if_false);
    }

    public Statement getWhileStatement(Expression condition, Statement if_true) {
        return  new WhileStatement(condition,if_true);
    }

    public Statement getMoveCommand(Direction direction) {
        return  new MoveCommand(direction);
    }

    public Statement getAttackCommand(Direction direction) {
        return  new AttackCommand(direction);
    }
}

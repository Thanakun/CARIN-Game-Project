package Parser;

import Model.Organism;
import Parser.Grammars.*;

import java.util.Map;
import java.util.regex.Pattern;

public class Parser {
    private Tokenizer tkz;
    private Statement ASTtree;
    private Map<String, Expression> bindings;
    private GrammarFactory grammarFactory;
    private Organism actor;

    public Parser(String src,Organism actor,Map<String,Expression> bindings){
        this.tkz = new Tokenizer(src);
        this.bindings = bindings;
        this.actor = actor;
        ASTtree = compute();
    }

    public Statement compute() throws RuntimeException{
        Statement parsed = parseStatement();
        if(tkz.peek().equals("")){
            return parsed;
        }
        else{
            throw new RuntimeException("Syntax Error");
        }
    }

    public Statement parseStatement() throws RuntimeException{

        // move => Move; consume(move), parseDirection
        //shoot => Shoot; consume(shoot), parseDirection
        // { =>Block; consume({) ,parseStatement, consume(})
        // if => If;  consume(if), consume( ( ), parseExpression , consume( ) ),consume(then), parseStatement,consume(else),parseStatement
        // while =>while; consume(while), consume( ( ), parseExpression , consume( ) ), parseStatement
        // identifier => Assign; consume(identifier) ,consume(=),parseExpression
        Statement state;
        if(tkz.peek("{")){
            tkz.consume("{");
           state =  parseStatement();
           tkz.consume("}");
        }
        else if(tkz.peek("if")){
            state = parseIf();
        }
        else if(tkz.peek("while")){
            state = parseWhile();
        }
        else if(tkz.peek("move")){
            state = parseMove();
        }
        else if(tkz.peek("shoot")){
            state = parseShoot();
        }
        else { //case peek at identifier
            state = parseAssignment();
        }
        return  state;
    }

    public Statement parseMove(){
        tkz.consume("move");
        return grammarFactory.getMoveCommand(parseDirection());
    }
    public Statement parseShoot(){
        tkz.consume("shoot");
        return grammarFactory.getAttackCommand(parseDirection());
    }

    public Statement parseWhile(){
        tkz.consume("while");
        tkz.consume("(");
        Expression condition = parseExpression();
        tkz.consume(")");
        Statement if_true = parseStatement();
        return grammarFactory.getWhileStatement(condition,if_true);
    }

    public Statement parseIf(){
        tkz.consume("if");
        tkz.consume("(");
        Expression condition = parseExpression();
        tkz.consume(")");
        tkz.consume("then");
        Statement if_true = parseStatement();
        tkz.consume("else");
        Statement if_false = parseStatement();
        return grammarFactory.getIfStatement(condition,if_true,if_false);

    }

    public Direction parseDirection() throws SyntaxError{
        String direction = tkz.consume();
       if(direction.equals("left")||direction.equals("right")||direction.equals("up")||direction.equals("down")
       ||direction.equals("upleft")||direction.equals("upright")||direction.equals("downleft")||direction.equals("downright")){
           return grammarFactory.getDirection(direction);
       }
       else{
           throw new SyntaxError("wrong format direction");
       }
    }

    public Statement parseAssignment() throws SyntaxError{
        String identifier = tkz.consume();
        if(!isCharacter(identifier.charAt(0))){  //wrong identifier format
            throw new SyntaxError("wrong identifier format");
        }
        tkz.consume("=");
        return grammarFactory.getAssignment(identifier,parseExpression());
    }

    public Expression parseExpression() throws SyntaxError{
        
    }


    public void evauateAll(){
        ASTtree.eval(actor,bindings);
    }

    private boolean isCharacter(char ch){
        return Pattern.matches("[a-zA-z]",new StringBuilder(1).append(ch));
    }
    public boolean isNumber(String s) throws NumberFormatException{
        try{
            int parsed = Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

}

package server.game.Game.GameData.Parser;

import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Parser.Grammars.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Parser {
    private Tokenizer tkz;
    private List<Statement> ASTtree;
    private Map<String, Integer> bindings;
    private static GrammarFactory grammarFactory;
    private Organism actor;

    public Parser(String src,Organism actor,Map<String,Integer> bindings){
        this.tkz = new Tokenizer(src);
        this.bindings = bindings;
        this.actor = actor;
        grammarFactory = GrammarFactory.getInstance();
        ASTtree = new LinkedList<>();
        compute();

    }

    public void compute() throws RuntimeException{
        parseProgram();
        if(tkz.hasNext()){
            throw new RuntimeException("Syntax Error");
        }
    }

    public void parseProgram() throws SyntaxError{
        while(!tkz.peek("")){
            ASTtree.add(parseStatement());
        }
    }

    public Statement parseStatement() throws SyntaxError{

        // move => Move; consume(move), parseDirection
        //shoot => Shoot; consume(shoot), parseDirection
        // { =>Block; consume({) ,parseStatement, consume(})
        // if => If;  consume(if), consume( ( ), parseExpression , consume( ) ),consume(then), parseStatement,consume(else),parseStatement
        // while =>while; consume(while), consume( ( ), parseExpression , consume( ) ), parseStatement
        // identifier => Assign; consume(identifier) ,consume(=),parseExpression

            Statement state ;
            if (tkz.peek("{")) {
               state = parseBlock();
            } else if (tkz.peek("if")) {
                state = parseIf();
            }
            else if (tkz.peek("while")) {
                state = parseWhile();
            } else if (tkz.peek("move")) {
                state = parseMove();
            } else if (tkz.peek("shoot")) {
                state = parseShoot();
            } else { //case peek at identifier
                state = parseAssignment();
            }
            return  state;
    }

    public Statement parseBlock(){
        tkz.consume("{");
        BlockStatment block =  grammarFactory.getBlock();
        while(!tkz.peek("}")){
            block.addToBlock(parseStatement());
        }
        tkz.consume("}");
        return block;
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
           System.out.println(direction);
           throw new SyntaxError("wrong format direction");
       }
    }

    public Statement parseAssignment() throws SyntaxError{

        String identifier_name = tkz.consume();
        Identifier identifier;
        if(!isCharacter(identifier_name.charAt(0))){  //wrong identifier format
            throw new SyntaxError("wrong identifier format");
        }
        identifier = grammarFactory.getIdentifier(identifier_name);
        tkz.consume("=");
        Expression expr = parseExpression();
        return grammarFactory.getAssignment(identifier,expr);
    }

    public Expression parseExpression() throws SyntaxError{
        Expression e = parseTerm();
        while(tkz.peek("+")||tkz.peek("-")){
            if(tkz.peek("+")){
                tkz.consume();
                e= grammarFactory.getBinaryExpr(e,"+",parseTerm());
            }
            else {
                tkz.consume();
                e= grammarFactory.getBinaryExpr(e,"-",parseTerm());
            }
        }
        return e;
    }

    public Expression parseTerm() throws SyntaxError{
        Expression e = parseFactor();
        while(tkz.peek("*") || tkz.peek("/") ||tkz.peek("%")){
            if(tkz.peek("*")){
                tkz.consume();
                e=grammarFactory.getBinaryExpr(e,"*",parseFactor());
            }
            else if(tkz.peek("/"))
            {
                tkz.consume();
                e=grammarFactory.getBinaryExpr(e,"/",parseFactor());
            }
            else if(tkz.peek("%"))
            {
                tkz.consume();
                e=grammarFactory.getBinaryExpr(e,"%",parseFactor());
            }

        }
        return e;
    }
    public Expression parseFactor() throws SyntaxError{
        Expression e = parsePower();
        while(tkz.peek("^")){
                tkz.consume();
                e= grammarFactory.getBinaryExpr(e,"^",parsePower());
        }
        return e;
    }
    public Expression parsePower() throws SyntaxError{
        Expression e;
        if(tkz.peek("(")){
            tkz.consume("(");
            e=parseExpression();
            tkz.consume(")");
        }
        else if(isNumber(tkz.peek())){
            e= grammarFactory.getNumber(Integer.parseInt(tkz.consume()));
        }
        else if(tkz.peek("virus")||tkz.peek("antibody")||tkz.peek("nearby")){
           e= parseSensor();
        }
        else if(isCharacter(tkz.peek().charAt(0))){
            e = grammarFactory.getIdentifier(tkz.consume());
        }
        else {
            throw new SyntaxError();
        }
        return e;
    }


    public Expression parseSensor() throws SyntaxError{
        Expression e;
        if(tkz.peek("virus")){
            e= grammarFactory.getSensor(tkz.consume(),null);
        }
        else if(tkz.peek("antibody")){
            e = grammarFactory.getSensor(tkz.consume(),null);
        }
        else if(tkz.peek("nearby")){
            e =grammarFactory.getSensor(tkz.consume(),parseDirection());
        }
        else{
            throw new SyntaxError();
        }
        return e;
    }


    public void evauateAll(){
        for(Statement statement:ASTtree){
            statement.eval(actor,bindings);
        }
    }
    public String prettyPrintAll(){
        StringBuilder s = new StringBuilder();
        for(Statement statement:ASTtree){
            statement.prettyPrint(s);
        }
        return s.toString();
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

package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Model.OrganismStorage;
import server.game.Game.GameData.Model.PositionMap;

import java.util.LinkedList;
import java.util.Map;

public class BlockStatment implements Statement {
    private LinkedList<Statement> statements;
    public BlockStatment(){
        this.statements = new LinkedList<>();
    }

    public void addToBlock(Statement statement){
        this.statements.add(statement);
    }
    @Override
    public void eval(Organism actor, Map<String, Integer> binding
            , PositionMap positionMap, OrganismStorage organismStorage) {
        if(!statements.isEmpty()){
            for(Statement statement:statements){
                statement.eval(actor,binding
                        ,  positionMap,  organismStorage);
            }
        }

    }

    @Override
    public void prettyPrint(StringBuilder s) {
        s.append("{");
        s.append("\n");
        for(Statement statement:statements){
            statement.prettyPrint(s);
        }
        s.append("}");

    }
    /** BlockStatement → { Statement* } */
}

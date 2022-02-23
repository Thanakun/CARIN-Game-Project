package server.game.Game.GameData.Parser.Grammars;

import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Model.OrganismStorage;
import server.game.Game.GameData.Model.PositionMap;

import java.util.Map;

public class MoveCommand implements Statement {
    private Direction direction;
    public MoveCommand(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void eval(Organism actor, Map<String,Integer> binding
            , PositionMap positionMap, OrganismStorage organismStorage) {
        int direction_value = direction.eval(actor,binding
                ,  positionMap,  organismStorage);
        direction_value%=10; //direction only not include distance
        switch (direction_value){
            case 1: actor.Move(0,1);break; //up
            case 2: actor.Move(1,1);break; //upright
            case 3: actor.Move(1,0);break; //right
            case 4: actor.Move(1,-1);break; //downright
            case 5: actor.Move(0,-1);break; //down
            case 6: actor.Move(-1,-1);break; //downleft
            case 7: actor.Move(-1,0);break; //left
            case 8: actor.Move(-1,1);break; //upleft

        }
    }

    @Override
    public void prettyPrint(StringBuilder s) {
    s.append("move ");
    direction.prettyPrint(s);
    }
    /** MoveCommand → move Direction */
}

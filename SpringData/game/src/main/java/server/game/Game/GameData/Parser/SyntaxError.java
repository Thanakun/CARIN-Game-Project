package server.game.Game.GameData.Parser;

public class SyntaxError extends RuntimeException {

    public SyntaxError(){super();}
    SyntaxError(String s){
        super(s);
    }
}

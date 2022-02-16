package Parser;

public class SyntaxError extends RuntimeException {

    public SyntaxError(){super();}
    SyntaxError(String s){
        super(s);
    }
}

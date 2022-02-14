package Parser;

public class SyntaxError extends RuntimeException {

    SyntaxError(){super();}
    SyntaxError(String s){
        super(s);
    }
}

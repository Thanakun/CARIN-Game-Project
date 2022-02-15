package Parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    Tokenizer tkz;

    String [] TestWord = {

            "anti body","down","downleft","downright","move","upleft","antibody", "down", "downleft",
            "downright", "else", "if", "left", "move",
            "nearby", "right", "shoot", "then", "up",
            "upleft", "upright", "virus", "while",
    };

    @Test
    public void setTestWord() throws SyntaxError{
        for(String test : TestWord){
            tkz = new Tokenizer(test);
            assertThrows(SyntaxError.class, ()->{
                while(tkz.hasNext()) {
                    System.out.print(tkz.consume());
                }
            },
            test);
        }
    }

}
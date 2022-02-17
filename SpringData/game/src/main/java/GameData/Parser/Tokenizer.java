package GameData.Parser;
import java.util.regex.Pattern;

public class Tokenizer implements Token{
    private String src;
    private String next;
    private int pos;

    public Tokenizer(String src){
        this.src = src;
        pos = 0;
        computeNext();
    }

    private void computeNext(){
        StringBuilder sub = new StringBuilder();

        while(pos<src.length() && ((src.charAt(pos)==' ')||(src.charAt(pos)=='\n'))){  //skip blank space
            next = " ";
            pos++;
            return;
        }
        if(pos==src.length()){  //if blank space until end of string
            next = "";
            return;
        }
        char ch = src.charAt(pos);
        if(Character.isDigit(ch)){  //start number
            sub.append(ch);
            pos++;
            for(;pos<src.length() && Character.isDigit(src.charAt(pos));pos++){
                sub.append(src.charAt(pos));
            }
        }
        else if(ch=='+'||ch=='-'||ch=='*'||ch=='/'||ch=='%'||ch=='('||ch==')'||ch=='='||ch=='{'||ch=='}'||ch=='^')  //operator
        {
            sub.append(ch);
            pos++;
        }
        else if(isCharacter(ch)){ //start identifier or reserved word
            sub.append(ch);
            pos++;
            for(;pos<src.length()
                    &&(Character.isDigit(src.charAt(pos)) || isCharacter(src.charAt(pos)));pos++){  //after first character can be a-zA-z0-9_
                sub.append(src.charAt(pos));
            }
        }
        else{
            throw  new RuntimeException("Unknown character: "+ch);
        }

        next = sub.toString();
    }

    //check if ch is a-z,A-Z,_
    private boolean isCharacter(char ch){
        return Pattern.matches("[a-zA-z]",new StringBuilder(1).append(ch));
    }

    @Override
    public boolean hasNext() {
        if(next.equals(""))return false;
        else return true;
    }

    /**Returns next token in the input stream**/
    @Override
    public String peek(){
        while(next.equals(" ")){
            computeNext();
        }
        return next;
    }
    /**Returns true if the next token is s **/
    @Override
    public boolean peek(String s){
        while(next.equals(" ")){
            computeNext();
        }
        return peek().equals(s);
    }
    /**Consumes the next token from input stream and return it**/
    @Override
    public String consume(){
        while(next.equals(" ")){
            computeNext();
        }
        String result = next;
        if(pos<src.length()){
            computeNext();
            return result;
        }
        else {   //last token
            next = "";
            return result;
        }
    }
    /**Consume the next token if it is s
     * Throw syntaxError otherwise**/
    @Override
    public void consume(String s) throws SyntaxError{
        while(next.equals(" ")){
            computeNext();
        }
        if(peek(s)){
            consume();
        }
        else{
            throw new SyntaxError();
        }
    }
}

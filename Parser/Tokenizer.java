package Parser;


public class Tokenizer implements Token {
    private static Tokenizer instance;
    private String src;
    private String next;
    private int pos;


    protected Tokenizer(String src) throws SyntaxError{
        this.src = src;
        pos = 0;
        computeNext();
    }



/***
 *
 *
 * For Any Situation Subject to Change
 *
 *
 * */
//    public static Tokenizer getInstance(){
//        if(Tokenizer.instance == null){
//            Tokenizer.instance = new Tokenizer();
//        }
//        return Tokenizer.instance;
//    }
//
//    public void initial(String src) throws SyntaxError{
//        this.src = src;
//        pos = 0;
//        computeNext();
//    }



    private void computeNext() {
        StringBuilder s = new StringBuilder();
        while (pos < src.length()) {
            char c = src.charAt(pos);
            if (Character.isDigit(c)) {  // start of number
                for (; pos < src.length() && (Character.isDigit(src.charAt(pos)) || Character.isLetter(src.charAt(pos))); pos++)
                    s.append(src.charAt(pos));
                break;
            } else if (Character.isLetter(c)) {  // start of identifier
                s.append(c);
                pos++;
                break;
            }else if (c == '+' || c== '-' || c == '(' || c == ')' || c == '*' || c == '/' || c == '%' || c== '{' || c == '}' || c == '^' || c == '=' || c=='_') {
                s.append(c);
                pos++;
                break;
            }
            else if(c == ' '){
                pos++;
            }
             else throw new SyntaxError("Unknown : " + c);
        }
        next = s.toString();

    }


    public boolean peek(String s){
        if(!src.isEmpty()) return peek().equals(s);
        return false;
    }

    public void consume(String s) throws SyntaxError {
        if(peek(s)) {
            consume();
        }else
            throw new SyntaxError();
    }


    public boolean hasNext(){
        return !next.equals("");
    }

    public String peek() {
        return next; }

    public String consume(){
        String result = next;
        computeNext();
        return result;
    }



}

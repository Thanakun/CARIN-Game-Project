package Parser;


public class Tokenizer {
    protected String src;
    protected String next;
    protected int pos;

    public Tokenizer(String src){
        this.src = src;
        pos = 0;
        computeNext();
    }

    private void computeNext() {
        StringBuilder s = new StringBuilder();
        while (pos < src.length() && Character.isWhitespace(src.charAt(pos))) pos++;  // ignore whitespace
        while (pos < src.length()) {
            char c = src.charAt(pos);
            if (Character.isDigit(c)) {  // start of number
                s.append(c);
                for (; pos < src.length() && Character.isDigit(src.charAt(pos)); pos++)
                    s.append(src.charAt(pos));
                break;
            } else if (Character.isLetter(c)) {  // start of identifier
                s.append(c);
                pos++;
                break;
            }
            else if(c == ' '){
                pos++;
            }
            else if (c == '+' || c== '-' || c == '(' || c == ')' || c == '*' || c == '/' || c == '%' || c== '{' || c == '}' || c == '^' || c == '=' || c=='_') {
                s.append(c);
                pos++;
                break;
            } else throw new SyntaxError("Unknown : " + c);
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

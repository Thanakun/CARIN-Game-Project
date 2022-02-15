package Parser;

import Parser.Grammars.Expression;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ParserTestGeneric {

    @Test
    public void GenericSimpleVariable() throws SyntaxError{
        String src = "i=1";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericPlusVariable() throws SyntaxError{
        String src = "i=i+1";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericMinusVariable() throws SyntaxError{
        String src = "i=i-1";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericDivideVariable() throws SyntaxError{
        String src = "i=i/1";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericModVariable() throws SyntaxError{
        String src = "i=i%1";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericPowerVariable() throws SyntaxError{
        String src = "i=i^1";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericMoveStatement() throws SyntaxError{
        String src = "move up";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericShootStatement() throws SyntaxError{
        String src = "shoot up";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericSenSorExpressionStatement() throws SyntaxError{
        String src = "virus = 0";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }



    @Test
    public void GenericIfStatement() throws SyntaxError{
        String src = "if ( i % 10 -3 ) then move up else move right";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericWhileStatement() throws SyntaxError{
        String src = "while ( i % 10 +1  ) move up ";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }

    @Test
    public void GenericIfCodeStatement() throws SyntaxError{
        String src = "t = t + 1  \n" +
                "virusLoc = virus\n" +
                "if (virusLoc / 10 - 1)\n" +
                "then \n" +
                "  if (virusLoc % 10 - 7) then move upleft\n" +
                "  else if (virusLoc % 10 - 6) then move left\n" +
                "  else if (virusLoc % 10 - 5) then move downleft\n" +
                "  else if (virusLoc % 10 - 4) then move down\n" +
                "  else if (virusLoc % 10 - 3) then move downright\n" +
                "  else if (virusLoc % 10 - 2) then move right\n" +
                "  else if (virusLoc % 10 - 1) then move upright\n" +
                "  else move up\n" +
                "else if (virusLoc)\n" +
                "then \n" +
                "  if (virusLoc % 10 - 7) then shoot upleft\n" +
                "  else if (virusLoc % 10 - 6) then shoot left\n" +
                "  else if (virusLoc % 10 - 5) then shoot downleft\n" +
                "  else if (virusLoc % 10 - 4) then shoot down\n" +
                "  else if (virusLoc % 10 - 3) then shoot downright\n" +
                "  else if (virusLoc % 10 - 2) then shoot right\n" +
                "  else if (virusLoc % 10 - 1) then shoot upright\n" +
                "  else shoot up\n" +
                "else \n" +
                "{\n" +
                "  dir = random % 8\n" +
                "  if (dir - 6) then move upleft\n" +
                "  else if (dir - 5) then move left\n" +
                "  else if (dir - 4) then move downleft\n" +
                "  else if (dir - 3) then move down\n" +
                "  else if (dir - 2) then move downright\n" +
                "  else if (dir - 1) then move right\n" +
                "  else if (dir) then move upright\n" +
                "  else move up\n" +
                "}\n";
        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }
    @Test
    public void GenericWhileCodeStatement() throws SyntaxError{
        String src = "t = t + 1  \n" +
                "virusLocation = v\n " +
                "while(v - 1) {\n" +
                "if(v-2) then move down\n" +
                "else if (v-3) then move upright\n" +
                "else move right\n" +
                "}";

        Map<String, Expression> binding = new LinkedHashMap<>();
        Parser parser = new Parser(src, null, binding);
        System.out.println(parser.prettyPrintAll());
    }


}
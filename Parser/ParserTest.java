package Parser;

import Parser.Grammars.Expression;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParserTest {
    public static void main(String[] args) {
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
        Parser parser = new Parser(src,null,binding);
        System.out.println(parser.prettyPrintAll());
    }
}
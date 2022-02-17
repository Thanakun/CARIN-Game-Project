package server.game.Game;

import server.game.Game.GameData.Model.Virus;
import server.game.Game.GameData.Parser.Parser;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class GameService {
    private Parser parser;
    private Virus virus;
    private Map<String, Integer> binding = new LinkedHashMap<>();

    public GameService(){
         String src = "i=10" +
                 "while (i) { move up " +
                 "i=i-1 }";
         virus = new Virus("V1","first");
         binding = new LinkedHashMap<>();
        parser = new Parser(src,virus,binding);
    }
    public String getPrint() {
        return parser.prettyPrintAll();
    }
}

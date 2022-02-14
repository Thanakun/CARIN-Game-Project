package Parser.Grammars;
import Model.Organism;
import java.util.Map;

public interface Grammar {
    void eval(Organism actor, Map<String,Integer> binding);
    void prettyPrint(StringBuilder s);
}

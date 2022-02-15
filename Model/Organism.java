package Model;
import Parser.Grammars.Direction;

import java.util.Map;

public interface Organism {
    void Show_Status();
    int HP();
    Map Position();
    void Move(Direction direction);
    void Attack(Direction direction);
}

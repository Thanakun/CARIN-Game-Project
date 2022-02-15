package Model;
import Parser.Grammars.Direction;

import java.util.Map;

public interface Organism {
    void Show_Status();
    int HP();
    String getType();
    String getId();
    int[] getPosition();  //[x,y]
    void Move(int x_change,int y_change);
    void Attack(int x_change,int y_change);
}

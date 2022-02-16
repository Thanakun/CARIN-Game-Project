package Model;
import Parser.Grammars.Direction;

import java.util.Map;

public interface Organism {
    /** Show every status of organisms
      * requires : none
      * effect : print all status : 1.ID 2.Category 3.Type 4.HP 5.Attack Damage 6.Position(x,y)
      * return : none
     **/
    void Show_Status();

    /** Get HP of organisms
      * requires : none
      * return : Integer type, HP of this organism
     **/
    int getHP();

    /** Get category of organisms
      * requires : none
      * return : String type, category of this organism
     **/
    String getCategory();

    /** Get Type of organisms
      * requires : none
      * return : String type, Type of this organism
     **/
    String getType();

    /** Get ID of organisms
      * requires : none
      * return : String type, ID of this organism
     **/
    String getId();

    /** Get Position of organisms
      * requires : none
      * return : Integer Array type, Position of this organism : x position and y position
     **/
    int[] getPosition();  //[x,y]

    /** Get category of organisms
      * requires : none
      * return : String category of this organism
     **/
    void Move(int x_change,int y_change);

    /** Get category of organisms
     * requires : none
     * return : String category of this organism
     **/
    void Attack(int x_change,int y_change);

    /** Get category of organisms
     * requires : none
     * return : String category of this organism
     **/
    void checkGame(Organism target);
}

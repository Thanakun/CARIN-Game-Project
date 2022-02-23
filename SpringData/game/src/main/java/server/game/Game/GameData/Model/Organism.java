package server.game.Game.GameData.Model;


public interface Organism {
    /** Show every status of organisms.
      * requires : none
      * effect : print all status : 1.ID 2.Category 3.Type 4.HP 5.Attack Damage 6.Position(x,y)
      * return : none
     **/
    void Show_Status();

    /** Get HP of organisms.
      * requires : none
      * return : Integer type, HP of this organism
     **/
    int getHP();

    /** Get category of organisms.
      * requires : none
      * return : String type, category of this organism
     **/
    String getCategory();

    /** Get Type of organisms.
      * requires : none
      * return : String type, Type of this organism
     **/
    int getType();

    /** Get ID of organisms.
      * requires : none
      * return : String type, ID of this organism
     **/
    String getId();

    /** Get attack damages(ATK) of organisms.
     * requires : none
     * return : Integer type, ATK of this organism
     **/
    int getATK();

    /** Get Position of organisms.
      * requires : none
      * return : Integer Array type, Position of this organism : x position and y position
     **/
    int[] getPosition();  //[x,y]

    /** Move object to some position.
      * requires : x_change and y_change is Integer
      * effect : change a position of an organism (to original position(x,y) + change(x,y))
      * return : none
     **/
    void Move(int x_change,int y_change);

    /** Shoot and make damage to organism some position.
     *  requires : x_change and y_change is Integer
     *  effect : make damage to organism at the position (original position(x,y) + change(x,y))
     *  return : none
     **/
    void Attack(int x_change,int y_change);

    /** Calculate damages which the organism has got.
     *  requires : target should be contained in allVirus or allAntivirus and damage is positive integer
     *  effect : reduce tar
     *  return : none
     **/
    void calc_damage(int damage);

    /** Update the game to current time.
     *  requires : organism and target should be contained in allVirus or allAntivirus and damage is positive integer
     *  effect : change target HP by damage from attacking
     *  return : none
     **/
    void UpdateGame(Organism organism, Organism target, int damage);


    /**set Organism's genetic code**/
    void setGeneticCode(String geneticCode);
    /**get Organism's genetic code**/
    String getGeneticCode();

    /** Get HP of organisms.
     * requires : none
     * return : Integer type, HP of this organism
     **/

    int getMax_HP();

    int gain_HP();

    /**set up status of Organism**/
    void setStatus(int HP,int atk,int gain);
}

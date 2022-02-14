package Model;

public class Antigen extends Entity implements Organism {

    int HP;
    int Attack_Damage;
    Map Position;

    public boolean Attack(){
        return true;
    }

}

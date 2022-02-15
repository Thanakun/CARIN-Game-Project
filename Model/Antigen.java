package Model;

import Parser.Grammars.Direction;

public class Antigen extends Entity implements Organism {

    int HP;
    int Attack_Damage;
    Map Position;

    public boolean Attack(){
        return true;
    }

    @Override
    public void Move(Direction direction) {

    }

    @Override
    public void Attack(Direction direction) {

    }
}

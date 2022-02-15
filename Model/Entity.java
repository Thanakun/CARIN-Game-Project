package Model;

import java.util.Map;

public class Entity implements Organism{
    protected String Id;
    protected String type;
    protected int HP;
    protected int Attack_Damage;
    protected int[] position;
    protected static PositionMap map=PositionMap.getInstance();

    public Entity(){
    }
    @Override
    public void Show_Status() {

    }

    @Override
    public int HP() {
        return 0;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public int[] getPosition() {
        return new int[]{0,0};
    }

    @Override
    public void Move(int x_change,int y_change) {

    }

    @Override
    public void Attack(int x_change,int y_change) {

    }


}

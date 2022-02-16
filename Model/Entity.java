package Model;

import java.util.Map;

public class Entity implements Organism{
    protected String Id;
    protected String category;
    protected String type;
    protected int HP;
    protected int Attack_Damage;
    protected int[] position = new int[2];
    protected static PositionMap PosiMap = PositionMap.getInstance();
    protected static OrganismController organiControl = OrganismController.getInstance();

    public Entity(){
    }
    @Override
    public void Show_Status() {
        System.out.println("ID : " + Id);
        System.out.println("Category : " + category);
        System.out.println("Type : " + type);
        System.out.println("HP : " + HP);
        System.out.println("Attack Damage : " + Attack_Damage);
        System.out.println("Position : x = " + position[0] + " y = " + position[1]);
    }

    @Override
    public int getHP() {
        return HP;
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
        return position;
    }

    @Override
    public void Move(int x_change,int y_change) {
        position[0] += x_change;
        position[2] += y_change;
        System.out.println(category + "(" + Id + ")" + " go to position : x = " + position[0] + " y = " + position[1]);
    }

    @Override
    public void Attack(int x_change,int y_change) {
        System.out.println("Shoot ");
        int[] current_position = PosiMap.getOrganismPosition(this.Id);
        current_position[0] += x_change;
        current_position[1] += y_change;
        String target_Id = PosiMap.getOrganismAt(current_position);
        Organism target = organiControl.getById(target_Id);     //get Organism by Id
        if (target.getHP() == 0) checkGame();
    }

    @Override
    public boolean checkGame() {
//        for () เดี๋ยวมาเขียนต่อ ง่วงละ 55
        return true;
    }


}

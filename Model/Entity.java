package Model;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Entity implements Organism{
    protected String Id;
    protected String category;
    protected String type;
    protected int HP;
    protected int Attack_Damage;
    protected int[] position = new int[2];
    protected static PositionMap PosiMap = PositionMap.getInstance();
    protected static OrganismController OrganiControl = OrganismController.getInstance();

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
    public String getCategory() {
        return category;
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
    public int getATK() {
        return Attack_Damage;
    }

    @Override
    public int[] getPosition() {
        return position;
    }

    @Override
    public void Move(int x_change,int y_change) {
        position[0] += x_change;
        position[1] += y_change;
        System.out.println(category + "(" + Id + ")" + " go to position : x = " + position[0] + " y = " + position[1]);
    }

    @Override
    public void Attack(int x_change,int y_change) {
        System.out.println("Shoot!!!");
        Organism organism =  OrganiControl.getById(Id);
        int[] current_position = PosiMap.getOrganismPosition(this.Id);
        current_position[0] += x_change;
        current_position[1] += y_change;
        String target_Id = PosiMap.getOrganismAt(current_position);
        Organism target = OrganiControl.getById(target_Id);     //get Organism by Id
        UpdateGame(organism ,target, getATK());
    }

    @Override
    public void calc_damage(int damage) {
        this.HP -= damage;
        if (this.HP < 0) this.HP =0;
    }

    @Override
    public void UpdateGame(Organism organism, Organism target, int damage) {
        target.calc_damage(damage);
        if (organism.getCategory().equals("Virus")) {  // organism is Virus
            ((Virus)organism).afterAttacked(damage);
            if (target.getHP() == 0) {
                OrganiControl.removeOrganism(target);
                CheckGame(target);
                ((Virus)organism).overcome();
            }
        }else if (organism.getCategory().equals("Antibody")) { // organism is Antibody
            if (target.getHP() == 0) {
                OrganiControl.removeOrganism(target);
                CheckGame(organism);
                ((Antigen)organism).overcome();
            }

        }
    }

    @Override
    public void CheckGame(Organism target) {
        if (target.getCategory().equals("Virus")) {
            if (OrganiControl.getVirus_count() == 0)
                System.out.println("You win!!!");

        }else if (target.getCategory().equals("Antibody")) {
            if (OrganiControl.getAntibody_count() == 0)
                System.out.println("You Lose!!!");
        }
    }

    @Override
    public void PlayerMoveAnti(Organism target,int x,int y) {
        if(Objects.equals(target.getCategory(), "Antibody")){
            this.position[0] = x;
            this.position[1] = y;
        }
    }

    public void firstSpawnLocationInit(){    // to spawn first time at virus constructor
        Random rand = new Random();
        int[] maxbound= PosiMap.getMapDimension();
        int x_posi = rand.nextInt(maxbound[0]+1);
        int y_posi = rand.nextInt(maxbound[1]+1);
        this.position[0] = x_posi;
        this.position[1] = y_posi;
        while(!PosiMap.updateOrganismPosition(this.Id,this.position)){  //loop until successfuly add this in map; prevent spawn at not empty position
            x_posi = rand.nextInt(maxbound[0]+1);
            y_posi = rand.nextInt(maxbound[1]+1);
            this.position[0] = x_posi;
            this.position[1] = y_posi;
        }
    }


}

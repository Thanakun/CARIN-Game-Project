package server.game.Game.GameData.Model;


public class Entity implements Organism{
    protected String Id;
    protected String category;
    protected String geneticCode="";
    protected int type;
    protected int HP;
    protected int Max_HP;
    protected int atk;
    protected int gain;
    protected int[] position = new int[2];
    protected  PositionMap positionMap;
    protected  OrganismStorage organismStorage;
    protected  VirusControl virusControl;

    public Entity(){
    }
    @Override
    public void Show_Status() {
        System.out.println("ID : " + Id);
        System.out.println("Category : " + category);
        System.out.println("Type : " + type);
        System.out.println("HP : " + HP);
        System.out.println("Attack Damage : " + atk);
        System.out.println("Position : x = " + position[0] + " y = " + position[1]);
    }

    @Override
    public int gain_HP(){
        if(HP + gain > Max_HP){
            HP = Max_HP;
        }else if(HP + gain <= Max_HP){
            HP += gain;
        }
        return HP;
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
    public int getType() {
        return type;
    }

    @Override
    public String getId() {
        return Id;
    }

    @Override
    public int getATK() {
        return atk;
    }

    @Override
    public int[] getPosition() {
        return position;
    }


    public void changeLocation(int[] dest){  //for antibody
        if(positionMap.updateOrganismPosition(Id,new int[]{
                dest[0],dest[1]
        })) //can update position on map
        {
            System.out.println(Id+" change location from "+position[0]+" "+position[1]+" to "+dest[0]+" "+dest[1]);
            position[0] =dest[0];
            position[1] = dest[1];
            //damage from move
            if(this instanceof Antibody){
                calc_damage(((Antibody) this).getMove_cost());
                System.out.println(Id+" remaining hp from move:"+HP);
                if(this.HP<=0){
                    organismDie(this);
                }
            }
        }
        else
        {
            System.out.println("cannot go there");
        }
    }


    @Override
    public synchronized void Move(int x_change,int y_change) {
        if(positionMap.updateOrganismPosition(Id,new int[]{
                position[0]+x_change,position[1]+y_change
        })) //can update position on map
        {
            position[0] += x_change;
            position[1] += y_change;
            System.out.println(category + "(" + Id + ")" + " go to position : x = " + position[0] + " y = " + position[1]);
        }
        else
        {
            System.out.println("cannot go there");
        }

    }

    @Override
    public synchronized void Attack(int x_change,int y_change) {

        Organism organism =  organismStorage.getById(Id);
        int[] current_position = positionMap.getOrganismPosition(this.Id);
        int[] usePosition = new int[2];
        usePosition[0] = current_position[0] + x_change;
        usePosition[1] = current_position[1] + y_change;
        String target_Id = positionMap.getOrganismAt(usePosition);
        Organism target = organismStorage.getById(target_Id);     //get Organism by Id
        System.out.println(Id+" is Attacking"+target_Id+" type :"+type+" x:"+x_change+" y:"+y_change+" remaining hp:"+HP);
        UpdateGame(organism ,target, getATK());
    }

    @Override
    public  void calc_damage(int damage) {
        this.HP -= damage;
        if (this.HP < 0) this.HP =0;
    }

    @Override
    public synchronized void UpdateGame(Organism organism, Organism target, int damage) {
        target.calc_damage(damage);
        if (organism.getCategory().equals("Virus")) {  // organism is Virus
            if (target.getHP() == 0) { //antibody die
                System.out.println(target.getId()+" die");
                 organismDie(target);
                virusControl.spawnNewVirusAfterkill(organism.getType());
                ((Antibody)target).dieEffect();
            }
            ((Virus)organism).afterAttacked();
        }else if (organism.getCategory().equals("Antibody")) { // organism is Antibody
            if (target.getHP() == 0) { //virus die
                organism.gain_HP();
                organismDie(target);
                ((Antibody)organism).killedVirus();
                ((Virus)target).dieEffect();
            }

        }
    }

    private synchronized void organismDie(Organism target){
        positionMap.removeOrganismPosition(target);
        organismStorage.removeOrganism(target);
    }




    @Override
    public void setGeneticCode(String geneticCode){
        this.geneticCode = geneticCode;
    }

    @Override
    public String getGeneticCode(){
        return geneticCode;
    }

    @Override
    public int getMax_HP() {
        return Max_HP;
    }

    @Override
    public  void setStatus(int init_HP,int atk,int gain){
        //every stat in crease by type if type lower type is weaker
        this.Max_HP = type*init_HP;
        this.atk = type*atk;
        this.gain = type*gain;
        this.HP = type*init_HP;
    }





}

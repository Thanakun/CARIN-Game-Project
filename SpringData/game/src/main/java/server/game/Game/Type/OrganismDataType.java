package server.game.Game.Type;

public class OrganismDataType {
    private String id;
    private String category;
    private int type;
    private int hp;
    private int max_HP;
    private int[] position;

    public OrganismDataType(){}
    public OrganismDataType(String id, String category, int type, int hp, int max_HP, int[] position) {
        this.id = id;
        this.category = category;
        this.type = type;
        this.hp = hp;
        this.max_HP = max_HP;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMax_HP() {
        return max_HP;
    }

    public void setMax_HP(int max_HP) {
        this.max_HP = max_HP;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }
}

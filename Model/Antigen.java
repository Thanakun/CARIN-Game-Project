package Model;



public class Antigen extends Entity implements Organism {

    public Antigen(String id){ // for antivirus Id format is "A??????" ?is any number or character
        this.Id = id;
        this.category = new String("Antibody");
        this.type = "first"; // types (3) : first , second , third
        this.HP = 100;
        this.position[0] = 0;
        this.position[1] = 0;
        organiControl.addOrganism(this);
    }

}

package Model;



public class Antigen extends Entity implements Organism {

    public Antigen(String id){
        this.Id = id;
        this.type = "Antibody";
    }

}

package server.game.Game.Type;



public class AntibodyReq {
    private String targetId;//target antibody's id
    private int[] location  ;
    private String genetic;

    public AntibodyReq(String id,int[] loc,String genetic){
        this.targetId = id;
        this.location =loc;
        this.genetic = genetic;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public String getGenetic() {
        return genetic;
    }

    public void setGenetic(String genetic) {
        this.genetic = genetic;
    }
}

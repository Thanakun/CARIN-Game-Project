package server.game.Game.Type;



public class AntibodyReq implements Request {
    private String targetId;//target antibody's id
    private int type;
    private int[] location  ;
    private String cmd; // buy|move|setup
    private String genetic;

    public AntibodyReq(){}
    public AntibodyReq(String id,int type,int[] loc,String cmd,String genetic){
        this.targetId = id;
        this.type = type;
        this.location =loc;
        this.cmd = cmd;
        this.genetic = genetic;
    }

    public String getGenetic() {
        return genetic;
    }

    public void setGenetic(String genetic) {
        this.genetic = genetic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    @Override
    public String getRequestType() {
        return "AntibodyRequest";
    }
}

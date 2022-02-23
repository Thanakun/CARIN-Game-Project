package server.game.Game.Type;

public class MenuReq implements Request{
    private String wanted_state;

    public MenuReq(){}
    public MenuReq(String wanted_state) {
        this.wanted_state = wanted_state;
    }

    public String getWanted_state() {
        return wanted_state;
    }

    public void setWanted_state(String wanted_state) {
        this.wanted_state = wanted_state;
    }

    @Override
    public String getRequestType() {
        return "MenuRequest";
    }
}

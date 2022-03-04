package server.game.Game.Type;

public class TimeReq implements Request{
    private String command;
    private int value;

    public TimeReq(){}
    public TimeReq(String command, int value) {
        this.command = command;
        this.value = value;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String getRequestType() {
        return "TimeRequest";
    }
}

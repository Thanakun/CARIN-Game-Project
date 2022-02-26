package server.game.Game.Type;


public class TimerDataType {
    private int time_count;
    private int time_pass;

    public TimerDataType(){}
    public TimerDataType(int time_count, int time_pass) {
        this.time_count = time_count;
        this.time_pass = time_pass;
    }

    public int getTime_count() {
        return time_count;
    }

    public void setTime_count(int time_count) {
        this.time_count = time_count;
    }

    public int getTime_pass() {
        return time_pass;
    }

    public void setTime_pass(int time_pass) {
        this.time_pass = time_pass;
    }
}

package server.game.Game.Type;


public class TimerDataType {
    private int time_count;
    private int time_pass;
    private double speed_multiplier;

    public TimerDataType(){}
    public TimerDataType(int time_count, int time_pass,double speed_multiplier) {
        this.time_count = time_count;
        this.time_pass = time_pass;
        this.speed_multiplier = speed_multiplier;
    }

    public double getSpeed_multiplier() {
        return speed_multiplier;
    }

    public void setSpeed_multiplier(double speed_multiplier) {
        this.speed_multiplier = speed_multiplier;
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

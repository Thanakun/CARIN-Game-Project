package server.game.Game.GameData.Model;

import org.springframework.stereotype.Component;
import server.game.Game.Type.Request;
import server.game.Game.Type.TimeReq;


@Component
public class Timer extends Thread {
    /** Time Unit */
    private static Timer instance;
    private  double[] time_speed_multiplier;
    private int speed_level;
    private  int time_per_unit;
    private  int time_count;
    private  boolean isActive;
    private  int time_pass;

    private Timer(){
        isActive = false; //default when create instance is off
        time_count =0; //counter increase when each time unit pass
        time_per_unit = 1000; // in ms
        time_speed_multiplier = new double[]{0.25,0.5,1,2,3};
        speed_level = 2; //default multiplier is 1
        time_pass = (int)(time_per_unit/time_speed_multiplier[speed_level]); //total time pass in 1 time unit
        //  if time multiplier high time will pass faster , time per unit decrease

    }

    public static Timer getInstance(){
        if(instance==null){
            instance = new Timer();
        }
        return instance;
    }



    @Override
    public void run(){
        try{
            while(true){
                 time_pass = (int)(time_per_unit/time_speed_multiplier[speed_level]);
                if(isActive){

                    sleep(time_pass);
                    if(isActive) {
                        ++time_count;
                   }
                }
                else{
                  //  System.out.println("pausing");
                   sleep(10); //wait for resume
                }
            }

        }
        catch (InterruptedException e){
            System.out.println("end timer :"+this.getName());
        }
    }

    public void resetTime(){
        isActive = false;
        time_count =0;
        time_per_unit = 2000;
        speed_level =2; //default multiplier is 1
        time_pass = (int)(time_per_unit/time_speed_multiplier[speed_level]);

    }
    public boolean getTimerStatus(){
        return this.isActive;
    }
    public int getTimePass(){
        return time_pass;
    }
    public int getTime_count(){
        return time_count;
    }
    public double getSpeedMultiplier(){
        return time_speed_multiplier[speed_level];
    }

    public void off(){
        isActive = false;
    }
    public void on(){
        isActive = true;
    }


    public void setTimePerUnit(int amount){  //set time per time unit
        time_per_unit = amount;
    }

    public void increaseSpeed(){
        if(speed_level<4){
            speed_level++;
        }
    }
    public void decreaseSpeed(){
        if(speed_level>0){
            speed_level--;
        }
    }



    public void computeSpeedInput(Request req) {
        System.out.println("computing time request");
        TimeReq newReq = (TimeReq) req;
        String command = newReq.getCommand();
        if(command.equals("increaseSpeed")){
            increaseSpeed();
        }
        if(command.equals("decreaseSpeed")){
            decreaseSpeed();
        }
    }
}

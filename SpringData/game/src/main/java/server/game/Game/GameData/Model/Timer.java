package server.game.Game.GameData.Model;

import org.springframework.stereotype.Component;


@Component
public class Timer extends Thread {
    /** Time Unit */
    private static Timer instance;
    private  int time_speed_multiplier;
    private  int time_per_unit;
    private  int time_count;
    private  boolean isActive;
    private  int time_pass;

    private Timer(){
        isActive = false; //default when create instance is off
        time_count =0; //counter increase when each time unit pass
        time_per_unit = 1000; // in ms
        time_pass = (int)(time_per_unit/(double)time_speed_multiplier); //total time pass in 1 time unit
        time_speed_multiplier = 1; //  if time multiplier high time will pass faster , time per unit decrease

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
                 time_pass = (int)(time_per_unit/(double)time_speed_multiplier);
                if(isActive){

                    sleep(time_pass);
                    ++time_count;
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
        time_pass = (int)(time_per_unit/(double)time_speed_multiplier);
        time_speed_multiplier = 1;
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

    public void off(){
        isActive = false;
    }
    public void on(){
        isActive = true;
    }

    public boolean decreaseMultiplier(){    //return true if can decrease time speed
        if(time_speed_multiplier<=1){
            return false;
        }
        else{
            time_speed_multiplier--;
            return true;
        }
    }

    public boolean increaseMultiplier(){    //return true if can increase time speed
        if(time_speed_multiplier>=3){
            return false;
        }
        else{
            time_speed_multiplier++;
            return true;
        }
    }

    public void setTimePerUnit(int amount){  //set time per time unit
        time_per_unit = amount;
    }


}

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
                    time_count++;
                    System.out.println(this.getName()+" "+time_count+" speed:"+time_speed_multiplier);
                    sleep(time_pass);
                }
                else{
                    System.out.println("pausing");
                    sleep(100); //wait for resume
                }
            }

        }
        catch (InterruptedException e){
            System.out.println("end timer :"+this.getName());
        }
    }

    public void resetTime(){
        time_count = 0;
    }
    public int getTimePass(){
        return time_pass;
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

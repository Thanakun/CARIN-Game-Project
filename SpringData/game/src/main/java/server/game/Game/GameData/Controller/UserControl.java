package server.game.Game.GameData.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.game.Game.GameData.Model.AntibodyControl;
import server.game.Game.GameData.Model.Player;
import server.game.Game.GameData.Model.Timer;
import server.game.Game.Type.Request;

import java.util.Stack;

@Component
public class UserControl extends Thread{
    private  static UserControl instance;
    @Autowired
    private Player player;
    @Autowired
    private Menu menu;
    @Autowired
    private Timer timer;


    private Stack<Request> requests;

    private UserControl(){
        requests = new Stack<>();
    }
    public static UserControl getInstance(){
        if(instance==null){
            instance = new UserControl();
        }
        return instance;
    }

    public void addRequset(Request req){
        this.requests.add(req);
    }



    @Override
    public void run(){
        try{
            while(true){
               if(!this.requests.isEmpty()){
                   Request req = requests.pop();
                   if(req.getRequestType().equals("AntibodyRequest")){
                       player.computeAntibodyInput(req);
                   }
                   else if(req.getRequestType().equals("MenuRequest")){
                        menu.computeGameState(req);
                   }
                   else if(req.getRequestType().equals("TimeRequest")){
                       System.out.println("computing time requset");
                        timer.computeSpeedInput(req);
                   }
                   else{
                       throw new RuntimeException("wrong request type");
                   }
               }
              //  System.out.println("Waiting for request");
                sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }

    }
}
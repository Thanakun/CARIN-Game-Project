package server.game.Game;

import org.springframework.beans.factory.annotation.Autowired;
import server.game.Game.GameData.Controller.Menu;
import server.game.Game.GameData.Controller.UserControl;
import server.game.Game.GameData.Model.*;
import server.game.Game.GameData.Parser.Parser;
import org.springframework.stereotype.Service;
import server.game.Game.Type.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class GameService {
    private final List<Request> reqLog = new LinkedList<>();
    @Autowired
    private UserControl userControl;
    @Autowired
    private PositionMap positionMap;
    @Autowired
    private OrganismStorage organismStorage;
    @Autowired
    private Timer timer;
    @Autowired
    private Player player;
    @Autowired
    private Menu menu;


    public List<Request> getAllReq(){
        return  reqLog;
    }
    //get
    public Timer getTimer(){
        return timer;
    }
    public int getCredit(){
        return player.getCurrentCredit();
    }
    public Map<String,Organism> getAntibody(){
        return organismStorage.getallAntibody();
    }
    public Map<String,Organism> getVirus(){
        return organismStorage.getallVirus();
    }
    public Map<String,int[]> getPosition(){
        return positionMap.getPositionMap();
    }
    public String getGameState(){
        return menu.getGameState();
    }
    public int[] getDimension(){return positionMap.getMapDimension();}

    public GameDataType getGameData() {
        return new GameDataType(getTimer(),getCredit(),getGameState(),getDimension(),getVirus(),getAntibody()
        ,organismStorage.getVirusAmount(),organismStorage.getAntibodyAmount());
    }

    public Request saveReq(Request req){
        if(req instanceof AntibodyReq){
            AntibodyReq newReq = new AntibodyReq(((AntibodyReq)req).getTargetId()
                    ,((AntibodyReq)req).getType(),((AntibodyReq)req).getLocation()
                    ,((AntibodyReq)req).getCmd(),((AntibodyReq)req).getGenetic());
            userControl.addRequset(req);
            reqLog.add(newReq);
            return newReq;
        }
        else if(req instanceof MenuReq){
            MenuReq newReq = new MenuReq(((MenuReq)req).getWanted_state());
            userControl.addRequset(req);
            reqLog.add(newReq);
            return newReq;
        }
        else if(req instanceof TimeReq){
            TimeReq newReq = new TimeReq(((TimeReq)req).getCommand(),((TimeReq)req).getValue());
            userControl.addRequset(req);
            reqLog.add(newReq);
            return newReq;
        }
        else return null;
    }


}

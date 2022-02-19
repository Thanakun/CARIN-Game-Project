package server.game.Game;

import org.springframework.beans.factory.annotation.Autowired;
import server.game.Game.GameData.Controller.Menu;
import server.game.Game.GameData.Controller.UserControl;
import server.game.Game.GameData.Model.Virus;
import server.game.Game.GameData.Parser.Parser;
import org.springframework.stereotype.Service;
import server.game.Game.Type.AntibodyReq;
import server.game.Game.Type.MenuReq;
import server.game.Game.Type.Request;

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

    public List<Request> getAllReq(){
        return  reqLog;
    }

    public Request save(Request req){
        if(req instanceof AntibodyReq){
            AntibodyReq newReq = new AntibodyReq(((AntibodyReq)req).getTargetId()
                    ,((AntibodyReq)req).getType(),((AntibodyReq)req).getLocation()
                    ,((AntibodyReq)req).getGenetic());
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
        else return null;
    }
}

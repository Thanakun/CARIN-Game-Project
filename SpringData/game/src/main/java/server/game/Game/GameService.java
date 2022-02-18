package server.game.Game;

import server.game.Game.GameData.Model.Virus;
import server.game.Game.GameData.Parser.Parser;
import org.springframework.stereotype.Service;
import server.game.Game.Type.AntibodyReq;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class GameService {
    private final List<AntibodyReq> allReq = new LinkedList<>();

    public List<AntibodyReq> getAllReq(){
        return  allReq;
    }

    public AntibodyReq save(AntibodyReq req){
        AntibodyReq newReq = new AntibodyReq(req.getTargetId(),req.getLocation(),req.getGenetic());
        allReq.add(newReq);
        return newReq;
    }
}

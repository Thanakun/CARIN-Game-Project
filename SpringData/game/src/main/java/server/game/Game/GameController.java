package server.game.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.game.Game.GameData.Model.Antibody;
import server.game.Game.Type.AntibodyReq;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/input/antibody")
    public ResponseEntity<List<AntibodyReq>> getAllReq(){
        return new ResponseEntity(gameService.getAllReq(),HttpStatus.OK);
    }

    @PostMapping(value="/input/antibody")
    public ResponseEntity<AntibodyReq> createAntibody(@RequestBody AntibodyReq req){

        AntibodyReq newReq = gameService.save(req);

        return  ResponseEntity
                .created(URI.
                        create(String.format("/posted")))
                .body(newReq);
    }

    @PutMapping("/input/antibody")
    public void updateAntibody(){

    }

}

package server.game.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.game.Game.Type.AntibodyReq;
import server.game.Game.Type.MenuReq;
import server.game.Game.Type.Request;
import server.game.Game.Type.TimeReq;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:3000/")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }
    //get
    @GetMapping("/get/requests")
    public ResponseEntity<List<Request>> getAllReq(){   //for test
        return new ResponseEntity(gameService.getAllReq(),HttpStatus.OK);
    }

    @GetMapping("/get/gameData")
    public ResponseEntity<String> getGameData(){
        return new ResponseEntity(gameService.getGameData(),HttpStatus.OK);
    }




//post
    @PostMapping(value="/input/antibody")
    public ResponseEntity<Request> createAntibody(@RequestBody AntibodyReq req){    //get input from react

        Request newReq = gameService.saveReq(req);

        return  ResponseEntity
                .created(URI.
                        create(String.format("/antibody")))
                .body(newReq);
    }

    @PostMapping(value = "/input/state")
    public ResponseEntity<Request> requestState(@RequestBody MenuReq req){
        Request newReq = gameService.saveReq(req);
        return  ResponseEntity
                .created(URI.
                        create(String.format("/state")))
                .body(newReq);
    }

    @PostMapping(value = "/input/time")
    public ResponseEntity<Request> requestTimer(@RequestBody TimeReq req){
        Request newReq = gameService.saveReq(req);
        return  ResponseEntity
                .created(URI.
                        create(String.format("/time")))
                .body(newReq);
    }


}

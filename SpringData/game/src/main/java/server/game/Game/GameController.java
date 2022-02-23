package server.game.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.game.Game.GameData.Model.Antibody;
import server.game.Game.GameData.Model.Organism;
import server.game.Game.GameData.Model.Timer;
import server.game.Game.Type.AntibodyReq;
import server.game.Game.Type.MenuReq;
import server.game.Game.Type.Request;


import java.net.URI;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<List<Request>> getAllReq(){
        return new ResponseEntity(gameService.getAllReq(),HttpStatus.OK);
    }
    @GetMapping("/get/time")
    public ResponseEntity<Timer> getTimer(){
        return new ResponseEntity(gameService.getTimer(),HttpStatus.OK);
    }
    @GetMapping("/get/credit")
    public ResponseEntity<Integer> getCredit(){
        return new ResponseEntity(gameService.getCredit(),HttpStatus.OK);
    }
    @GetMapping("/get/virus")
    public ResponseEntity<Map<String, Organism>> getVirus(){
        return new ResponseEntity(gameService.getVirus(),HttpStatus.OK);
    }
    @GetMapping("/get/antibody")
    public ResponseEntity<Map<String,Organism>> getAntibody(){
        return new ResponseEntity(gameService.getAntibody(),HttpStatus.OK);
    }
    @GetMapping("/get/position")
    public ResponseEntity<Map<String,int[]>> getPosition(){
        return new ResponseEntity(gameService.getPosition(),HttpStatus.OK);
    }
    @GetMapping("/get/state")
    public ResponseEntity<String> getGameState(){
        return new ResponseEntity(gameService.getGameState(),HttpStatus.OK);
    }

    @GetMapping("/get/gameData")
    public ResponseEntity<String> getGameData(){
        return new ResponseEntity(gameService.getGameData(),HttpStatus.OK);
    }




//post
    @PostMapping(value="/input/antibody")
    public ResponseEntity<Request> createAntibody(@RequestBody AntibodyReq req){    //get input from web

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


}

//package server.game.Game;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(path="/")
//public class GameController {
//    private final GameService gameService;
//
//    @Autowired
//    public GameController(GameService gameService){
//        this.gameService = gameService;
//    }
//
//    @GetMapping
//   // @RequestMapping(path = "/hello")
//    public String getHello(){
//        return gameService.getHello();
//    }
//}

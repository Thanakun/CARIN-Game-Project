package Model;

import java.util.Arrays;

public class MainGame {
    public static void main(String[] args) {
        Player player = new Player();
        Organism K = new Antigen("0");
        K.Show_Status();
        System.out.println(K.getCategory());
        System.out.println(Arrays.toString(K.getPosition()));
        player.moveAntigen(K,"0",11,11);
        System.out.println(Arrays.toString(K.getPosition()));
    }

}

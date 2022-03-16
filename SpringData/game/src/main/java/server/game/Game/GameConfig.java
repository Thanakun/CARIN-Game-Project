package server.game.Game;

import org.springframework.stereotype.Component;
import server.game.Game.GameData.Controller.UserControl;
import server.game.Game.GameData.Model.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Scanner;

@Component
public class GameConfig {
    private static GameConfig instance;

    //config variables with default setting
    private int m=10;
    private int n=10;
    private float virus_rate= (float) 0.2;
    private int max_virus_amount=10;
    private int init_credits=1000;
    private int antibody_cost=100;
    private int virus_hp=100;
    private int antibody_hp=100;
    private int virus_atk=10;
    private int virus_gain=3;
    private int antibody_atk=20;
    private int antibody_gain=50;
    private int antibody_move_cost=1;
    private int killed_virus_credit_gain=100;

    private GameConfig(){
        readFromConfig();
    }

    public static GameConfig getInstance() {
        if(instance==null){
            instance = new GameConfig();
        }
        return instance;
    }

    public void readFromConfig(){
        Path read_path = Path.of("SpringData/game/src/main/resources/config.txt");
        Charset charset = StandardCharsets.US_ASCII;

        try(BufferedReader reader = Files.newBufferedReader(read_path,charset);
            Scanner scanner = new Scanner(reader);
        ){
            m=scanner.nextInt(); n=scanner.nextInt();
            virus_rate = scanner.nextFloat();
            init_credits=scanner.nextInt(); antibody_cost = scanner.nextInt();
            virus_hp = scanner.nextInt(); antibody_hp = scanner.nextInt();
            virus_atk = scanner.nextInt(); virus_gain = scanner.nextInt();
            antibody_atk = scanner.nextInt(); antibody_gain = scanner.nextInt();
            antibody_move_cost = scanner.nextInt();
            killed_virus_credit_gain = scanner.nextInt();
            // variable that not in config file from project specification
            max_virus_amount = 30;
            System.out.println("use Config file");
        }
        catch(NoSuchFileException e){
            System.out.println("Config file not found,Use default setting");
            System.out.println(e);
        }catch(IOException read_write_err){
            System.out.println(read_write_err);
        }
    }

    public int getM() {
        return m;
    }

    public int getN() {
        return n;
    }

    public float getVirus_rate() {
        return virus_rate;
    }

    public int getMax_virus_amount() {
        return max_virus_amount;
    }

    public int getInit_credits() {
        return init_credits;
    }

    public int getAntibody_cost() {
        return antibody_cost;
    }

    public int getVirus_hp() {
        return virus_hp;
    }

    public int getAntibody_hp() {
        return antibody_hp;
    }

    public int getVirus_atk() {
        return virus_atk;
    }

    public int getVirus_gain() {
        return virus_gain;
    }

    public int getAntibody_atk() {
        return antibody_atk;
    }

    public int getAntibody_gain() {
        return antibody_gain;
    }

    public int getAntibody_move_cost() {
        return antibody_move_cost;
    }

    public int getKilled_virus_credit_gain() {
        return killed_virus_credit_gain;
    }
}

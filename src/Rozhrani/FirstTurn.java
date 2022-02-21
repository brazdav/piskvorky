package Rozhrani;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public interface FirstTurn{
    AtomicBoolean player1_turn = new AtomicBoolean(true);
    AtomicBoolean server_turn = new AtomicBoolean(true);
    Random random = new Random();
    JLabel textfield = new JLabel();

    static void firstTurn(){

        if(random.nextInt(2)==1){
            player1_turn.set(true);
            textfield.setText("X turn");
        }
        else{
            player1_turn.set(false);
            textfield.setText("O turn");
        }
    }

    static void firstTurnLan(){
        if(random.nextInt(2)==1){
            server_turn.set(false);
        }
        else{
            server_turn.set(true);
        }
    }

}

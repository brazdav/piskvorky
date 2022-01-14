import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

interface FirstTurn{
    AtomicBoolean player1_turn = new AtomicBoolean(true);
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

}

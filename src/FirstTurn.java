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
    default void vykresleni(JButton button) {
        if (player1_turn.get()) {
            if (button.getText().equals("")) {
                button.setForeground(new Color(0, 0, 0));
                button.setText("X");
                player1_turn.set(false);
                textfield.setText("O turn");
                button.setBackground(new Color(245, 191, 191));
            }
        } else if (button.getText().equals("")) {
            button.setForeground(new Color(0, 0, 0));
            button.setText("O");
            player1_turn.set(true);
            textfield.setText("X turn");
            button.setBackground(new Color(245, 191, 191));
        }
    }

    /*default void vykresleniLan(JButton button) {
        if (turn) {
            if (button.getText().equals("")) {
                button.setForeground(new Color(0, 0, 0));
                button.setText("X");
                turn = false;
                textfield.setText("O turn");
                button.setBackground(new Color(245, 191, 191));
            }
        } else if (button.getText().equals("")) {
            button.setForeground(new Color(0, 0, 0));
            button.setText("O");
            turn = true;
            textfield.setText("X turn");
            button.setBackground(new Color(245, 191, 191));
        }
    }*/
}

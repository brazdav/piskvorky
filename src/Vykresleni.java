import javax.swing.*;
import java.awt.*;

public class Vykresleni implements FirstTurn{
    private String x;
    private String o;

    public void poradi(){
        if (player1_turn.get()){
            x = "server";
            o = "client";
        }
        else {
            x = "client";
            o = "server";
        }
    }
    public void vykresleni(JButton button) {
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

    public void vykresleniLan(JButton button) {
        if (x == "server") {
            if (button.getText().equals("")) {
                button.setForeground(new Color(0, 0, 0));
                button.setText("X");

                textfield.setText("O turn");
                button.setBackground(new Color(245, 191, 191));
            }
        } else if (x == "client") {
            if (button.getText().equals("")) {
                button.setForeground(new Color(0, 0, 0));
                button.setText("O");
                textfield.setText("X turn");
                button.setBackground(new Color(245, 191, 191));
            }
        }
    }
}

package Spousteni_hry;

import Rozhrani.FirstTurn;
import Tvoreni_menu.Piskvorky;

import javax.swing.*;
import java.awt.*;

public class Vykresleni implements FirstTurn {

    public void vykresleni(JButton button) {
        if (player_turn.get()) {
            if (button.getText().equals("")) {
                button.setForeground(new Color(0, 0, 0));
                button.setText("X");
                textfield.setText("O turn");
            }
        } else if (button.getText().equals("")) {
            button.setForeground(new Color(0, 0, 0));
            button.setText("O");
            textfield.setText("X turn");
        }
    }

    public void vykresleniLan(JButton button) {
        if (player1_turn.get()) {
            if (button.getText().equals("")) {
                button.setForeground(new Color(0, 0, 0));
                button.setText("X");
                player1_turn.set(false);
                textfield.setText("O turn");
            }
        } else if (button.getText().equals("")) {
            button.setForeground(new Color(0, 0, 0));
            button.setText("O");
            player1_turn.set(true);
            textfield.setText("X turn");
        }
    }
}
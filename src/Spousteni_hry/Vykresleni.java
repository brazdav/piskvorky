package Spousteni_hry;

import Rozhrani.FirstTurn;
import Tvoreni_menu.Piskvorky;

import javax.swing.*;
import java.awt.*;

public class Vykresleni implements FirstTurn {

    public void vykresleni(JButton button, Piskvorky piskvorky) {
        if (player_turn.get()) {
            if (button.getText().equals("")) {
                button.setForeground(new Color(0, 0, 0));
                button.setText("X");
                textfield.setText("O turn");
                player_turn.set(false);
                piskvorky.utok(piskvorky.list, "O");
                piskvorky.ai = true;
            }
        } else if (button.getText().equals("")) {
            button.setForeground(new Color(0, 0, 0));
            button.setText("O");
            textfield.setText("X turn");
            player_turn.set(true);
            piskvorky.ai = false;
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
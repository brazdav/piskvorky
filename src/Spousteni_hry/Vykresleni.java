package Spousteni_hry;

import Rozhrani.FirstTurn;
import Tvoreni_menu.Piskvorky;

import javax.swing.*;
import java.awt.*;
/**
 * Třída Vykreslení implementuje rozhraní FirstTurn
 * Obsahuje 2 metody vykreslení a vykresleniLan, obě dělají skoro to samé akorát jedna je pro LAN hru
 * @author Vojtěch Brázda
 * @version 1.0.0
 *
 * */
public class Vykresleni implements FirstTurn {
    /**
     * Metoda vykreslení nám nastavuje, kdo bude hrát v daný moment na label, který je nad hracím polem a na tlačítko nastaví text "X" nebo "O"
     * Nastavuje barvu popřední (znaku) na tlačítku na bílou
     * Také zde používáme metodu utok z AI
     * @param button  JButton
     * @param piskvorky Konstruktor třídy Piskvorky
     *
     * */
    public void vykresleni(JButton button, Piskvorky piskvorky) {
        if (player_turn.get()) {
            if (button.getText().equals("")) {
                button.setForeground(new Color(0, 0, 0));
                button.setText("X");
                textfield.setText("O turn");
                player_turn.set(false);
                piskvorky.obrana(piskvorky.list, "X");
                //piskvorky.utok(piskvorky.list, "O");
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
    /**
     * Metoda vykreslení nám nastavuje, kdo bude hrát v daný moment na label, který je nad hracím polem a na tlačítko nastaví text "X" nebo "O"
     * Nastavuje barvu popřední (znaku) na tlačítku na bílou
     * @param button  JButton
     *
     *
     * */
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
package AI;

import Rozhrani.FirstTurn;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;


public class AI1 extends JButton implements FirstTurn {
    Random random = new Random();
    private String znakAi;
    private int count;
    public void obrana (String znak, int poradi, ArrayList<JButton> buttons, String strana, int oRada, int xRada){

        if (znak.equals("X")) {
            this.znakAi = "O";
            count = xRada;
        }
        else {
            this.znakAi = "X";
            count = oRada;
        }
        if (count == 1){
            switch (strana){
                case "leva": poradi--;
                    zapis(znakAi,poradi,buttons);
                    break;
                case "prava": poradi++;
                    zapis(znakAi,poradi,buttons);
                    break;
            }
            if (znakAi.equals("X"))
                textfield.setText("O turn");
            else
                textfield.setText("X turn");
        }
        else if (count == 2){
            switch (strana){
                case "leva": poradi=poradi-2;
                    zapis(znakAi,poradi,buttons);
                    break;
                case "prava": poradi++;
                    zapis(znakAi,poradi,buttons);
                    break;
            }
        }
        else{
            utok(znak,poradi,buttons);
        }
    }
    private void utok(String znak, int poradi, ArrayList<JButton> buttons){

    }
    private boolean zapis(String znak, int poradi, ArrayList<JButton> buttons) {
            if (poradi >= 0 && poradi <= 254 && buttons.get(poradi).getText().equals("")) {
                buttons.get(poradi).setText(znak);
                return true;
            }
            else{
                return false;
        }
    }
}

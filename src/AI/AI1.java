package AI;

import Rozhrani.FirstTurn;
import Spousteni_hry.Vykresleni;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;


public class AI1 extends JButton implements FirstTurn {
    int pocet = 0;
    Random random = new Random();
    private String znakAi;
    public void obrana (String znak, int poradi, ArrayList<JButton> buttons, String strana){
        pocet ++;
        System.out.println(poradi);
        if (znak.equals("X"))
            this.znakAi = "O";
        else
            this.znakAi = "X";
        if (pocet == 2){
            switch (strana){
                case "leva": poradi--;
                buttons.get(poradi).setText(znakAi);
            }
            if (znakAi.equals("X"))
                textfield.setText("O turn");
            else
                textfield.setText("X turn");
        }
    }
}

package AI;

import Rozhrani.FirstTurn;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;


public class AI1 extends JButton implements FirstTurn {
    Random random = new Random();
    private String znakAi;
    private int count;
    private boolean zapsano;
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
                    zapsano = zapis(znakAi,poradi,buttons);
                    if(!zapsano){
                        poradi = poradi+4;
                        zapis2(znakAi,poradi,buttons);
                        System.out.println("zapis2");
                    }
                    break;
                case "prava": poradi++;
                    zapsano = zapis(znakAi,poradi,buttons);
                    if(!zapsano){
                        poradi = poradi-4;
                        zapis2(znakAi,poradi,buttons);
                        System.out.println("funguje");
                    }
                    break;
                case "horni": poradi -=15;
                    zapsano = zapis(znakAi,poradi,buttons);
                    if(!zapsano){
                        poradi = poradi+60;
                        zapis2(znakAi, poradi, buttons);
                        System.out.println("hore more");
                    }
                    break;
                case "dolni": poradi +=15;
                    zapsano = zapis(znakAi,poradi,buttons);
                    if(!zapsano){
                        poradi = poradi-60;
                        zapis2(znakAi, poradi, buttons);
                        System.out.println("dole more");
                    }
                    break;
                case "pravaHorni": poradi -=14;
                    zapsano = zapis(znakAi,poradi,buttons);
                    if(!zapsano){
                        poradi = poradi+56;
                        zapis2(znakAi, poradi, buttons);
                        System.out.println("prava hore more");
                    }
                    break;
                case "pravaDolni": poradi +=16;
                    zapsano = zapis(znakAi,poradi,buttons);
                    if(!zapsano){
                        poradi = poradi-64;
                        zapis2(znakAi, poradi, buttons);
                        System.out.println("prava dole more");
                    }
                    break;
                case "levaHorni": poradi -=16;
                    zapsano = zapis(znakAi,poradi,buttons);
                    if(!zapsano){
                        poradi = poradi+64;
                        zapis2(znakAi, poradi, buttons);
                        System.out.println("leva hore more");
                    }
                    break;
                case "levaDolni": poradi +=14;
                    zapsano = zapis(znakAi,poradi,buttons);
                    if(!zapsano){
                        poradi = poradi-56;
                        zapis2(znakAi, poradi, buttons);
                        System.out.println("leva hore more");
                    }
                    break;
            }
            if (znakAi.equals("X"))
                textfield.setText("O turn");
            else
                textfield.setText("X turn");
        }
        /*else if (count == 2){
            switch (strana){
                case "leva": poradi=poradi-2;
                    zapis(znakAi,poradi,buttons);
                    break;
                case "prava": poradi=poradi+2;
                    zapis(znakAi,poradi,buttons);
                    break;
            }
        }*/
        else{
            utok(znak,poradi,buttons);
        }
    }
    private void utok(String znak, int poradi, ArrayList<JButton> buttons){

    }

    private boolean zapis2(String znak, int poradi,ArrayList<JButton> buttons){
            if (poradi >= 0 && poradi <= 254 && buttons.get(poradi).getText().equals("")) {
                buttons.get(poradi).setText(znak);
                return true;
             }
            else{
                return false;
             }
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

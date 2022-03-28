package AI;

import Rozhrani.FirstTurn;
import Uprava_tlacitka.MyButtons;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class AI1 extends JButton implements FirstTurn {
    Random random = new Random();
    private MyButtons obj;
    private String znakAi;
    private int count;
    private boolean zapsano;
    public int utok;
    public ArrayList<JButton> list = new ArrayList();
    private String check = "ai";
    private boolean found = false;
    public boolean ai = false;
    final int LEFT = -1;
    final int RIGHT = 1;
    final int UP = -15;
    final int DOWN = 15;
    public void obrana (String znak, int poradi, ArrayList<JButton> buttons, String strana, int oRada, int xRada){
        if (znak.equals("X")) {
            this.znakAi = "O";
            count = xRada;
        }
        else {
            this.znakAi = "X";
            count = oRada;
        }
        if (count == 1 && ai){
            switch (strana){
                case "leva": poradi--;
                    zapsano = zapis(poradi,buttons);
                    if(!zapsano){
                        poradi = poradi+4;
                        zapis(poradi,buttons);
                    }
                    break;
                case "prava": poradi++;
                    zapsano = zapis(poradi,buttons);
                    if(!zapsano){
                        poradi = poradi-4;
                        zapis(poradi,buttons);
                    }
                    
                    break;
                case "horni": poradi -=15;
                    zapsano = zapis(poradi,buttons);
                    if(!zapsano){
                        poradi = poradi+60;
                        zapis(poradi, buttons);
                    }
                    
                    break;
                case "dolni": poradi +=15;
                    zapsano = zapis(poradi,buttons);
                    if(!zapsano){
                        poradi = poradi-60;
                        zapis(poradi, buttons);
                    }
                    
                    break;
                case "pravaHorni": poradi -=14;
                    zapsano = zapis(poradi,buttons);
                    if(!zapsano){
                        poradi = poradi+56;
                        zapis(poradi, buttons);
                    }
                    
                    break;
                case "pravaDolni": poradi +=16;
                    zapsano = zapis(poradi,buttons);
                    if(!zapsano){
                        poradi = poradi-64;
                        zapis(poradi, buttons);
                    }
                    
                    break;
                case "levaHorni": poradi -=16;
                    zapsano = zapis(poradi,buttons);
                    if(!zapsano){
                        poradi = poradi+64;
                        zapis(poradi, buttons);
                    }
                    break;
                case "levaDolni": poradi +=14;
                    zapsano = zapis(poradi,buttons);
                    if(!zapsano){
                        poradi = poradi-56;
                        zapis(poradi, buttons);
                    }
                    
                    break;
            }
        }
        else if (ai){
            //utok(buttons);
        }
    }
    public void utok(ArrayList<JButton> buttons, String znak){
        for (JButton button:buttons) {
            if (button.getText().equals(znak)){
                int index = buttons.indexOf(button);
                CheckAI obj = new CheckAI();
                ArrayList<CheckAI> list = new ArrayList<>();
                list.add(obj.check(index,buttons,znak,"up"));
                list.add(obj.check(index,buttons,znak,"left"));
                list.add(obj.check(index,buttons,znak,"down"));
                list.add(obj.check(index,buttons,znak,"righ"));
                int max = 0;
                String site = "";
                for (int i = 0; i < list.size(); i++){
                    int row = list.get(i).getRow();
                    if (row > max){
                        max = row;
                        site = list.get(i).getSite();
                    }
                }
                if (max > 0) {
                    switch (site) {
                        case "up": zapis(index+(max*UP),buttons);
                        break;
                        case "down": zapis(index+(max*DOWN), buttons);
                        break;
                        case "left": zapis(index+(max*LEFT), buttons);
                        break;
                        case "right": zapis(index+(max*RIGHT),buttons);
                        break;
                    }
                }
                else {

                }
                found = true;
            }
        }
        if (!found){
            for (JButton button:buttons) {
                if (button.getText().equals("X")){
                    int index = buttons.indexOf(button);
                    move(index);
                }
            }
            //zapis(generateNumber(), buttons);
        }
    }

    private boolean zapis(int poradi, ArrayList<JButton> buttons) {
            if (poradi >= 0 && poradi <= 254 && buttons.get(poradi).getText().equals("")) {
                buttons.get(poradi).doClick();
                return true;
            }
            else{
                return false;
        }
    }

    public void getObject(MyButtons obj){
        this.obj = obj;
    }

    private int generateNumber(int number) {
        Random rd = new Random();
        return rd.nextInt(number);
    }
    public void getList(ArrayList<JButton> buttons){
        this.list = buttons;
    }
    private void move(int index){
        ArrayList<Integer> l = new ArrayList<>();
        l.add(index - 15);
        l.add(index - 14);
        l.add(index + 1);
        l.add(index + 16);
        l.add(index + 15);
        l.add(index + 14);
        l.add(index - 1);
        l.add(index - 16);
        int number = generateNumber(8);
        boolean writen = zapis(l.get(number),list);
        if (!writen){
            move(index);
        }
    }


}

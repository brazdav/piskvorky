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
    private ArrayList<CheckAI> checkAI = new ArrayList<>();
    public void obrana (ArrayList<JButton> buttons, String znak){

    }
    public void utok(ArrayList<JButton> buttons, String znak){
        for (JButton button:buttons) {
            if (button.getText().equals(znak)){
                int index = buttons.indexOf(button);
                System.out.println("tlačítko na indexu: " + index);
                CheckAI obj = new CheckAI();
                CheckAI obj2 = new CheckAI();
                CheckAI obj3 = new CheckAI();
                CheckAI obj4 = new CheckAI();
                checkAI.add(obj.check(index,buttons,znak,"up"));
                checkAI.add(obj2.check(index,buttons,znak,"left"));
                checkAI.add(obj3.check(index,buttons,znak,"down"));
                checkAI.add(obj4.check(index,buttons,znak,"right"));
                int max = 0;
                String site = "";
                for (int i = 0; i < checkAI.size(); i++){
                    //System.out.println("row: " + checkAI.get(i).getRow() + " site: " + checkAI.get(i).getSite());
                    int row = checkAI.get(i).getRow();
                    if (row > max){
                        max = row;
                        site = checkAI.get(i).getSite();
                        System.out.println("max: " + max);
                    }
                }
                if (max > 0 && ai) {
                    found = true;
                    switch (site) {
                        case "up": zapis(index+((max)*UP),buttons);
                        break;
                        case "down": zapis(index+((max)*DOWN), buttons);
                        break;
                        case "left": zapis(index+((max)*LEFT), buttons);
                        break;
                        case "right": zapis(index+((max)*RIGHT),buttons);
                        break;
                    }
                }
                else if (max == 0 && ai){
                    found = true;
                    move(index);
                    System.out.println("metoda move");
                }
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
                System.out.println(poradi);
                checkAI.clear();
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
        l.clear();
    }


}

package AI;

import Rozhrani.FirstTurn;
import Uprava_tlacitka.MyButtons;

import javax.swing.*;
import java.util.ArrayList;
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
    private ArrayList<CheckAI> checkAI = new ArrayList<>();
    public void obrana (ArrayList<JButton> buttons, String znak){

    }
    public void utok(ArrayList<JButton> buttons, String znak){
            for (int i = 0; i < buttons.size(); i++) {
                if (buttons.get(i).getText().equals(znak)){
                    CheckAI obj1 = new CheckAI();
                    CheckAI obj2 = new CheckAI();
                    CheckAI obj3 = new CheckAI();
                    CheckAI obj4 = new CheckAI();
                    CheckAI obj6 = new CheckAI();
                    CheckAI obj7 = new CheckAI();
                    CheckAI obj8 = new CheckAI();
                    CheckAI obj9 = new CheckAI();
                    checkAI.add(obj1.check(i,buttons,znak,"up"));
                    checkAI.add(obj2.check(i,buttons,znak,"left"));
                    checkAI.add(obj3.check(i,buttons,znak,"down"));
                    checkAI.add(obj4.check(i,buttons,znak,"right"));
                    checkAI.add(obj6.check(i,buttons,znak,"leftup"));
                    checkAI.add(obj7.check(i,buttons,znak,"leftdown"));
                    checkAI.add(obj8.check(i,buttons,znak,"rightup"));
                    checkAI.add(obj9.check(i,buttons,znak,"rightdown"));
                    System.out.println(checkAI);
                }
                    int max = 0;
                    CheckAI obj5 = new CheckAI();
                for (int x = 0; x < checkAI.size(); x++) {
                    //System.out.println("row: " + checkAI.get(i).getRow() + " site: " + checkAI.get(i).getSite());
                    if (checkAI.get(x) != null) {
                        if (checkAI.get(x).getRow() > max) {
                            obj5 = checkAI.get(x);
                        }
                    }
                }
                    if (obj5.getRow() > 0 && ai) {
                        found = true;
                        zapis(obj5.getIndex(), buttons);
                    }
                    else if (obj5.getRow() == 0 && ai){
                        found = true;
                        move(find(buttons, "O"));
                    }
                }

            if (!found){
                move(find(buttons, "X"));
                //zapis(generateNumber(), buttons);
            }


    }

    private boolean zapis(int poradi, ArrayList<JButton> buttons) {
            if (poradi >= 0 && poradi <= 224 && buttons.get(poradi).getText().equals("")) {
                buttons.get(poradi).doClick();
                checkAI.clear();
                return true;
            }
            else{
                return false;
        }
    }

    private int find(ArrayList<JButton> buttons, String znak){
        int index = 0;
        for (JButton button:buttons) {
            if (button.getText().equals(znak)){
                index = buttons.indexOf(button);
            }
        }
        return index;
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

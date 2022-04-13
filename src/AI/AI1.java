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
    public int utok;
    public ArrayList<JButton> list = new ArrayList();
    private String check = "ai";
    private boolean found = false;
    public boolean ai = false;
    private ArrayList<CheckAI> utokList = new ArrayList<>();
    private ArrayList<CheckAI> obranaList = new ArrayList<>();
    public void obrana (ArrayList<JButton> buttons, String znak) {
        found = false;
        for (int i = 0; i < buttons.size(); i++) {
            if (buttons.get(i).getText().equals(znak)) {
                CheckAI obj1 = new CheckAI();
                CheckAI obj2 = new CheckAI();
                CheckAI obj3 = new CheckAI();
                CheckAI obj4 = new CheckAI();
                CheckAI obj6 = new CheckAI();
                CheckAI obj7 = new CheckAI();
                CheckAI obj8 = new CheckAI();
                CheckAI obj9 = new CheckAI();
                obranaList.add(obj1.check(i, buttons, znak, "up"));
                obranaList.add(obj2.check(i, buttons, znak, "left"));
                obranaList.add(obj3.check(i, buttons, znak, "down"));
                obranaList.add(obj4.check(i, buttons, znak, "right"));
                obranaList.add(obj6.check(i, buttons, znak, "leftup"));
                obranaList.add(obj7.check(i, buttons, znak, "leftdown"));
                obranaList.add(obj8.check(i, buttons, znak, "rightup"));
                obranaList.add(obj9.check(i, buttons, znak, "rightdown"));
            }
        }
        int max = 0;
        CheckAI obj5 = new CheckAI();
        for (int x = 0; x < obranaList.size(); x++) {
                if (obranaList.get(x).getRow() > max) {
                    max = obranaList.get(x).getRow();
                    obj5 = obranaList.get(x);
            }
                System.out.println("Počet v řadě: " + obranaList.get(x).getRow() + "Index: " + obranaList.get(x).getIndex());
        }
        System.out.println(ai);
        if (obj5.getRow() > 0 && ai) {
            zapis(obj5.getIndex(), buttons);
        }
        else if (obj5.getRow() == 0 && ai){
            move(find(buttons, "O"));
            System.out.println("move1");
        }

        if (!found){
            move(find(buttons, "X"));
            System.out.println("move2");
        }
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
                    utokList.add(obj1.check(i,buttons,znak,"up"));
                    utokList.add(obj2.check(i,buttons,znak,"left"));
                    utokList.add(obj3.check(i,buttons,znak,"down"));
                    utokList.add(obj4.check(i,buttons,znak,"right"));
                    utokList.add(obj6.check(i,buttons,znak,"leftup"));
                    utokList.add(obj7.check(i,buttons,znak,"leftdown"));
                    utokList.add(obj8.check(i,buttons,znak,"rightup"));
                    utokList.add(obj9.check(i,buttons,znak,"rightdown"));
                }
            }
                    int max = 0;
                    CheckAI obj5 = new CheckAI();
                for (int x = 0; x < utokList.size(); x++) {
                    //System.out.println("row: " + utokList.get(i).getRow() + " site: " + utokList.get(i).getSite());
                    if (utokList.get(x) != null) {
                        if (utokList.get(x).getRow() > max) {
                            obj5 = utokList.get(x);
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


            if (!found){
                move(find(buttons, "X"));
                System.out.println("move");
            }


    }

    private boolean zapis(int poradi, ArrayList<JButton> buttons) {
            if (buttons.get(poradi).getText().equals("")) {
                System.out.println("projde");
                buttons.get(poradi).doClick();
                obranaList.clear();
                utokList.clear();
                found = true;
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

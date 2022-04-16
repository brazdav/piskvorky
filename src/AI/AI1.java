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
    private boolean found;
    public boolean ai = false;
    private ArrayList<CheckAI> utokList = new ArrayList<>();
    private ArrayList<CheckAI> obranaList = new ArrayList<>();
    public void obrana (ArrayList<JButton> buttons, String znak, String type) {
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
        if (type.equals("ez"))
            evaluationEz(buttons, obranaList);
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
            evaluationHa(buttons);


    }

    private void evaluationEz(ArrayList<JButton> buttons, ArrayList<CheckAI> list){
        int max = 0;
        CheckAI obj = new CheckAI();
        for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getRow() > max) {
                    max = list.get(i).getRow();
                    obj = list.get(i);
            }
        }
        if (obj.getRow() > 0 && ai) {
            found = true;
            zapis(obj.getIndex(), buttons);
        }
        else if (obj.getRow() == 0 && ai){
            int number = find(buttons, "O");
            if (found){
                move(number);
            }
        }


        if (!found){
            move(find(buttons, "X"));
        }
    }

    private void evaluationHa(ArrayList<JButton> buttons){
        found = false;
        CheckAI obj = new CheckAI();
            int max = 0;
            for (int i = 0; i < utokList.size(); i ++){
                if (utokList.get(i).getRow() > max) {
                    max = utokList.get(i).getRow();
                    obj = utokList.get(i);
                }
            }
            for (int i = 0; i < obranaList.size(); i ++){
                if (obranaList.get(i).getRow() > max) {
                    max = obranaList.get(i).getRow();
                    obj = obranaList.get(i);
                }
            }
            if (obj.getRow() > 0 && ai) {
                found = true;
                zapis(obj.getIndex(), buttons);
                System.out.println("zapis");
            }
            else if (obj.getRow() == 0 && ai){
                int number = find(buttons, "O");
                if (found){
                    move(number);
                }
                System.out.println("move1");
            }


            if (!found){
                move(find(buttons, "X"));
                System.out.println("move2");
            }
    }

    private boolean zapis(int poradi, ArrayList<JButton> buttons) {
            if (poradi >= 0 && poradi < 225 && buttons.get(poradi).getText().equals("")) {
                System.out.println("zapis: " + poradi);
                buttons.get(poradi).doClick();
                utokList.clear();
                obranaList.clear();
                found = true;
                return true;
            }
            else{
                return false;
        }
    }

    private int find(ArrayList<JButton> buttons, String znak){
        int index = 0;
        for (int i = 0; i < buttons.size(); i++){
            if (buttons.get(i).getText().equals(znak)) {
                found = true;
                index = i;
                break;
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

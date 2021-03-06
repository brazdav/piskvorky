package AI;

import Rozhrani.FirstTurn;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Třída AI dědí z třídy JButton, aby třída MyButtons převzala vlastnoti tlačítka a implementuje rozhraní FirstTrun
 * Obsahuje metoudy obrana, útok, evolutionEz, evolutionHa, zapis, find, generateNumber a move
 * @author Vojtěch Brázda
 * @version 1.0.0
 */
public class AI extends JButton implements FirstTurn {
    public ArrayList<JButton> list = new ArrayList();
    private boolean found;
    public boolean ai = false;
    private ArrayList<CheckAI> utokList = new ArrayList<>();
    private ArrayList<CheckAI> obranaList = new ArrayList<>();
    private ArrayList<JButton> buttons;

    /**
     * Metoda getZnak získává ArrayList buttons, který obsahuje tlačítka z herní plochy, uloží ho do globálního ArrayListu buttons
     * Následně zavolá metodu naplneni() podle vyhodnocené podmínky, která se rozhoduje na základě parametru znak
     * @param buttons ArrayList tlačítek z herní plochy
     * @param znak String který rozhoduje jaký text se na tlačítkách vyhledávat
     */
    public void getZnak(ArrayList<JButton> buttons, String znak){
        this.buttons = buttons;
        if (znak.equals("X")) {
            naplneni(buttons, znak, obranaList);
            System.out.println("naplnění ez");
        }
        else {
            naplneni(buttons, znak, utokList);
            System.out.println("naplnění hard");
        }
    }
    /**
     * Metoda při zavolání nastaví proměnnou found na false
     * poté vyhledá všechna tlačítka, na kterých je daný text
     * po nalezení vytvoří 8 objektů, které představují jednotlivé směry hledání dalších tlačítek
     * následně jsou objekty uloženy do ArrayListu
     * @param buttons ArrayList tlačítek, ve kterém hledáme tlačítka s daným textem
     * @param znak text který na tlačítku hledáme
     * @param list proměnná zamezuje spouštění vyhodnocování v nesprávný moment
     */
    private void naplneni (ArrayList<JButton> buttons, String znak, ArrayList<CheckAI> list) {
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
                list.add(obj1.check(i, buttons, znak, "up"));
                list.add(obj2.check(i, buttons, znak, "left"));
                list.add(obj3.check(i, buttons, znak, "down"));
                list.add(obj4.check(i, buttons, znak, "right"));
                list.add(obj6.check(i, buttons, znak, "leftup"));
                list.add(obj7.check(i, buttons, znak, "leftdown"));
                list.add(obj8.check(i, buttons, znak, "rightup"));
                list.add(obj9.check(i, buttons, znak, "rightdown"));
            }
        }
    }

    /**
     * Metoda po zavolání najde v ArrayListu list objekt s největším hodnotou row a uloží ho do objektu třídy CheckAI, pokud se zde nacházejí dva objekty se stejným číslem, uloží se první
     * Pokud bude row větší než nula a zároveň proměnná ai bude true, proměnná found se nastaví na treu a zavolá se metoda zapis()
     * Pokud bude row roven nule a zároveň proměnná ai bude true zavolá se metoda find() jejíž výstup bude uložen do proměnné number, když se metoda find() přepíše proměnnou found na true, zavolá se i metoda move
     * Za podmínky, že zůstane proměnná found nastvená jako false, se vykoná metoda move
     */
    public void evaluationEz(){
        int max = 0;
        CheckAI obj = new CheckAI();
        for (int i = 0; i < obranaList.size(); i++) {
                if (obranaList.get(i).getRow() > max) {
                    max = obranaList.get(i).getRow();
                    obj = obranaList.get(i);
            }
        }
        if (obj.getRow() > 0 && ai) {
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

    /**
     * Metoda při zavolání nastaví proměnnou found na false
     * Poté vyhledá největší proměnnou row u objektu v ArrayListu utokList a uloží ji do deklarovaného objektu obj
     * Následovně se hledá větší hodnota proměnné row u objektu v ArrayListu obranaList než byla nalezena v ArrayListu utokList, pokud je tomu tak, tak se objekt obj přepíše
     * Pokud bude row větší než nula a zároveň proměnná ai bude true, proměnná found se nastaví na treu a zavolá se metoda zapis()
     * Pokud bude row roven nule a zároveň proměnná ai bude true zavolá se metoda find() jejíž výstup bude uložen do proměnné number, když se metoda find() přepíše proměnnou found na true, zavolá se i metoda move
     * Za podmínky, že zůstane proměnná found nastvená jako false, se vykoná metoda move
     */
    public void evaluationHa(){
        found = false;
        CheckAI obj = new CheckAI();
            int max = 0;
            for (int i = 0; i < utokList.size(); i ++){
                if (utokList.get(i).getRow() > max) {
                    max = utokList.get(i).getRow();
                    obj = utokList.get(i);
                }
                System.out.println("utok row: " + utokList.get(i).getRow() + " index: " + utokList.get(i).getIndex());
            }
            for (int i = 0; i < obranaList.size(); i ++){
                if (obranaList.get(i).getRow() > max) {
                    max = obranaList.get(i).getRow();
                    obj = obranaList.get(i);
                }
                System.out.println("obrana row: " + obranaList.get(i).getRow() + " index: " + obranaList.get(i).getIndex());
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

    /**
     * Metoda zapis kontroluje zda je vstupní parametr poradi v rozsahu ArrayListu buttons a zároveň, zda již není na indexu něco vykresleno
     * Dále simuluje kliknutí na tlačítko vybrané z ArrayListu buttons a vymzává hodnoty z ArrayListu utokList a ArrayListu obranaList a nastavuje proměnnou found na true
     * @param poradi index na kterém cheme hledat tlačítko v ArrayListu buttons
     * @param buttons proměnná v sobě obsahuje ArrayList tlačítek z herní plochy
     * @return pokud metoda uměle klikne na tlačítko vrátí true, jinak vrací false
     */
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

    /**
     * Metoda nastavuje index na 0
     * Najde v ArrayListu buttons tlačítko, na kterém je vykreslený daný string
     * Pokud takové tlačítko najde nastaví proměnnou found na true, uloží si index na kterém se v tlačítko v ArrayListu nachází a cylus se ukončí
     * @param buttons ArrayList tlačítek z herní plochy, ve kterém hledáme danné tlačítko
     * @param znak string který hledáme na tlačítku
     * @return vrací index z ArrayListu buttons, na kterém jsem tlačítko našli
     */
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

    /**
     * Metoda generateNumber generuje random číslo v rozsahu
     * @param number vstupní hodnota pro určení rozsahu, ve kterém se má číslo pohybovat
     * @return metoda vrací vygenerované číslo
     */
    private int generateNumber(int number) {
        Random rd = new Random();
        return rd.nextInt(number);
    }

    /**
     * Metoda getList získává ArrayList buttons a ukládá ho do globálního ArrayListu list
     * @param buttons ArrayList ve kterém jsou uloženy tlačítka z herní plochy
     */
    public void getList(ArrayList<JButton> buttons){
        this.list = buttons;
    }

    /**
     * Metoda move naplní ArrayList l 8 hodnotami, které představují tlačítka okolo základního indexu
     * Poté vygeneruje číslo od 0 do 7 a vybere se hodnota na vygenerovaném indexu
     * Pokud zavolaná metoda zapis() vrátí treu na daném indexu je prázdno, jinak se musí postup opakovat, dokud se nenajde tlačítko na které lze zapsat
     * @param index proměnná, okolo které se bude hledat tlačítko na které lze zapsat
     */
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

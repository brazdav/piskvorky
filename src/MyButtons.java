import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyButtons extends JButton implements FirstTurn,Podminky {
    int xRada = 0;//parametr pro výhru X
    int oRada = 0;//parametr pro výhru O
    JPanel button_panel = new JPanel();//objekt pro vykreslení tlačítek
    public MyButtons(){//konstruktor třídy MyButtons
            this.setFont(new Font("MV Boli", Font.BOLD, 25));//nastavení fontu pro objekt
            this.setBackground(new Color(255,255,255));//nastavení barvy pro objekt
            this.setFocusable(false);//nastavení vlastnosti Focusble pro objekt
            }



    public void vykresleni(JButton button){//tato meto vykresluje X a O na objekt typu JButton
        if (player1_turn.get()){
            if (button.getText().equals("")){//získání řetězce na daném objektu
                button.setForeground(new Color(0,0,0));//nastavení popředí na objektu
                button.setText("X");//nastavení řetězce na objektu
                player1_turn.set(false);//změna proměnné, aby bylo jasné, kdo hraje
                textfield.setText("O turn");//změna textfieldu, zobrazeného nad polem piškvorek, aby bylo jasné, kdo hraje
                //button.setEnabled(false);
            }
        }
        else if (button.getText().equals("")){//dělá to samé jako if nad ním, akorát vykresluje na objekt O
            button.setForeground(new Color(0, 0, 0));
            button.setText("O");
            //button.setEnabled(false);
            player1_turn.set(true);
            textfield.setText("X turn");
        }
    }


    public void checkLeva(JButton tlaco, ArrayList<JButton> buttons){//metoda kontroluje zda nalevo od vybraného objektu, je objekt se stejným parametrem
        String znak = tlaco.getText();//získání parametru
        int poradi = buttons.indexOf(tlaco);//získání indexu objektu v arraylistu
        if(Podminky.vyhodnoceni(poradi,Podminky.naplneni(15,0,210))){//if volá metodu vyhodnocení z inteface Vyhodnocení s parametry indexu objektu v arraylistu a arraylistem naplněným čísly pro podmínku
            poradi--;//snížení indexu v arraylistu o 1
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){//if kontroluje zda je vedle stejný znak
            if (znak.equals("X")) xWins();//pokud je znakem X zavolá se metoda xWins
            else oWins();//pokud je znakem O zavolá se metoda oWins
            checkLeva((JButton) buttons.get(poradi), buttons);//znovu zavolání metody checkLeva


        }
        else return;
    }

    public void checkPrava(JButton tlaco, ArrayList<JButton> buttons){//metoda dělá to samé jako chechLeva akorát doprava
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if(Podminky.vyhodnoceni(poradi,Podminky.naplneni(15, 14, 224))){
            poradi++;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkPrava((JButton) buttons.get(poradi), buttons);


        }
        else{
            oRada = 0;
            xRada = 0;
            return;
        }
    }

    public void checkDole(JButton tlaco, ArrayList<JButton> buttons){//metoda dělá to samé jako chechLeva akorát dolů
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if(Podminky.vyhodnoceni(poradi,Podminky.naplneni(1, 210, 254))){
            poradi += 15;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkDole((JButton) buttons.get(poradi), buttons);


        }
        else{
            xRada = 0;
            oRada = 0;
            return;
        }
    }

    public void checkHore(JButton tlaco, ArrayList<JButton> buttons){//metoda dělá to samé jako chechLeva akorát nahoru
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if(Podminky.vyhodnoceni(poradi,Podminky.naplneni(1, 0, 14))){
            poradi -= 15;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkHore((JButton) buttons.get(poradi), buttons);


        }
        else return;
    }


    public void checkPravaHore(JButton tlaco, ArrayList<JButton> buttons){//metoda dělá to samé jako chechLeva, s rozšířenou podmínkou akorát doprava a sikmo nahoru
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if(Podminky.vyhodnoceni(poradi,Podminky.napleni(1, 0, 14, 15, 14, 224))){
            poradi -= 14;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkPravaHore((JButton) buttons.get(poradi), buttons);


        }
        else{
            xRada = 0;
            oRada = 0;
            return;
        }
    }

    public void checkPravaDole(JButton tlaco, ArrayList<JButton> buttons){//metoda dělá to samé jako chechLeva, s rozšířenou podmínkou akorát doprava a sikmo dolu
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if(Podminky.vyhodnoceni(poradi,Podminky.napleni(1, 210, 224, 15, 14, 224))){
            poradi += 16;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkPravaDole((JButton) buttons.get(poradi), buttons);


        }
        else{
            xRada = 0;
            oRada = 0;
            return;
        }
    }

    public void checkLevaHore(JButton tlaco, ArrayList<JButton> buttons){//metoda dělá to samé jako chechLeva, s rozšířenou podmínkou akorát doleva a sikmo nahoru
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if(Podminky.vyhodnoceni(poradi,Podminky.napleni(1, 0, 14, 15, 0, 210))){
            poradi -= 16;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkLevaHore((JButton) buttons.get(poradi), buttons);


        }
        else return;
    }

    public void checkLevaDole(JButton tlaco, ArrayList<JButton> buttons){//metoda dělá to samé jako chechLeva, s rozšířenou podmínkou akorát doleva a sikmo dolu
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if(Podminky.vyhodnoceni(poradi,Podminky.napleni(1, 210, 224, 15, 0, 210))){
            poradi += 14;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkLevaDole((JButton) buttons.get(poradi), buttons);


        }
        else return;
    }

    public void xWins(){//metoda při každém zavolání přičte do proměnné xRada 1 a kontroluje proměnnou xRada, jestli její hodnota dosáhla 4
        xRada ++;//přičtení do proměnné
        //System.out.println(xRada);
        if (xRada == 4){//kontrola proměnné
            System.out.println("x vyhrali");
        }
    }

    public void oWins(){//dělá to samé jako xWins, akorát s proměnnou oRada
        oRada ++;
        if (oRada == 4){
            System.out.println("o vyhrali");
        }

    }
}

package Uprava_tlacitka;

import AI.AI;
import Rozhrani.FirstTurn;
import Rozhrani.Podminky;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
/**
 * Třída MyButtons dědí z třídy AI1 a implementuje rozhraní FirstTurn a Podminky
 * Obsahuje konstruktor, který nastavuje vlastnosti tlačítek v hracím poli
 * Obsahuje metody buttonOn a buttonOff, který nastavují setEnabled true nebo false na tlaco
 * Dále metody check..., které kontrolují tlačítka v daném směru metody
 * Metody xWins a oWins, který určují zda vyhrálo X nebo O
 * Metoda getString, která nastavuje String do proměnné lan_ai
 * @author Vojtěch Brázda, Simon Valeš
 * @version 0.1.0
 *
 *
 * */
public class MyButtons extends AI implements FirstTurn, Podminky {
    int xRada = 0;
    int oRada = 0;
    public int winX = 0;
    public int winO = 0;
    public JPanel button_panel = new JPanel();
    public boolean vyhra = false;
    static private String lan_ai;
    /**
     * Konstruktor třídy MyButtons
     * Nastavujeme zde různé vzhledové prvky, pro písmo, pozadí
     * Také přidáváme mouseListener a v něm metodu mouseEntered, která nejprve zkontroluje tlačítko na které najede myš a pokud se text na tlačítku rovná "",
     * tak se změní barva pozadí tlačíta na zelenou a může na dané políčko hrát, pokud je tomu jinak tak se na tlačítku změní barva pozadí na červenou a hráč tam hrát nemůže
     * Metodu mouseExited, která když myš vyjede z tlačítka tak nastaví barvu jeho pozadí na bílou
     * */

    public MyButtons() {
        this.setFont(new Font("MV Boli", Font.BOLD, 25));
        this.setBackground(new Color(255, 255, 255));
        this.setFocusable(false);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (MyButtons.this.getText().equals(""))
                    MyButtons.this.setBackground(new Color(197, 245, 191));
                else
                    MyButtons.this.setBackground(new Color(245, 191, 191));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                MyButtons.this.setBackground(Color.WHITE);
            }
        });
    }

    /**
     * Metoda buttonOn nastavuje tlačítko na aktivní, takže na něj lze kliknout
     * @param tlaco je to JButton
     * */
    public void buttonOn(JButton tlaco){
        tlaco.setEnabled(true);
    }
    /**
     * Metoda buttonOn nastavuje tlačítko na neaktivní, takže na něj nelze kliknout
     * @param tlaco je to JButton
     * */
    public void buttonOff(JButton tlaco){
        tlaco.setEnabled(false);
    }




    /**
     * Metoda checkLeva kontroluje zda lze hrat do leva, využívá rozhraní Podminky, které kontroluje zda neni tlačítko na kraji hrací plochy
     * Pokud hráč hraje z leva do prava a má tam 3 znaky, tak ho AI zablokuje z levé strany
     * Pokud hráč má 4 znaky v řadě a hrál z pravé strany tak ho AI zablokuje z leva
     * @param tlaco JButton, tlačítko na hrací ploše
     * @param buttons ArrayList, pole tlačítek
     * @param check String
     *
     * */
    public void checkLeva(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(15, 0, 210))) {
            poradi--;
        } else return;
            if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
                if (znak.equals("X")) xWins();
                else oWins();
                checkLeva((JButton) buttons.get(poradi), buttons, check);


            } else return;
    }

    /**
     * Metoda checkPrava kontroluje zda lze hrat do prava, využívá rozhraní Podminky, které kontroluje zda neni tlačítko na kraji hrací plochy
     * Pokud hráč hraje z prava do leva a má tam 3 znaky, tak ho AI zablokuje z pravé strany
     * Pokud hráč má 4 znaky v řadě a hrál z levé strany tak ho AI zablokuje z prava
     * @param tlaco JButton, tlačítko na hrací ploše
     * @param buttons ArrayList, pole tlačítek
     * @param check String
     *
     * */
    public void checkPrava(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(15, 14, 224))) {
            poradi++;
        } else return;
            if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
                if (znak.equals("X")) xWins();
                else if (znak.equals("O")) oWins();
                checkPrava((JButton) buttons.get(poradi), buttons, check);


            } else {
                oRada = 0;
                xRada = 0;
                return;
            }

    }
    /**
     * Metoda checkDole kontroluje zda lze hrát dolů, využívá rozhraní Podminky, které kontroluje zda neni tlačítko na kraji hrací plochy
     * Pokud hráč hraje ze shora dolů a má tam 3 znaky, tak ho AI zablokuje z horní strany
     * Pokud hráč má 4 znaky v řadě a hrál ze shora, tak ho AI zablokuje ze zdola
     * @param tlaco JButton, tlačítko na hrací ploše
     * @param buttons ArrayList, pole tlačítek
     * @param check String
     *
     * */
    public void checkDole(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 210, 254))) {
            poradi += 15;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkDole((JButton) buttons.get(poradi), buttons, check);


        } else {
            xRada = 0;
            oRada = 0;
            return;
        }
    }
    /**
     * Metoda checkHore kontroluje zda lze hrát nahoru, využívá rozhraní Podminky, které kontroluje zda neni tlačítko na kraji hrací plochy
     * Pokud hráč hraje ze dola na horu a má tam 3 znaky, tak ho AI zablokuje z dolní strany
     * Pokud hráč má 4 znaky v řadě a hrál ze shora, tak ho AI zablokuje ze zdola
     * @param tlaco JButton, tlačítko na hrací ploše
     * @param buttons ArrayList, pole tlačítek
     * @param check String
     *
     * */
    public void checkHore(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 0, 14))) {
            poradi -= 15;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkHore((JButton) buttons.get(poradi), buttons, check);


        } else return;
    }
    /**
     * Metoda checkPravaHore kontroluje zda lze hrát do prava nahoru, využívá rozhraní Podminky, které kontroluje zda neni tlačítko na kraji hrací plochy
     * Pokud hráč hraje ze dola šikmo směrem na horu a má tam 3 znaky, tak ho AI zablokuje z dolní strany
     * Pokud hráč má 4 znaky v řadě a hrál z dola, tak ho AI zablokuje ze zdola
     * @param tlaco JButton, tlačítko na hrací ploše
     * @param buttons ArrayList, pole tlačítek
     * @param check String
     *
     * */
    public void checkPravaHore(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 0, 14, 15, 14, 224))) {
            poradi -= 14;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkPravaHore((JButton) buttons.get(poradi), buttons, check);


        } else {
            xRada = 0;
            oRada = 0;
            return;
        }
    }
    /**
     * Metoda checkPravaDole kontroluje zda lze hrát do prava dolu, využívá rozhraní Podminky, které kontroluje zda neni tlačítko na kraji hrací plochy
     * Pokud hráč hraje ze dola šikmo směrem na dolu a má tam 3 znaky, tak ho AI zablokuje z horní strany
     * Pokud hráč má 4 znaky v řadě a hrál z hora, tak ho AI zablokuje ze zdola
     * @param tlaco JButton, tlačítko na hrací ploše
     * @param buttons ArrayList, pole tlačítek
     * @param check String
     *
     * */
    public void checkPravaDole(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 210, 224, 15, 14, 224))) {
            poradi += 16;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkPravaDole((JButton) buttons.get(poradi), buttons, check);


        } else {
            xRada = 0;
            oRada = 0;
            return;
        }
    }
    /**
     * Metoda checkLevaHore kontroluje zda lze hrát do leva nahoru, využívá rozhraní Podminky, které kontroluje zda neni tlačítko na kraji hrací plochy
     * Pokud hráč hraje ze dola šikmo směrem na horu a má tam 3 znaky, tak ho AI zablokuje z dolní strany
     * Pokud hráč má 4 znaky v řadě a hrál z dola, tak ho AI zablokuje ze zdola
     * @param tlaco JButton, tlačítko na hrací ploše
     * @param buttons ArrayList, pole tlačítek
     * @param check String
     *
     * */
    public void checkLevaHore(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 0, 14, 15, 0, 210))) {
            poradi -= 16;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkLevaHore((JButton) buttons.get(poradi), buttons, check);


        } else return;
    }
    /**
     * Metoda checkLevaDole kontroluje zda lze hrát do leva dolu, využívá rozhraní Podminky, které kontroluje zda neni tlačítko na kraji hrací plochy
     * Pokud hráč hraje ze dola šikmo směrem na dolu a má tam 3 znaky, tak ho AI zablokuje z horní strany
     * Pokud hráč má 4 znaky v řadě a hrál z hora, tak ho AI zablokuje ze zdola
     * @param tlaco JButton, tlačítko na hrací ploše
     * @param buttons ArrayList, pole tlačítek
     * @param check String
     *
     * */
    public void checkLevaDole(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 210, 224, 15, 0, 210))) {
            poradi += 14;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkLevaDole((JButton) buttons.get(poradi), buttons, check);


        } else return;
    }
    /**
     * Metoda xWins vyhodnocuje výhru pro znak X, pokue se řada znaků X bude rovnat 5 tak se přičte a vyhodnotí výhra
     * */
    public void xWins() {
        xRada++;
        if (xRada == 4) {
            winX++;
            vyhra = true;
        }
    }
    /**
     * Metoda oWins vyhodnocuje výhru pro znak O, pokue se řada znaků O bude rovnat 5 tak se přičte a vyhodnotí výhra
     * */
    public void oWins() {
        oRada++;
        if (oRada == 4) {
            winO++;
            vyhra = true;
        }

    }
    /**
     * Metoda getString nastaví do proměnné lan_ai řetězec st
     * @param st String
     * */
    public void getString(String st){
        lan_ai = st;
    }
}

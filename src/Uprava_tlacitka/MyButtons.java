package Uprava_tlacitka;

import AI.AI1;
import Rozhrani.FirstTurn;
import Rozhrani.Podminky;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyButtons extends AI1 implements FirstTurn, Podminky {
    int xRada = 0;
    int oRada = 0;
    public int winX = 0;
    public int winO = 0;
    public JPanel button_panel = new JPanel();
    public boolean vyhra = false;
    static private String lan_ai;

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
        getObject(this);
    }


    public void buttonOn(JButton tlaco){
        tlaco.setEnabled(true);
    }
    public void buttonOff(JButton tlaco){
        tlaco.setEnabled(false);
    }





    public void checkLeva(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(15, 0, 210))) {
            poradi--;
        } else return;
            if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
                if (lan_ai.equals("ai")) {
                    obrana(znak, poradi, buttons, "leva", oRada, xRada);
                }
                if (znak.equals("X")) xWins();
                else oWins();
                checkLeva((JButton) buttons.get(poradi), buttons, check);


            } else return;
    }


    public void checkPrava(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(15, 14, 224))) {
            poradi++;
        } else return;
            if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
                if (lan_ai.equals("ai")) {
                    obrana(znak, poradi, buttons, "prava", oRada, xRada);
                }
                if (znak.equals("X")) xWins();
                else if (znak.equals("O")) oWins();
                checkPrava((JButton) buttons.get(poradi), buttons, check);


            } else {
                oRada = 0;
                xRada = 0;
                return;
            }

    }

    public void checkDole(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 210, 254))) {
            poradi += 15;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (lan_ai.equals("ai")) {
                obrana(znak, poradi, buttons, "dolni", oRada, xRada);
            }
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkDole((JButton) buttons.get(poradi), buttons, check);


        } else {
            xRada = 0;
            oRada = 0;
            return;
        }
    }

    public void checkHore(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 0, 14))) {
            poradi -= 15;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (lan_ai.equals("ai")) {
                obrana(znak, poradi, buttons, "horni", oRada, xRada);
            }
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkHore((JButton) buttons.get(poradi), buttons, check);


        } else return;
    }


    public void checkPravaHore(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 0, 14, 15, 14, 224))) {
            poradi -= 14;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (lan_ai.equals("ai")) {
                obrana(znak, poradi, buttons, "pravaHorni", oRada, xRada);
            }
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkPravaHore((JButton) buttons.get(poradi), buttons, check);


        } else {
            xRada = 0;
            oRada = 0;
            return;
        }
    }

    public void checkPravaDole(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 210, 224, 15, 14, 224))) {
            poradi += 16;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (lan_ai.equals("ai")) {
                obrana(znak, poradi, buttons, "pravaDolni", oRada, xRada);
            }
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkPravaDole((JButton) buttons.get(poradi), buttons, check);


        } else {
            xRada = 0;
            oRada = 0;
            return;
        }
    }

    public void checkLevaHore(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 0, 14, 15, 0, 210))) {
            poradi -= 16;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (lan_ai.equals("ai")) {
                obrana(znak, poradi, buttons, "levaHorni", oRada, xRada);
            }
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkLevaHore((JButton) buttons.get(poradi), buttons, check);


        } else return;
    }

    public void checkLevaDole(JButton tlaco, ArrayList<JButton> buttons, String check) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 210, 224, 15, 0, 210))) {
            poradi += 14;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (lan_ai.equals("ai")) {
                obrana(znak, poradi, buttons, "levaDolni", oRada, xRada);
            }
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkLevaDole((JButton) buttons.get(poradi), buttons, check);


        } else return;
    }

    public void xWins() {
        xRada++;
        if (xRada == 4) {
            winX++;
            vyhra = true;
        }
    }

    public void oWins() {
        oRada++;
        if (oRada == 4) {
            winO++;
            vyhra = true;
        }

    }
    public void getString(String st){
        lan_ai = st;
    }
}

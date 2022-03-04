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

    public MyButtons(String lan_ai) {
        this.lan_ai = lan_ai;
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

    public MyButtons() {
    }

    public void buttonOn(JButton tlaco){
        tlaco.setEnabled(true);
    }
    public void buttonOff(JButton tlaco){
        tlaco.setEnabled(false);
    }





    public void checkLeva(JButton tlaco, ArrayList<JButton> buttons) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(15, 0, 210))) {
            poradi--;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (lan_ai.equals("ai")) {
                obrana(znak, poradi, buttons, "leva");
            }
            if (znak.equals("X")) xWins();
            else oWins();
            checkLeva((JButton) buttons.get(poradi), buttons);


        } else return;
    }

    public void checkPrava(JButton tlaco, ArrayList<JButton> buttons) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(15, 14, 224))) {
            poradi++;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else if (znak.equals("O")) oWins();
            checkPrava((JButton) buttons.get(poradi), buttons);


        } else {
            oRada = 0;
            xRada = 0;
            return;
        }
    }

    public void checkDole(JButton tlaco, ArrayList<JButton> buttons) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 210, 254))) {
            poradi += 15;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else oWins();
            checkDole((JButton) buttons.get(poradi), buttons);


        } else {
            xRada = 0;
            oRada = 0;
            return;
        }
    }

    public void checkHore(JButton tlaco, ArrayList<JButton> buttons) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.naplneni(1, 0, 14))) {
            poradi -= 15;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else oWins();
            checkHore((JButton) buttons.get(poradi), buttons);


        } else return;
    }


    public void checkPravaHore(JButton tlaco, ArrayList<JButton> buttons) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.napleni(1, 0, 14, 15, 14, 224))) {
            poradi -= 14;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else oWins();
            checkPravaHore((JButton) buttons.get(poradi), buttons);


        } else {
            xRada = 0;
            oRada = 0;
            return;
        }
    }

    public void checkPravaDole(JButton tlaco, ArrayList<JButton> buttons) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.napleni(1, 210, 224, 15, 14, 224))) {
            poradi += 16;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else oWins();
            checkPravaDole((JButton) buttons.get(poradi), buttons);


        } else {
            xRada = 0;
            oRada = 0;
            return;
        }
    }

    public void checkLevaHore(JButton tlaco, ArrayList<JButton> buttons) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.napleni(1, 0, 14, 15, 0, 210))) {
            poradi -= 16;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else oWins();
            checkLevaHore((JButton) buttons.get(poradi), buttons);


        } else return;
    }

    public void checkLevaDole(JButton tlaco, ArrayList<JButton> buttons) {
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if (Podminky.vyhodnoceni(poradi, Podminky.napleni(1, 210, 224, 15, 0, 210))) {
            poradi += 14;
        } else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)) {
            if (znak.equals("X")) xWins();
            else oWins();
            checkLevaDole((JButton) buttons.get(poradi), buttons);


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
}

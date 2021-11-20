import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyButtons extends JButton implements FirstTurn,Podminky {
    String obsah;
    JPanel button_panel = new JPanel();
    ArrayList buttons = new ArrayList<JButton>();
    int xRada = 0;
    int oRada = 0;

    public void vytvoreni(String i){
        MyButtons tlaco = new MyButtons();
        //tlaco.setText(i);
        button_panel.add(tlaco);
        tlaco.setFont(new Font("MV Boli",Font.BOLD,50));
        tlaco.setBackground(new Color(255, 255, 255));
        tlaco.setFocusable(false);
        tlaco.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vykresleni(tlaco);
                checkLeva(tlaco);
                checkPrava(tlaco);
                checkHore(tlaco);
                checkDole(tlaco);
                checkLevaHore(tlaco);
                checkLevaDole(tlaco);
                checkPravaHore(tlaco);
                checkPravaDole(tlaco);
            }
        });
        buttons.add(tlaco);
    }



    public void vykresleni(JButton button){
        if (player1_turn.get()){
            if (button.getText().equals("")){
                button.setForeground(new Color(0,0,0));
                button.setText("X");
                player1_turn.set(false);
                textfield.setText("O turn");
                //button.setEnabled(false);
            }
        }
        else if (button.getText().equals("")){
                button.setForeground(new Color(0, 0, 0));
                button.setText("O");
                //button.setEnabled(false);
                player1_turn.set(true);
                textfield.setText("X turn");
            }
        }


    public void checkLeva(JButton tlaco){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        Podminky.naplneni(15,0,210);
        if(Podminky.vyhodnoceni(poradi)){
            poradi--;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkLeva((JButton) buttons.get(poradi));
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkDole(JButton tlaco){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        Podminky.naplneni(1, 210, 254);
        if(Podminky.vyhodnoceni(poradi)){
            poradi -= 15;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkDole((JButton) buttons.get(poradi));
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkHore(JButton tlaco){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        Podminky.naplneni(1, 0, 14);
        if(Podminky.vyhodnoceni(poradi)){
            poradi += 15;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkHore((JButton) buttons.get(poradi));
            oRada = 0;
            xRada = 0;
        }
        else return;
    }
    public void checkPrava(JButton tlaco){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        Podminky.naplneni(15, 14, 254);
        if(Podminky.vyhodnoceni(poradi)){
            poradi++;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkPrava((JButton) buttons.get(poradi));
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkPravaHore(JButton tlaco){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        Podminky.napleni(1, 0, 14, 15, 14, 224);
        if(Podminky.vyhodnoceni(poradi)){
            poradi -= 14;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkPravaHore((JButton) buttons.get(poradi));
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkPravaDole(JButton tlaco){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        Podminky.napleni(1, 210, 224, 15, 14, 224);
        if(Podminky.vyhodnoceni(poradi)){
            poradi += 16;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkPravaDole((JButton) buttons.get(poradi));
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkLevaHore(JButton tlaco){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        Podminky.napleni(1, 0, 14, 15, 0, 210);
        if(Podminky.vyhodnoceni(poradi)){
            poradi += 16;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkLevaHore((JButton) buttons.get(poradi));
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkLevaDole(JButton tlaco){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        Podminky.napleni(1, 210, 224, 15, 0, 210);
        if(Podminky.vyhodnoceni(poradi)){
            poradi += 16;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkLevaDole((JButton) buttons.get(poradi));
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void xWins(){
        xRada ++;

        if(xRada == 4){
            System.out.println("x vyhrali");
        }
    }
    public void oWins(){
        oRada ++;

        if (oRada == 4){
            System.out.println("o vyhrali");
        }
    }
}

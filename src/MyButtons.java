import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyButtons extends JButton implements FirstTurn,Podminky {
    int xRada = 0;
    int oRada = 0;
    JPanel button_panel = new JPanel();
    public MyButtons(){
            this.setFont(new Font("MV Boli", Font.BOLD, 25));
            this.setBackground(new Color(255,255,255));
            this.setFocusable(false);
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


    public void checkLeva(JButton tlaco, ArrayList<JButton> buttons){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if(Podminky.vyhodnoceni(poradi,Podminky.naplneni(15,0,210))){
            poradi--;
        }
        else return;
        if (((JButton) buttons.get(poradi)).getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkLeva((JButton) buttons.get(poradi), buttons);


        }
        else return;
    }

    public void checkPrava(JButton tlaco, ArrayList<JButton> buttons){
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

    public void checkDole(JButton tlaco, ArrayList<JButton> buttons){
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

    public void checkHore(JButton tlaco, ArrayList<JButton> buttons){
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


    public void checkPravaHore(JButton tlaco, ArrayList<JButton> buttons){
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

    public void checkPravaDole(JButton tlaco, ArrayList<JButton> buttons){
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

    public void checkLevaHore(JButton tlaco, ArrayList<JButton> buttons){
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

    public void checkLevaDole(JButton tlaco, ArrayList<JButton> buttons){
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

    public void xWins(){
        xRada ++;
        //System.out.println(xRada);
        if (xRada == 4){
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

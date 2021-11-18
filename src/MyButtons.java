import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyButtons extends JButton implements FirstTurn {
    String obsah;
    JPanel button_panel = new JPanel();
    //MyButtons[] buttons = new MyButtons[225];
    ArrayList buttons = new ArrayList<JButton>();
    int xRada = 0;
    int oRada = 0;

    public void vytvoreni(String i){
            MyButtons tlaco = new MyButtons();
            button_panel.add(tlaco);
            tlaco.setFont(new Font("MV Boli",Font.BOLD,50));
            tlaco.setBackground(new Color(255, 255, 255));
            tlaco.setFocusable(false);
            tlaco.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vykresleni(tlaco);
                    checkLeva(tlaco);
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
             textfield.setText("X turn");
             //button.setEnabled(false);
         }
        }
        else if (button.getText().equals("")){
                button.setForeground(new Color(0, 0, 0));
                button.setText("O");
                button.setEnabled(false);
                player1_turn.set(true);
                textfield.setText("X turn");
            }
        }


    public void checkLeva(JButton tlaco){
        String znak = tlaco.getText();
        int poradi = buttons.indexOf(tlaco);
        if(poradi != 0 && poradi != 15 && poradi != 30 && poradi != 45 && poradi != 60 && poradi != 75 && poradi != 90 && poradi != 105 && poradi != 120 && poradi != 135 && poradi != 150 && poradi != 165 && poradi != 180 && poradi != 195 && poradi != 210){
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

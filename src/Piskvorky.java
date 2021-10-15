import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Piskvorky implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[225];
    boolean player1_turn;
    boolean player2_turn;
    int xRada = 0;
    int oRada = 0;

    Piskvorky(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(43, 135, 255));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("SansSerif",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Piškvorky");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(15,15));
        button_panel.setBackground(new Color(150,150,150));

        for (int i = 0; i < 225; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,50));
            buttons[i].setBackground(new Color(255, 255, 255));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);

        }

        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();

    }
    public void actionPerformed(ActionEvent e){
        for(int i=0; i<225; i++){
            if(e.getSource()==buttons[i]){
                if (player1_turn){
                    if (buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0,0,0));
                        buttons[i].setText("X");
                        player1_turn=false;
                        textfield.setText("O turn");
                        check(i, "X");
                    }

                }
                else {
                    if (buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0, 0, 0));
                        buttons[i].setText("O");
                        player1_turn=true;
                        textfield.setText("X turn");
                        check(i, "O");
                    }
                }
            }
        }
    }
    public void firstTurn(){
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if(random.nextInt(2)==1){
            player1_turn = true;
            textfield.setText("X turn");
        }
        else{
            player1_turn = false;
            textfield.setText("O turn");
        }
    }
    public void check(int tlacitko, String znak){


        checkLeva(tlacitko, znak);


    }
    public void checkLeva(int tlactiko, String znak){
        if(tlactiko > 0){
            tlactiko--;
        }
        else return;
        System.out.println(buttons[tlactiko].getText().toString() + ", " + tlactiko);
        if (buttons[tlactiko].getText().toString() == znak){
            if (znak == "X") xWins();
            else oWins();
            checkLeva(tlactiko, znak);
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

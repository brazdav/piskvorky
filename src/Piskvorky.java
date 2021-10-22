
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;




public class Piskvorky implements ActionListener{

    Random random = new Random();
    JFrame frame = new JFrame();
    JFrame menu = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JLabel textfield1 = new JLabel();
    JButton[] buttons = new JButton[225];
    JButton hra = new JButton("Hrát");
    JButton lan = new JButton("Hra po LAN");
    boolean player1_turn;
    int xRada = 0;
    int oRada = 0;

    Piskvorky(){

        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(800,800);
        menu.setResizable(false);
        menu.setVisible(true);
        menu.setLayout(new BorderLayout());
        menu.setLocationRelativeTo(null);
        menu.getContentPane().setBackground(new Color(50,50,50));


        textfield1.setBackground(new Color(43, 135, 255));
        textfield1.setForeground(new Color(255, 255, 255));
        textfield1.setFont(new Font("SansSerif",Font.BOLD,65));
        textfield1.setHorizontalAlignment(JLabel.CENTER);
        textfield1.setVerticalAlignment(JLabel.TOP);
        textfield1.setText("Piškvorky");
        textfield1.setOpaque(true);
        menu.add(textfield1);

        lan.setBounds(300,450,200,100);
        lan.setFont((new Font("SansSerif",Font.BOLD,30)));
        lan.setBackground(new Color(255, 255, 255));
        lan.setForeground(new Color(43, 135, 255));
        textfield1.add(lan);

        hra.setBounds(300,300,200,100);
        hra.setFont((new Font("SansSerif",Font.BOLD,30)));
        hra.setBackground(new Color(255, 255, 255));
        hra.setForeground(new Color(43, 135, 255));
        textfield1.add(hra);


        hra.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(true);
                menu.setVisible(false);
            }
        });




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(false);

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
                    if (buttons[i].getText().equals("")){
                        buttons[i].setForeground(new Color(0,0,0));
                        buttons[i].setText("X");
                        player1_turn=false;
                        textfield.setText("O turn");
                        check(i, "X");
                    }

                }
                else {
                    if (buttons[i].getText().equals("")){
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
        checkPrava(tlacitko, znak);
        checkHore(tlacitko, znak);
        checkDole(tlacitko, znak);
        checkHorePrava(tlacitko, znak);
        checkHoreLeva(tlacitko, znak);
        checkDolePrava(tlacitko, znak);
        checkDoleLeva(tlacitko, znak);
    }
    public void checkLeva(int tlacitko, String znak){
        if(tlacitko != 0 && tlacitko != 15 && tlacitko != 30 && tlacitko != 45 && tlacitko != 60 && tlacitko != 75 && tlacitko != 90 && tlacitko != 105 && tlacitko != 120 && tlacitko != 135 && tlacitko != 150 && tlacitko != 165 && tlacitko != 180 && tlacitko != 195 && tlacitko != 210){
            tlacitko--;
        }
        if (){
            tlacitko--;
        }
        else return;
        System.out.println(buttons[tlacitko].getText() + ", " + tlacitko);
        if (buttons[tlacitko].getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkLeva(tlacitko, znak);
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkPrava(int tlacitko, String znak){
        if(tlacitko > 0){
            tlacitko++;
        }
        else return;
        System.out.println(buttons[tlacitko].getText() + ", " + tlacitko);
        if (buttons[tlacitko].getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkPrava(tlacitko, znak);
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkHore(int tlacitko, String znak){
        if(tlacitko > 14){
            tlacitko -= 15;
        }
        else return;
        System.out.println(buttons[tlacitko].getText() + ", " + tlacitko);
        if (buttons[tlacitko].getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkHore(tlacitko, znak);
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkDole(int tlacitko, String znak){
        if(tlacitko < 210){
            tlacitko += 15;
        }
        else return;
        System.out.println(buttons[tlacitko].getText() + ", " + tlacitko);
        if (buttons[tlacitko].getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkDole(tlacitko, znak);
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkHorePrava(int tlacitko, String znak){
        if(tlacitko > 0){
            tlacitko -= 14;
        }
        else return;
        System.out.println(buttons[tlacitko].getText() + ", " + tlacitko);
        if (buttons[tlacitko].getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkHorePrava(tlacitko, znak);
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkDolePrava(int tlacitko, String znak){
        if(tlacitko > 0){
            tlacitko += 16;
        }
        else return;
        System.out.println(buttons[tlacitko].getText() + ", " + tlacitko);
        if (buttons[tlacitko].getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkDolePrava(tlacitko, znak);
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkHoreLeva(int tlacitko, String znak){
        if(tlacitko > 0){
            tlacitko -= 16;
        }
        else return;
        System.out.println(buttons[tlacitko].getText() + ", " + tlacitko);
        if (buttons[tlacitko].getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkHoreLeva(tlacitko, znak);
            oRada = 0;
            xRada = 0;
        }
        else return;
    }

    public void checkDoleLeva(int tlacitko, String znak){
        if(tlacitko > 0){
            tlacitko += 14;
        }
        else return;
        System.out.println(buttons[tlacitko].getText() + ", " + tlacitko);
        if (buttons[tlacitko].getText().equals(znak)){
            if (znak.equals("X")) xWins();
            else oWins();
            checkDoleLeva(tlacitko, znak);
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

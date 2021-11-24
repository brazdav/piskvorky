import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Piskvorky extends MyButtons implements FirstTurn{

    JFrame frame = new JFrame();
    JFrame menu = new JFrame();
    JPanel title_panel = new JPanel();


    JLabel textfield1 = new JLabel();
    JButton hra = new JButton("Hrát");
    JButton lan = new JButton("Hra po LAN");

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double height = screenSize.getHeight() - 35;


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
        frame.setSize((int) height,(int) height);
        frame.setResizable(false);
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
        title_panel.setBounds(0,0,800,1000);

        button_panel.setLayout(new GridLayout(15,15));
        button_panel.setBackground(new Color(150,150,150));

        for (int i = 0; i < 225; i++){
            vytvoreni(String.valueOf(i));
        }

        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        FirstTurn.firstTurn();

    }



}

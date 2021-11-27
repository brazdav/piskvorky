import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Piskvorky extends MyButtons implements FirstTurn{

    JFrame frame = new JFrame();
    JFrame menu = new JFrame();
    JPanel title_panel = new JPanel();


    ImageIcon lanImage= new ImageIcon(getClass().getResource("button.png"));
    ImageIcon hraImage= new ImageIcon(getClass().getResource("hra.png"));

    JLabel textfield1 = new JLabel();
    JButton hra = new JButton(hraImage);
    JButton lan = new JButton(lanImage);

    ImageIcon logoImage= new ImageIcon(getClass().getResource("tiktak.png"));
    JLabel logoLabel = new JLabel(logoImage);

    ArrayList buttons = new ArrayList<JButton>();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double height = screenSize.getHeight() - 35;

    public Piskvorky(){

        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(800,600);
        menu.setResizable(false);
        menu.setVisible(true);
        menu.setLayout(new BorderLayout());
        menu.setLocationRelativeTo(null);
        menu.getContentPane().setBackground(new Color(50,50,50));

        logoLabel.setBounds(10,10,150,150);
        textfield1.add(logoLabel);


        textfield1.setBackground(new Color(43, 135, 255));
        textfield1.setForeground(new Color(255, 255, 255));
        textfield1.setFont(new Font("SansSerif",Font.BOLD,65));
        textfield1.setHorizontalAlignment(JLabel.CENTER);
        textfield1.setVerticalAlignment(JLabel.TOP);
        textfield1.setText("Piškvorky");
        textfield1.setOpaque(true);
        menu.add(textfield1);

        lan.setBounds(250,375,300,100);
        lan.setOpaque(false);
        lan.setContentAreaFilled(false);
        lan.setBorderPainted(false);
        lan.setFocusable(false);
        lan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textfield1.add(lan);

        hra.setBounds(250,250,300,100);
        hra.setOpaque(false);
        hra.setContentAreaFilled(false);
        hra.setBorderPainted(false);
        hra.setFocusable(false);
        hra.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textfield1.add(hra);


        hra.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                frame.setVisible(true);
                menu.setVisible(false);
            }
        });




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) height,(int) height);
        double sirka = (screenSize.getWidth()-frame.getSize().width)/2;
        System.out.print(sirka);
        frame.setLocation((int) sirka, 0);
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
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(15,15));
        button_panel.setBackground(new Color(150,150,150));

        for (int i = 0; i < 225; i++) {
            MyButtons obj = new MyButtons();
            button_panel.add(obj);
            buttons.add(obj);
            obj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    vykresleni(obj);

                    checkLeva(obj, buttons);
                    checkPrava(obj, buttons);
                    checkHore(obj, buttons);
                    checkDole(obj, buttons);
                    checkLevaHore(obj, buttons);
                    checkPravaDole(obj, buttons);
                    checkLevaDole(obj, buttons);
                    checkPravaHore(obj, buttons);
                }
            });
        }

        title_panel.add(textfield);
        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

        FirstTurn.firstTurn();

    }

}

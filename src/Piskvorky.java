import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class Piskvorky extends MyButtons implements FirstTurn,Music{
    JFrame menu = new JFrame();
    JPanel title_panel = new JPanel();

    ImageIcon lanImage= new ImageIcon(getClass().getResource("button.png"));
    ImageIcon hraImage= new ImageIcon(getClass().getResource("hra.png"));
    ImageIcon logoImage= new ImageIcon(getClass().getResource("tiktak.png"));
    ImageIcon soundImage1 = new ImageIcon(getClass().getResource("sound.png"));
    ImageIcon soundImage2 = new ImageIcon(getClass().getResource("mute.png"));

    JLabel textfield1 = new JLabel();

    JButton hra;
    JButton lan;
    JLabel logoLabel;
    JButton sound;

    ArrayList buttons = new ArrayList<JButton>();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double height = screenSize.getHeight() - 35;

    boolean turn = true;
    Clip clip;

    public Piskvorky() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        clip = Music.nacteni("Beethoven.wav");
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();

        try{
            hra = new JButton(hraImage);
            lan = new JButton(lanImage);
            logoLabel = new JLabel(logoImage);
            sound = new JButton(soundImage1);
        }
        catch (Exception e){
            System.out.println("špátná cesta k souboru obrázku");
        }
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

        sound.setBounds(690,475,100,100);
        sound.setOpaque(false);
        sound.setContentAreaFilled(false);
        sound.setBorderPainted(false);
        sound.setFocusable(false);
        sound.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textfield1.add(sound);


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



        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn) {
                    clip.stop();
                    turn = false;
                    sound.setIcon(soundImage2);
                }
                else {
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    sound.setIcon(soundImage1);
                    turn = true;
                }
            }
        });

        hra.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                start();
                menu.setVisible(false);
                clip.stop();
            }
        });


    }

    public void start (){
        JFrame frame = new JFrame();
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) height,(int) height);
        double sirka = (screenSize.getWidth()-frame.getSize().width)/2;

        frame.setLocation((int) sirka, 0);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());

        frame.add(title_panel,BorderLayout.NORTH);
        frame.add(button_panel);

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
                    if (vyhra){
                        vyhra = false;
                        button_panel.removeAll();
                        buttons.removeAll(buttons);
                        //frame.dispose();
                        start();

                    }
                }
            });
        }
        title_panel.add(textfield);


        FirstTurn.firstTurn();
    }


}


package AI;

import Spousteni_hry.Start;
import Tvoreni_menu.Piskvorky;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MenuAI implements ActionListener{

    ImageIcon zacatecnik = new ImageIcon(getClass().getResource("/Obrazky/zacatecnik.png"));
    ImageIcon pokrocily = new ImageIcon(getClass().getResource("/Obrazky/pokrocily.png"));

    private JFrame frame = new JFrame("Menu");
    private JButton b1 = new JButton(zacatecnik);
    private JButton b2 = new JButton(pokrocily);
    private Piskvorky obj;
    private JLabel vzhled;
    private JButton zpatky;




    public MenuAI(Piskvorky obj){

        this.obj = obj;

        zpatky = new JButton(obj.zpet);

        b1.addActionListener(this);
        b2.addActionListener(this);

        vzhled = new JLabel();

        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(50,50,50));


        vzhled.setBackground(new Color(43, 135, 255));
        vzhled.setForeground(new Color(255, 255, 255));
        vzhled.setFont(new Font("SansSerif",Font.BOLD,65));
        vzhled.setHorizontalAlignment(JLabel.CENTER);
        vzhled.setVerticalAlignment(JLabel.TOP);
        vzhled.setText("Piškvorky");
        vzhled.setOpaque(true);
        frame.add(vzhled);


        b1.setBounds(275,250,250,100);
        b1.setOpaque(false);
        b1.setContentAreaFilled(false);
        b1.setBorderPainted(false);
        b1.setFocusable(false);
        b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        vzhled.add(b1);

        b2.setBounds(275,375,250,100);
        b2.setOpaque(false);
        b2.setContentAreaFilled(false);
        b2.setBorderPainted(false);
        b2.setFocusable(false);
        b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        vzhled.add(b2);

        zpatky.setBounds(10,500,50,50);
        zpatky.setOpaque(false);
        zpatky.setContentAreaFilled(false);
        zpatky.setBorderPainted(false);
        zpatky.setFocusable(false);
        zpatky.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        zpatky.setVisible(true);
        vzhled.add(zpatky);

        frame.setVisible(true);

        zpatky.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent f){
                try {
                    Piskvorky obj = new Piskvorky();
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    e.printStackTrace();
                }
                frame.dispose();
            }
        });



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        String st;
        Start start = null;
        try {
            start = new Start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            ex.printStackTrace();
        }
        if (e.getSource() == b1)
            st = "Lehčí";
        else
            st = "Těžší";
        start.start(obj, st);
    }
}

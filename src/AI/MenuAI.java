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

/**
 * Třídá MenuAI implemetuje rozhraní ActionListener, aby bylo možné přepsat abstraktní metodu actionPerformed a ta byla přiřazena dvoum tlačítkům
 * Obsahuje konstruktor MenuAI, který vytváří menu pro spouštění hry s umělou inteligencí
 */
public class MenuAI implements ActionListener{

    ImageIcon zacatecnik = new ImageIcon(getClass().getResource("/Obrazky/zacatecnik.png"));
    ImageIcon pokrocily = new ImageIcon(getClass().getResource("/Obrazky/pokrocily.png"));

    private JFrame frame = new JFrame("Menu");
    private JButton b1 = new JButton(zacatecnik);
    private JButton b2 = new JButton(pokrocily);
    private Piskvorky obj;
    private JLabel vzhled = new JLabel();
    private JButton zpatky;


    /**
     * Konstruktor MenuAI si ukládá do vlastní globální proměnné instanci třídy Piskvorky
     * Následně vytvoří tlačítko s ikonou zpět, na tlačítka b1 a b2 nastaví implementovaný ActionListener
     * Nastavuje vlastnosti JFrame frame, na který se bude vázat JLabel vzhled
     * Nastavuje vlastnosti JLable vzhled, na který se následovně vykreslí tlačítka a je přidán na JFrame frame
     * Nastavuje vlastnosti tlačítek b1 a b2, které jsou přidány na JLabel vzhled
     * Nastavuje tlačítko zpet, které slouží k vracení se na předchozí frame, nakonec je taktéž přidáno na JLabel vzhled
     * @param obj instance třídy Piskvorky
     */
    public MenuAI(Piskvorky obj){

        this.obj = obj;

        zpatky = new JButton(obj.zpet);

        b1.addActionListener(this);
        b2.addActionListener(this);

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

package LAN;

import Rozhrani.FirstTurn;
import Tvoreni_menu.Piskvorky;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class SettingUpClient implements ActionListener{
    JFrame dialogy;
    JTextField ipaddress;
    String adresa;
    JLabel barva;
    JLabel nazev;
    JButton zpatky;
    JButton send;
    Piskvorky piskvorky;

    public SettingUpClient(Piskvorky piskvorky)throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.piskvorky = piskvorky;

        dialogy = new JFrame("Zapnutí clienta");
        zpatky = new JButton(piskvorky.zpet);
        nazev = new JLabel("Zadej IP serveru:");
        barva = new JLabel();
        ipaddress = new JTextField();
        send = new JButton("Spustit");

        dialogy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialogy.setSize(250,250);
        dialogy.setResizable(false);
        dialogy.setVisible(true);
        dialogy.setLayout(new BorderLayout());
        dialogy.setLocationRelativeTo(null);
        dialogy.getContentPane().setBackground(new Color(50,50,50));

        barva.setBackground(new Color(43, 135, 255));
        barva.setForeground(new Color(255, 255, 255));
        barva.setFont(new Font("SansSerif",Font.BOLD,65));
        barva.setHorizontalAlignment(JLabel.CENTER);
        barva.setVerticalAlignment(JLabel.TOP);
        barva.setOpaque(true);

        nazev.setBounds(45,30,150,20);
        nazev.setFont(new Font("SansSerif",Font.BOLD,18));
        nazev.setBackground(new Color(43, 135, 255));
        nazev.setForeground(new Color(255, 255, 255));

        ipaddress.setBounds(45,70,150,30);
        ipaddress.setOpaque(true);

        send.setBounds(72,125,100,50);
        send.setOpaque(false);
        send.addActionListener(this);

        zpatky.setBounds(0,160,50,50);
        zpatky.setOpaque(false);
        zpatky.setContentAreaFilled(false);
        zpatky.setBorderPainted(false);
        zpatky.setFocusable(false);
        zpatky.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        zpatky.setVisible(true);
        zpatky.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent f){
                piskvorky.menu.setVisible(true);
                dialogy.dispose();
            }
        });

        barva.add(nazev);
        barva.add(ipaddress);
        barva.add(send);
        barva.add(zpatky);

        dialogy.add(barva);
        dialogy.setVisible(true);
        piskvorky.menu.setVisible(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        FirstTurn.firstTurn();
        FirstTurn.firstTurnLan();
        dialogy.dispose();
        adresa = ipaddress.getText();
        System.out.print(adresa);
        try {
            Client client = new Client(adresa, 6669, piskvorky);
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        }
    }

}


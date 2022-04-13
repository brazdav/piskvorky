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
import java.net.InetAddress;
import java.net.UnknownHostException;

public class SettingUpServer implements ActionListener{
    JFrame dialogy;
    JRadioButton d1;
    JRadioButton d2;
    JRadioButton d3;
    ButtonGroup dg;
    JLabel pocetK1;
    JLabel ipaddress;
    JLabel barva;
    JButton zpatky;


    JButton send;
    Piskvorky piskvorky;
    public SettingUpServer(Piskvorky piskvorky)throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.piskvorky = piskvorky;

        dialogy = new JFrame("Dialogy");

        zpatky = new JButton(piskvorky.zpet);

        barva = new JLabel();
        ipaddress = new JLabel("IP: " + getAdress());
        // create a dialog Box




        // create a label

        pocetK1 = new JLabel("Počet kol:");
        JLabel aa = new JLabel();
        d1 = new JRadioButton("1");
        d2 = new JRadioButton("3");
        d3 = new JRadioButton("5");
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


        ipaddress.setBackground(new Color(43, 135, 255));
        ipaddress.setForeground(new Color(255, 255, 255));
        ipaddress.setBounds(45,20,200,30);
        ipaddress.setFont(new Font("SansSerif",Font.BOLD,18));

        pocetK1.setBounds(20,70,80,30);
        send.setBounds(72,125,100,50);
        send.setOpaque(false);

        zpatky.setBounds(0,160,50,50);
        zpatky.setOpaque(false);
        zpatky.setContentAreaFilled(false);
        zpatky.setBorderPainted(false);
        zpatky.setFocusable(false);
        zpatky.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        zpatky.setVisible(true);



        //pocetK1.setBounds(40, 40, 100, 30);


        d1.setBounds(80,70,40,30);
        d2.setBounds(120,70,40,30);
        d3.setBounds(160,70,40,30);

        d1.setBackground(new Color(0,0,0,0));
        d2.setBackground(new Color(0,0,0,0));
        d3.setBackground(new Color(0,0,0,0));
        d1.setOpaque(false);
        d2.setOpaque(false);
        d3.setOpaque(false);
        d1.setSelected(true);
        d1.setFocusable(false);
        d2.setFocusable(false);
        d3.setFocusable(false);

        dg = new ButtonGroup();
        dg.add(d1);dg.add(d2);dg.add(d3);

        barva.add(ipaddress);
        barva.add(d1);barva.add(d2);barva.add(d3);
        barva.add(pocetK1);
        barva.add(send);
        barva.add(zpatky);

        send.addActionListener(this);


        dialogy.add(barva);

        dialogy.setVisible(true);

        piskvorky.menu.setVisible(false);

        zpatky.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent f){
                piskvorky.menu.setVisible(true);
                dialogy.dispose();
            }
        });
    }




    @Override
    public void actionPerformed(ActionEvent e) {
        FirstTurn.firstTurn();
        FirstTurn.firstTurnLan();

        piskvorky.kolaLan = piskvorky.pocetKol(d1,d2,d3);

        dialogy.setVisible(false);


        try {
            Server server = new Server(piskvorky);
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        } catch (LineUnavailableException lineUnavailableException) {
            lineUnavailableException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public String getAdress() {

        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        return ip.getHostAddress();
    }
}


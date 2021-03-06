package Tvoreni_menu;

import AI.MenuAI;
import LAN.SettingUpClient;
import LAN.SettingUpServer;
import Rozhrani.FirstTurn;
import Rozhrani.Music;
import Spousteni_hry.Vykresleni;
import Uprava_tlacitka.MyButtons;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Třída Piškvorky dědí z třídy MyButtons a implementuje rozhraní Music
 * Obsahuje kontruktor, který tvoří celé základní menu. Obsahuje také 2 metody pocetKol() a getAdress().
 * 5 tlačítek na kterých jsou přidané různé události
 * @author Simon Valeš, Vojtěch Brázda
 * @version 1.0.0
 *
 *
 **/
public class Piskvorky extends MyButtons implements Music {
    public JFrame menu = new JFrame();
    public JPanel title_panel = new JPanel();

    ImageIcon lanImage= new ImageIcon(getClass().getResource("/Obrazky/button.png"));
    ImageIcon hraImage= new ImageIcon(getClass().getResource("/Obrazky/hra.png"));
    ImageIcon clientImage = new ImageIcon(getClass().getResource("/Obrazky/client.png"));
    ImageIcon serverImage = new ImageIcon(getClass().getResource("/Obrazky/server.png"));
    ImageIcon logoImage= new ImageIcon(getClass().getResource("/Obrazky/tiktak.png"));
    public ImageIcon soundImage1 = new ImageIcon(getClass().getResource("/Obrazky/sound.png"));
    public ImageIcon soundImage2 = new ImageIcon(getClass().getResource("/Obrazky/mute.png"));
    public ImageIcon zpet = new ImageIcon(getClass().getResource("/Obrazky/zpet.png"));

    public JLabel textfield1 = new JLabel();

    public JButton hra;
    public JButton lan;
    JLabel logoLabel;
    public JButton sound;
    public JButton server = new JButton(serverImage);
    public JButton client = new JButton(clientImage);
    public JButton zpatky = new JButton(zpet);

    public boolean turn_music;

    public JRadioButton r1;
    public JRadioButton r2;
    public JRadioButton r3;
    ButtonGroup bg;
    public JLabel pocetK;
    public int kola;
    public int kolaLan;
    public Clip win;
    public Clip lose;
    public Clip waiting;
    private String adresa;
    Piskvorky piskvorky = this;


    /**
     * Konstruktor třídy Piškvorky
     * Zde tvoříme celý základ naší aplikace - menu a jeho prvky
     * Hudba, ikony, tlačítka
     * Taky nastavuje label pro počítání vyhraných kol
     *
    * */
    public Piskvorky(boolean turn) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.turn_music = turn;

        win = nacteni("win.wav");
        lose = nacteni("lose.wav");
        waiting = nacteni("waiting.wav");

        try{
            hra = new JButton(hraImage);
            lan = new JButton(lanImage);
            logoLabel = new JLabel(logoImage);
            if (turn)
                sound = new JButton(soundImage1);
            else
                sound = new JButton(soundImage2);
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

        FirstTurn.textfield.setBackground(new Color(43, 135, 255));
        FirstTurn.textfield.setForeground(new Color(255, 255, 255));
        FirstTurn.textfield.setFont(new Font("SansSerif",Font.BOLD,75));
        FirstTurn.textfield.setHorizontalAlignment(JLabel.CENTER);
        FirstTurn.textfield.setText("Piškvorky");
        FirstTurn.textfield.setOpaque(true);
        FirstTurn.textfield.setLayout(new BorderLayout());

        client.setBounds(275,375,250,80);
        client.setVisible(true);
        client.setOpaque(false);
        client.setContentAreaFilled(false);
        client.setBorderPainted(false);
        client.setFocusable(false);
        client.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        server.setBounds(275,250,250,80);
        server.setVisible(true);
        server.setOpaque(false);
        server.setContentAreaFilled(false);
        server.setBorderPainted(false);
        server.setFocusable(false);
        server.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        zpatky.setBounds(10,500,50,50);
        zpatky.setOpaque(false);
        zpatky.setContentAreaFilled(false);
        zpatky.setBorderPainted(false);
        zpatky.setFocusable(false);
        zpatky.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        zpatky.setVisible(false);
        textfield1.add(zpatky);

        textfield1.add(client);
        textfield1.add(server);

        client.setVisible(false);
        server.setVisible(false);

        adresa = getAdress();



        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                menu.setVisible(false);
                try {
                    SettingUpClient setClient = new SettingUpClient(piskvorky);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    ex.printStackTrace();
                }
            }
        });

        server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                menu.setVisible(false);
                try {
                    SettingUpServer dialogs = new SettingUpServer(piskvorky);
                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        button_panel.setLayout(new GridLayout(15,15));
        button_panel.setBackground(new Color(150,150,150));

        pocetK = new JLabel("Počet kol:");
        pocetK.setBounds(280,500,100,30);
        textfield1.add(pocetK);

        r1 = new JRadioButton("1");
        r2 = new JRadioButton("3");
        r3 = new JRadioButton("5");
        r1.setBounds(340,500,50,30);
        r2.setBounds(390,500,50,30);
        r3.setBounds(440,500,50,30);
        r1.setBackground(new Color(0,0,0,0));
        r2.setBackground(new Color(0,0,0,0));
        r3.setBackground(new Color(0,0,0,0));
        r1.setOpaque(false);
        r2.setOpaque(false);
        r3.setOpaque(false);
        r1.setSelected(true);
        r1.setFocusable(false);
        r2.setFocusable(false);
        r3.setFocusable(false);



        bg = new ButtonGroup();
        bg.add(r1);bg.add(r2);bg.add(r3);
        textfield1.add(r1);textfield1.add(r2);textfield1.add(r3);





        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (turn_music) {
                    turn_music = false;
                    sound.setIcon(soundImage2);
                } else {
                    sound.setIcon(soundImage1);
                    turn_music = true;
                }
            }
        });

        hra.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                kola = pocetKol(r1,r2,r3);
                MenuAI obj = new MenuAI(piskvorky);
                menu.dispose();
            }
        });

        lan.addActionListener(new ActionListener(){//potreba dve tlacitka, kde se nastavuje turn
            public void actionPerformed(ActionEvent e){
                Vykresleni obj = new Vykresleni();
                lan.setVisible(false);
                hra.setVisible(false);
                r1.setVisible(false);
                r2.setVisible(false);
                r3.setVisible(false);
                pocetK.setVisible(false);

                zpatky.setVisible(true);

                client.setVisible(true);
                server.setVisible(true);

            }
        });

        zpatky.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                lan.setVisible(true);
                hra.setVisible(true);

                pocetK.setVisible(true);
                r1.setVisible(true);
                r2.setVisible(true);
                r3.setVisible(true);

                zpatky.setVisible(false);

                client.setVisible(false);
                server.setVisible(false);
            }
        });
    }

    /**
     * Metoda pocetKol počítá kolik kol se má odehrát podle vybraného radio buttonu
     * Vrací číslo, které nám udává kolikrát se odehraje hra
     * @param r1 JRadioButton vstupní parametr, selektor pro hodnotu 1
     * @param r2 JRadioButton vstupní parametr, selektor pro hodnotu 3
     * @param r3 JRadioButton vstupní parametr, selektor pro hodnotu 5
     * @return číslo z vybraného radio buttonu
     * 3 možnosti - 1 kolo, 3 kola a 5 kol
     * základně je nastaveno 1 kolo
     * */
    public int pocetKol(JRadioButton r1, JRadioButton r2, JRadioButton r3){
        if (r1.isSelected()){
            return (1);
        }
        else if (r2.isSelected()){
            return (3);
        }
        else if (r3.isSelected()){
            return (5);
        }
        return 0;
    }
    /**
     * Metoda getAdress zjišťuje adresu PC na kterým je zaplý server od LAN
     * @return lokolní IP adresu
    * */
    public String getAdress() {

        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        return ip.toString();
    }


}
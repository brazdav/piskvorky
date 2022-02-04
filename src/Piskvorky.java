
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


public class Piskvorky extends MyButtons implements Music{
    JFrame menu = new JFrame();
    JPanel title_panel = new JPanel();

    ImageIcon lanImage= new ImageIcon(getClass().getResource("button.png"));
    ImageIcon hraImage= new ImageIcon(getClass().getResource("hra.png"));
    ImageIcon clientImage = new ImageIcon(getClass().getResource("client.png"));
    ImageIcon serverImage = new ImageIcon(getClass().getResource("server.png"));
    ImageIcon logoImage= new ImageIcon(getClass().getResource("tiktak.png"));
    ImageIcon soundImage1 = new ImageIcon(getClass().getResource("sound.png"));
    ImageIcon soundImage2 = new ImageIcon(getClass().getResource("mute.png"));

    JLabel textfield1 = new JLabel();

    JButton hra;
    JButton lan;
    JLabel logoLabel;
    JButton sound;
    JButton server = new JButton(serverImage);
    JButton client = new JButton(clientImage);


    JLabel vyhranaKola = new JLabel("Vyhrana kola:");





    boolean turn = true;
    Clip clip;

    JRadioButton r1;
    JRadioButton r2;
    JRadioButton r3;
    ButtonGroup bg;
    JLabel pocetK;
    int kola;

    private String adresa;




    public Piskvorky() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        clip = Music.nacteni("Adventure.wav");
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.stop();

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


        vyhranaKola.setBounds(790,60,250,40);
        vyhranaKola.setText("Vyhrana kola: X: " + winX + "  O: " + winO);
        vyhranaKola.setFont(new Font("SansSerif",Font.BOLD,20));
        vyhranaKola.setForeground(new Color(255, 255, 255));

        textfield.setBackground(new Color(43, 135, 255));
        textfield.setForeground(new Color(255, 255, 255));
        textfield.setFont(new Font("SansSerif",Font.BOLD,75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Piškvorky");
        textfield.setOpaque(true);
        textfield.add(vyhranaKola);

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
                    SettingUpClient setClient = new SettingUpClient();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                /*try {
                    Client client = new Client("192.168.0.94", 6669);
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }*/
            }
        });

        server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                menu.setVisible(false);
                try {
                    SettingUpServer dialogs = new SettingUpServer();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                /*
                FirstTurn.firstTurn();
                FirstTurn.firstTurnLan();
                try {
                    Server server = new Server();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }*/
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
                pocetKol();
                Start obj = null;
                try {
                    obj = new Start();
                } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                } catch (LineUnavailableException lineUnavailableException) {
                    lineUnavailableException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                obj.start();
                menu.dispose();
                clip.stop();
            }
        });

        lan.addActionListener(new ActionListener(){//potreba dve tlacitka, kde se nastavuje turn
            public void actionPerformed(ActionEvent e){
                Vykresleni obj = new Vykresleni();
                clip.stop();


                lan.setVisible(false);
                hra.setVisible(false);
                r1.setVisible(false);
                r2.setVisible(false);
                r3.setVisible(false);
                pocetK.setVisible(false);

                client.setVisible(true);
                server.setVisible(true);

            }
        });


    }


    public void pocetKol(){
        if (r1.isSelected()){
            kola = 1;
        }
        else if (r2.isSelected()){
            kola = 3;
        }
        else if (r3.isSelected()){
            kola = 5;
        }
    }

    public String getAdress() {

        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println("Your current IP address : " + ip);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        return ip.toString();
    }


}

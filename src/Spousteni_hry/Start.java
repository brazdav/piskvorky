package Spousteni_hry;

import LAN.Server;
import Rozhrani.FirstTurn;
import Tvoreni_menu.Piskvorky;
import Uprava_tlacitka.MyButtons;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Třída Start implementuje rozhraní FirstTurn
 * Obsahuje konstruktor a 4 metody, slouží k zapnutí hry
 * @author Vojtěch Brázda, Simon Valeš
 * @version 1.0.0
 *
 * */
public class Start implements FirstTurn{
    public int indexTlaco;
    private double height;
    private Dimension screenSize;
    private Server server;
    private String check = "check";
    public ArrayList buttons = new ArrayList<JButton>();
    private static JLabel vyhranaKola = new JLabel();
    private Piskvorky piskvorky;
    public static String server_znak;
    private String lan;
    public Start(Piskvorky obj, String lan) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.lan = lan;
        this.piskvorky = obj;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = screenSize.getHeight() - 35;
        vyhranaKola.setText("Vyhrana kola: X: " + obj.winX + "  O: " + obj.winO);
        vyhranaKola.setFont(new Font("SansSerif",Font.BOLD,20));
        vyhranaKola.setForeground(new Color(255, 255, 255));
        vyhranaKola.setHorizontalAlignment(JLabel.RIGHT);
        FirstTurn.textfield.add(vyhranaKola, BorderLayout.PAGE_END);
    }

    /**
     * Metoda start nám zapíná hru proti AI
     * Vytvoří okno, dá nám ho doprostřed obrazovky s danými rozměry, které nejdou měnit
     * Zároveň vykreslí všechny tlačítka na hrací pole
     * Nastavuje v pravém rohu okna label s počtem vyhraných kol pro X nebo O
     * @param ai
     */
    public void start (String ai){

        piskvorky.getString("ai");
        JFrame frame = new JFrame();
        frame.setVisible(true);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) height,(int) height);
        double sirka = (screenSize.getWidth()-frame.getSize().width)/2;

        frame.setLocation((int) sirka, 0);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());

        frame.add(piskvorky.title_panel,BorderLayout.NORTH);
        frame.add(piskvorky.button_panel);

        for (int i = 0; i < 225; i++) {
            MyButtons obj = new MyButtons();
            piskvorky.button_panel.add(obj);
            buttons.add(obj);
            obj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (obj.getText().equals("")) {
                        Vykresleni vykresleni = new Vykresleni();
                        if(ai.equals("Lehčí"))
                            vykresleni.vykresleniEz(obj, piskvorky);
                        else
                            vykresleni.vykresleniHard(obj, piskvorky);

                        piskvorky.checkLeva(obj, buttons, check);
                        piskvorky.checkPrava(obj, buttons, check);
                        piskvorky.checkHore(obj, buttons, check);
                        piskvorky.checkDole(obj, buttons, check);
                        piskvorky.checkLevaHore(obj, buttons, check);
                        piskvorky.checkPravaDole(obj, buttons, check);
                        piskvorky.checkLevaDole(obj, buttons, check);
                        piskvorky.checkPravaHore(obj, buttons, check);
                        if (piskvorky.vyhra) {
                            vyhranaKola.setText("Vyhrana kola: X:" + piskvorky.winX + "  O:" + piskvorky.winO);
                            piskvorky.kola--;
                            piskvorky.vyhra = false;
                            buttons.removeAll(buttons);
                            if (piskvorky.kola > 0) {
                                piskvorky.button_panel.removeAll();
                                frame.dispose();
                                start(ai);
                            } else {
                                if (piskvorky.winX > piskvorky.winO) {
                                    JOptionPane.showMessageDialog(null, "Konec hry, vyhral X");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Konec hry, vyhral O");
                                }
                                try {
                                    frame.dispose();
                                    Piskvorky piskvorky1 = new Piskvorky();
                                } catch (UnsupportedAudioFileException ex) {
                                    ex.printStackTrace();
                                } catch (LineUnavailableException ex) {
                                    ex.printStackTrace();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }

                            }
                        }
                    }
                }
            });
        }
        piskvorky.title_panel.add(piskvorky.textfield);
        FirstTurn.firstTurnAI();
        piskvorky.getList(buttons);
    }

    /**
     * Metoda startLan dělá skoro to samé jako metoda start, akorát pro hru po Lan
     * Nepoužívá se zde AI
     * Zároveň vykreslí všechny tlačítka na hrací pole
     * Nastavuje v pravém rohu okna label s počtem vyhraných kol pro X nebo O
     */
    public void startLan (){
        piskvorky.getString("lan");
        JFrame frame = new JFrame();
        frame.setVisible(true);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize((int) height,(int) height);
        double sirka = (screenSize.getWidth()-frame.getSize().width)/2;

        frame.setLocation((int) sirka, 0);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());

        frame.add(piskvorky.title_panel,BorderLayout.NORTH);
        frame.add(piskvorky.button_panel);

        for (int i = 0; i < 225; i++) {
            MyButtons obj = new MyButtons();
            piskvorky.button_panel.add(obj);
            buttons.add(obj);
            obj.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Vykresleni vykresleni = new Vykresleni();
                    vykresleni.vykresleniLan(obj);
                    indexTlaco = buttons.indexOf(obj);

                    piskvorky.checkLeva(obj, buttons, check);
                    piskvorky.checkPrava(obj, buttons, check);
                    piskvorky.checkHore(obj, buttons, check);
                    piskvorky.checkDole(obj, buttons, check);
                    piskvorky.checkLevaHore(obj, buttons, check);
                    piskvorky.checkPravaDole(obj, buttons, check);
                    piskvorky.checkLevaDole(obj, buttons, check);
                    piskvorky.checkPravaHore(obj, buttons, check);
                    if (piskvorky.vyhra){
                        vyhranaKola.setText("Vyhrana kola: X:" + piskvorky.winX + "  O:" + piskvorky.winO);
                        piskvorky.kolaLan --;
                        piskvorky.vyhra = false;
                        buttons.removeAll(buttons);
                        if (piskvorky.kolaLan > 0) {
                            piskvorky.button_panel.removeAll();
                            frame.dispose();
                            startLan();
                        }
                        else {
                            if (server_znak.equals("X") && lan.equals("server") && (piskvorky.winX > piskvorky.winO)){
                                JOptionPane.showMessageDialog(frame, "Vyhrál jsi");
                            }
                            else if (server_znak.equals("X") && lan.equals("server") && (piskvorky.winX < piskvorky.winO)){
                                JOptionPane.showMessageDialog(frame, "Prohrál jsi");
                            }

                            if (server_znak.equals("X") && lan.equals("client") && (piskvorky.winX > piskvorky.winO)){
                                JOptionPane.showMessageDialog(frame, "Prohrál jsi");
                            }
                            else if (server_znak.equals("X") && lan.equals("client") && (piskvorky.winX < piskvorky.winO)){
                                JOptionPane.showMessageDialog(frame, "Vyhrál jsi");
                            }

                            piskvorky.winX = 0;
                            piskvorky.winO = 0;
                            vyhranaKola.setText("Vyhrana kola: X:" + piskvorky.winX + "  O:" + piskvorky.winO);
                            piskvorky.menu.setVisible(true);
                            piskvorky.sound.setIcon(piskvorky.soundImage1);
                            //piskvorky.clip.start();
                            try {
                                if(server != null) {
                                    server.sendEnd();
                                    server.end();
                                }
                                //Client client = new Client();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
        piskvorky.title_panel.add(piskvorky.textfield);

    }

    /**
     * Metoda getServer předává instanci třídy Server
     * @param server
     */
    public void getServer(Server server){
        this.server = server;
    }

    /**
     * Metoda getIndexTlaco vrací stringovou hodnotu z tlačítka
     * @return
     */
    public String getIndexTlaco(){
        return String.valueOf(indexTlaco);
    }

}

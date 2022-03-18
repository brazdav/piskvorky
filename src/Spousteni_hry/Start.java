package Spousteni_hry;

import LAN.Client;
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

public class Start implements FirstTurn{
    public int indexTlaco;
    private double height;
    private Dimension screenSize;
    private Server server;
    private Client client;
    public ArrayList buttons = new ArrayList<JButton>();
    public Start() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = screenSize.getHeight() - 35;
    }




    public void start (Piskvorky piskvorky){
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
                    Vykresleni vykresleni = new Vykresleni();
                    vykresleni.vykresleni(obj);

                    piskvorky.checkLeva(obj, buttons);
                    piskvorky.checkPrava(obj, buttons);
                    piskvorky.checkHore(obj, buttons);
                    piskvorky.checkDole(obj, buttons);
                    piskvorky.checkLevaHore(obj, buttons);
                    piskvorky.checkPravaDole(obj, buttons);
                    piskvorky.checkLevaDole(obj, buttons);
                    piskvorky.checkPravaHore(obj, buttons);
                    if (piskvorky.vyhra){
                        piskvorky.vyhranaKola.setText("Vyhrana kola: X:" + piskvorky.winX + "  O:" + piskvorky.winO);
                        piskvorky.kola --;
                        frame.dispose();
                        piskvorky.vyhra = false;
                        piskvorky.button_panel.removeAll();
                        buttons.removeAll(buttons);
                        if (piskvorky.kola > 0) {
                            start(piskvorky);
                        }
                        else {
                            if(piskvorky.winX > piskvorky.winO){
                                JOptionPane.showMessageDialog(frame, "Konec hry, vyhral X");
                            }else{
                                JOptionPane.showMessageDialog(frame, "Konec hry, vyhral O");
                            }
                            piskvorky.winX = 0;
                            piskvorky.winO = 0;
                            piskvorky.vyhranaKola.setText("Vyhrana kola: X:" + piskvorky.winX + "  O:" + piskvorky.winO);
                            piskvorky.menu.setVisible(true);
                            piskvorky.sound.setIcon(piskvorky.soundImage1);
                            piskvorky.clip.start();
                        }
                    }
                }
            });
        }
        piskvorky.title_panel.add(piskvorky.textfield);
        FirstTurn.firstTurnAI();
    }

    public void startLan (Piskvorky piskvorky){
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

                    piskvorky.checkLeva(obj, buttons);
                    piskvorky.checkPrava(obj, buttons);
                    piskvorky.checkHore(obj, buttons);
                    piskvorky.checkDole(obj, buttons);
                    piskvorky.checkLevaHore(obj, buttons);
                    piskvorky.checkPravaDole(obj, buttons);
                    piskvorky.checkLevaDole(obj, buttons);
                    piskvorky.checkPravaHore(obj, buttons);
                    if (piskvorky.vyhra){
                        piskvorky.vyhranaKola.setText("Vyhrana kola: X:" + piskvorky.winX + "  O:" + piskvorky.winO);
                        piskvorky.kola --;
                        frame.dispose();
                        piskvorky.vyhra = false;
                        piskvorky.button_panel.removeAll();
                        buttons.removeAll(buttons);
                        if (piskvorky.kola > 0) {
                            start(piskvorky);
                        }
                        else {
                            if(piskvorky.winX > piskvorky.winO){
                                JOptionPane.showMessageDialog(frame, "Konec hry, vyhral X");
                            }else{
                                JOptionPane.showMessageDialog(frame, "Konec hry, vyhral O");
                            }
                            piskvorky.winX = 0;
                            piskvorky.winO = 0;
                            piskvorky.vyhranaKola.setText("Vyhrana kola: X:" + piskvorky.winX + "  O:" + piskvorky.winO);
                            piskvorky.menu.setVisible(true);
                            piskvorky.sound.setIcon(piskvorky.soundImage1);
                            piskvorky.clip.start();
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

    public void getServer(Server server){
        this.server = server;
    }
    public String getIndexTlaco(){
        return String.valueOf(indexTlaco);
    }

}

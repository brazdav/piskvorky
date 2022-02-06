import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class Start extends Piskvorky{
    public int indexTlaco;
    private double height;
    private Dimension screenSize;
    public ArrayList buttons = new ArrayList<JButton>();
    public Start() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        height = screenSize.getHeight() - 35;
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
                    Vykresleni vykresleni = new Vykresleni();
                    vykresleni.vykresleni(obj);

                    checkLeva(obj, buttons);
                    checkPrava(obj, buttons);
                    checkHore(obj, buttons);
                    checkDole(obj, buttons);
                    checkLevaHore(obj, buttons);
                    checkPravaDole(obj, buttons);
                    checkLevaDole(obj, buttons);
                    checkPravaHore(obj, buttons);
                    if (vyhra){
                        vyhranaKola.setText("Vyhrana kola: X:" + winX + "  O:" + winO);
                        kola --;
                        frame.dispose();
                        vyhra = false;
                        button_panel.removeAll();
                        buttons.removeAll(buttons);
                        if (kola > 0) {
                            start();
                        }
                        else {
                            if(winX > winO){
                                JOptionPane.showMessageDialog(frame, "Konec hry, vyhral X");
                            }else{
                                JOptionPane.showMessageDialog(frame, "Konec hry, vyhral O");
                            }
                            winX = 0;
                            winO = 0;
                            vyhranaKola.setText("Vyhrana kola: X:" + winX + "  O:" + winO);
                            menu.setVisible(true);
                            sound.setIcon(soundImage1);
                            clip.start();
                        }
                    }
                }
            });
        }
        title_panel.add(textfield);


        FirstTurn.firstTurn();
    }

    public void startLan (){
        menu.dispose();
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

                    Vykresleni vykresleni = new Vykresleni();
                    vykresleni.vykresleniLan(obj);
                    indexTlaco = buttons.indexOf(obj);

                    checkLeva(obj, buttons);
                    checkPrava(obj, buttons);
                    checkHore(obj, buttons);
                    checkDole(obj, buttons);
                    checkLevaHore(obj, buttons);
                    checkPravaDole(obj, buttons);
                    checkLevaDole(obj, buttons);
                    checkPravaHore(obj, buttons);
                    if (vyhra){
                        vyhranaKola.setText("Vyhrana kola: X:" + winX + "  O:" + winO);
                        kola --;
                        frame.dispose();
                        vyhra = false;
                        button_panel.removeAll();
                        buttons.removeAll(buttons);
                        if (kola > 0) {
                            startLan();
                        }
                        else {
                            if(winX > winO){
                                JOptionPane.showMessageDialog(frame, "Konec hry, vyhral X");
                            }else{
                                JOptionPane.showMessageDialog(frame, "Konec hry, vyhral O");
                            }
                            winX = 0;
                            winO = 0;
                            vyhranaKola.setText("Vyhrana kola: X:" + winX + "  O:" + winO);
                            menu.setVisible(true);
                            sound.setIcon(soundImage1);
                            clip.start();
                        }
                    }
                }
            });
        }
        title_panel.add(textfield);

    }


    public String getIndexTlaco(){
        return String.valueOf(indexTlaco);
    }

}

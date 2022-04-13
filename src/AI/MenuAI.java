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
    private JFrame frame = new JFrame("Menu");
    private JButton b1 = new JButton("Lehčí");
    private JButton b2 = new JButton("Těžší");
    private Piskvorky obj;
    public MenuAI(Piskvorky obj){
        this.obj = obj;
        b1.addActionListener(this);
        b2.addActionListener(this);
        frame.add(b1);
        frame.add(b2);
        frame.setLayout(new FlowLayout());
        frame.setSize(500,500);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        JButton button;
        Start start = null;
        try {
            start = new Start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException ex) {
            ex.printStackTrace();
        }
        if (e.getSource() == b1)
            button = b1;
        else
            button = b2;
        start.start(obj, button.getText());
    }
}

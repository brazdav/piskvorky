import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Dialogs extends Piskvorky implements ActionListener{

    JRadioButton d1;
    JRadioButton d2;
    JRadioButton d3;
    ButtonGroup dg;
    JLabel pocetK1;


    JButton send;

    public Dialogs()throws UnsupportedAudioFileException, LineUnavailableException, IOException {


        JFrame dialogy = new JFrame("Dialogy");
        JLabel barva = new JLabel();

        // create a dialog Box
        JDialog d = new JDialog(menu, "dialog Box");



        // create a label

        pocetK1 = new JLabel("Počet kol:");
        JLabel aa = new JLabel();
        d1 = new JRadioButton("1");
        d2 = new JRadioButton("3");
        d3 = new JRadioButton("5");
        send = new JButton("Spustit");



        dialogy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialogy.setSize(800,600);
        dialogy.setResizable(false);
        dialogy.setVisible(true);
        dialogy.setLayout(new BorderLayout());
        dialogy.setLocationRelativeTo(null);
        dialogy.getContentPane().setBackground(new Color(50,50,50));


        // setsize of dialog

        dialogy.setLocation(500,500);
        // set visibility of dialog
        barva.setBackground(new Color(43, 135, 255));
        barva.setForeground(new Color(255, 255, 255));
        barva.setFont(new Font("SansSerif",Font.BOLD,65));
        barva.setHorizontalAlignment(JLabel.CENTER);
        barva.setVerticalAlignment(JLabel.TOP);
        barva.setOpaque(true);




        pocetK1.setBounds(20,120,80,30);
        send.setBounds(100,200,100,50);
        send.setOpaque(false);



        //pocetK1.setBounds(40, 40, 100, 30);


        d1.setBounds(80,120,40,30);
        d2.setBounds(120,120,40,30);
        d3.setBounds(160,120,40,30);

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


        dialogy.add(d1);d.add(d2);d.add(d3);
        dialogy.add(pocetK1);
        dialogy.add(send);

        send.addActionListener(this);


        dialogy.add(barva);

        dialogy.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        }
    }
}


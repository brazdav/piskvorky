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

    JButton send;
    Object piskvorky;
    public SettingUpClient(Piskvorky piskvorky)throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        this.piskvorky = piskvorky;

        dialogy = new JFrame("Dialogy");

        nazev = new JLabel("Zadej IP serveru:");
        barva = new JLabel();
        ipaddress = new JTextField();

        // create a dialog Box




        // create a label

        JLabel aa = new JLabel();
        send = new JButton("Spustit");



        dialogy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dialogy.setSize(250,250);
        dialogy.setResizable(false);
        dialogy.setVisible(true);
        dialogy.setLayout(new BorderLayout());
        dialogy.setLocationRelativeTo(null);
        dialogy.getContentPane().setBackground(new Color(50,50,50));


        // setsize of d;ialog

        dialogy.setLocation(500,500);
        // set visibility of dialog
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



        barva.add(nazev);
        barva.add(ipaddress);
        barva.add(send);

        send.addActionListener(this);


        dialogy.add(barva);

        dialogy.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        dialogy.setVisible(false);
        adresa = ipaddress.getText();
        System.out.print(adresa);
        try {
            Client client = new Client(adresa, 6669,(Piskvorky) piskvorky);
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        } catch (LineUnavailableException lineUnavailableException) {
            lineUnavailableException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}


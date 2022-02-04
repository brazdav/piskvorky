
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class ShowIP {


    private static Dialog d;

    public ShowIP() throws UnsupportedAudioFileException, LineUnavailableException, IOException{

        Frame f = new Frame();
        d = new Dialog(f , "Dialog Example", true);
        d.setLayout( new FlowLayout() );
        d.add( new Label (getAdress()));
        d.setSize(300,300);
        d.setVisible(true);

    }


    public String getAdress() {

        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
            System.out.println("Your current IP address : " + ip);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }
        return ip.getHostAddress();
    }
}

import Tvoreni_menu.Piskvorky;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Piskvorky piskvorky = new Piskvorky(true);
    }
}

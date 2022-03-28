package Rozhrani;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStream;

public interface Music {
    static Clip nacteni(String cesta){
        Clip clip = null;
        try
        {
            InputStream wavStream = Music.class.getResourceAsStream(cesta);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(wavStream);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        catch (Exception e){
            System.out.println("zvuk se nenačetl");
        }
        return clip;
    }


}

package Rozhrani;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.InputStream;
/**
 * Rozhraní Music spouští muziku v programu
 * Implementujeme toto rozhraní do třídy Piškvorky
 * Obsahuje metodu nacteni, která má návratovou hodnotu Clip
 * @author Vojtěch Brázda
 * @version 1.0.0
 *
 */

public interface Music {
    /**
     * Metoda nacteni načítá soubor typu .wav do instance rozhraní Clip
     * K získání souboru používá třídu InputStream, nastaví se soubor
     * Dále třída AudioInputStream, který převádí instanci třídy InputStreamu jako spustitelné audio
     * clip otevírá audioStream
     * @param cesta
     * @return
     */
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

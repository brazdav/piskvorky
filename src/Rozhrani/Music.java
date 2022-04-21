package Rozhrani;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Rozhraní Music spouští muziku v programu
 * Implementujeme toto rozhraní do třídy Piškvorky
 * Obsahuje metodu nacteni, která má návratovou hodnotu Clip
 * @author Vojtěch Brázda
 * @version 1.0.0
 *
 */

public interface Music{
    /**
     * Metoda nacteni načítá soubor typu .wav do instance rozhraní Clip
     * K získání souboru používá třídu InputStream, nastaví se soubor
     * Dále třída AudioInputStream, který převádí instanci třídy InputStreamu jako spustitelné audio
     * clip otevírá audioStream
     * @param cesta název souboru
     * @return vrací objekt typu Clip
     */
    default Clip nacteni(String cesta) throws UnsupportedAudioFileException, IOException {
        Clip clip = null;
        try
        {
            InputStream wavStream = Music.class.getResourceAsStream(cesta);
            BufferedInputStream br = new BufferedInputStream(wavStream);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(br);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        catch (Exception e){
            System.out.println(e);
            e.printStackTrace();
            JFrame frame = new JFrame("Error");
            JLabel l = new JLabel("Chyba: " + e);
            frame.setSize(500,500);
            frame.add(l);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        return clip;
    }


}

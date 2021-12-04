import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public interface Music {
    AtomicBoolean turn = new AtomicBoolean(true);
    static Clip nacteni(String cesta)throws UnsupportedAudioFileException,
            IOException, LineUnavailableException {
        Clip clip = null;
        try
        {
            File file = new File(cesta);
            String st = file.getAbsolutePath();
            String[] pole = st.split("\\\\");
            st = "";
            for (int i = 0;i < pole.length; i++){
                st += pole[i];
                if (i != (pole.length - 1))
                    st += "\\\\";
            }
            System.out.println(st);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File(st));
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        }
        catch (Exception e){
            System.out.println("nenačetla se songa");
        }
        return clip;
    }


}

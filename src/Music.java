import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public interface Music {
    static Clip nacteni(String cesta){
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

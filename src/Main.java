import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Main extends Piskvorky{
    public Main() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    }

    public static void main(String[] args) {

        try {
            Piskvorky piskvorky = new Piskvorky();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}

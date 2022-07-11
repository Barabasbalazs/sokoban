import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.ActionListener;
import java.io.IOException;


public class Sokoban{

    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        StartMenu startmenu = new StartMenu();
        new SoundPlayer();
        ActionListener l1 = e -> {
            startmenu.dispose();
            try {
                new LevelFrame(1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };

        ActionListener l2 = e -> {
            startmenu.dispose();
            try {
                new LevelFrame(2);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };

        ActionListener quit = e -> startmenu.dispose();

        startmenu.getLev1().addActionListener(l1);
        startmenu.getLev2().addActionListener(l2);
        startmenu.getQuit().addActionListener(quit);

    }

}



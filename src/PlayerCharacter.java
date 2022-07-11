import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayerCharacter implements Block{
    private BufferedImage img;
    private int x;//a koordinatak
    private int y;

    public PlayerCharacter(int x,int y) {
        try {
            img = ImageIO.read(new File("src\\dep\\kid.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
    }

    public void show (Graphics g){
        g.drawImage(img,x,y,null);
    }

    public void move (int x, int y) {
        this.x += x;
        this.y += y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

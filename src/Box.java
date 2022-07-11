import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Box implements Block{
    private BufferedImage img;
    private int x;//a koordinatak
    private int y;

    public Box(int x,int y) {
        try {
            img = ImageIO.read(new File("src\\dep\\box.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
    }

    public void move (int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public void show(Graphics g) {
        g.drawImage(img,x,y,null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

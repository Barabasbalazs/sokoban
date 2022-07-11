import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Wall implements Block {
    private BufferedImage img;
    private final int x;//a koordinatak
    private final int y;

    public Wall(int x,int y) {
        try {
            img = ImageIO.read(new File("src\\dep\\brick_wall.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
    }

    public void show (Graphics g){
        g.drawImage(img,x,y,null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

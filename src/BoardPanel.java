import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BoardPanel extends JPanel {
    private int level;
    private int nrWalls;
    private int nrGoals;
    private int nrBoxes;
    private Wall [] w;
    private Goal [] goal;
    private Box [] boxes;
    private PlayerCharacter player;
    private BufferedImage background;

    public BoardPanel(int level) throws IOException{
        this.level = level;
        try {
            background = ImageIO.read(new File("src\\dep\\background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        readWalls();
        readGoals();
        readBoxes();
        readPlayer();
    }

    public void reload() throws IOException{
        level = 2;
        readWalls();
        readGoals();
        readBoxes();
        readPlayer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background,0,0,null);
        showWalls(g);
        showGoals(g);
        showBoxes(g);
        showPlayer(g);
    }

    private void readWalls() throws IOException {
        String path;
        if (level == 1)
            path = "src\\layouts_level1\\walls.txt";
        else
            path = "src\\layouts_level2\\walls.txt";
        File myFile = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(myFile));
        nrWalls = Integer.parseInt(bufferedReader.readLine());
        w = new Wall[nrWalls];
        String [] koord;
        int x, y;
        for (int i = 0; i < nrWalls; i++) {
            koord = bufferedReader.readLine().split(" ");
            x = Integer.parseInt(koord[0]);
            y = Integer.parseInt(koord[1]);
            w[i] = new Wall(x,y);
        }
        bufferedReader.close();
    }


    private void readGoals() throws IOException {
        String path;
        if (level == 1)
            path = "src\\layouts_level1\\goals.txt";
        else
            path = "src\\layouts_level2\\goals.txt";
        File myFile = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(myFile));
        nrGoals = Integer.parseInt(bufferedReader.readLine());
        goal = new Goal[nrGoals];
        String [] koord;
        int x, y;
        for (int i = 0; i < nrGoals; i++) {
            koord = bufferedReader.readLine().split(" ");
            x = Integer.parseInt(koord[0]);
            y = Integer.parseInt(koord[1]);
            goal[i] = new Goal(x,y);
        }
        bufferedReader.close();
    }

    private void readBoxes() throws IOException {
        String path;
        if (level == 1)
            path = "src\\layouts_level1\\boxes.txt";
        else
            path = "src\\layouts_level2\\boxes.txt";
        File myFile = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(myFile));
        nrBoxes = Integer.parseInt(bufferedReader.readLine());
        boxes = new Box[nrBoxes];
        String [] koord;
        int x, y;
        for (int i = 0; i < nrBoxes; i++) {
            koord = bufferedReader.readLine().split(" ");
            x = Integer.parseInt(koord[0]);
            y = Integer.parseInt(koord[1]);
            boxes[i] = new Box(x,y);
        }
        bufferedReader.close();
    }

    private void readPlayer() throws IOException {
        String path;
        if (level == 1)
            path = "src\\layouts_level1\\player.txt";
        else
            path = "src\\layouts_level2\\player.txt";
        File myFile = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(myFile));
        String [] koord;
        int x, y;
        koord = bufferedReader.readLine().split(" ");
        x = Integer.parseInt(koord[0]);
        y = Integer.parseInt(koord[1]);
        player = new PlayerCharacter(x,y);
        bufferedReader.close();
    }

    private void showWalls(Graphics g) {
        for (int i = 0; i < nrWalls; i++)
            w[i].show(g);
    }

    private void showGoals(Graphics g) {
        for (int i = 0; i < nrGoals; i++)
            goal[i].show(g);
    }

    private void showBoxes(Graphics g) {
        for (int i = 0; i < nrBoxes; i++)
            boxes[i].show(g);
    }

    private void showPlayer(Graphics g) {
        player.show(g);
    }

    public void movePlayer(int x,int y) {
        player.move(x,y);
        this.repaint();
    }

    public void moveBox(int i,int x,int y) {
        boxes[i].move(x,y);
        this.repaint();
    }

    public int playerX() {
        return player.getX();
    }

    public int playerY() {
        return player.getY();
    }

    public boolean collisionWithWalls(int x,int y) {
        for (int i = 0; i < nrWalls; i++) {
            if (w[i].getY() == y && w[i].getX() == x)
                return true;
        }
        return false;
    }

    public int collisionWithBox(int x,int y) {
        for (int i = 0; i < nrBoxes; i++) {
            if (boxes[i].getY() == y && boxes[i].getX() == x)
                return i;
        }
        return -1;
    }

    public int getXfromBox(int i) {
        return boxes[i].getX();
    }

    public int getYfromBox(int i) {
        return boxes[i].getY();
    }

    public boolean checkIfEnd() {
        int count = 0;
        for (int i = 0; i < nrGoals; i++)
            for (int j = 0; j < nrBoxes; j++)
                if (goal[i].getX() == boxes[j].getX() && goal[i].getY() == boxes[j].getY())
                    count++;
        return count == nrGoals;
    }
}

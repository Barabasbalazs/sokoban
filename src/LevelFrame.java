import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class LevelFrame extends JFrame implements KeyListener{

    private int level;
    private final BoardPanel bp;
    private final EndMenu endMenu;

    public LevelFrame(int level) throws IOException {
        this.level = level;
        setBounds(0, 0, 660, 660);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sokoban");

        bp = new BoardPanel(level);
        endMenu = new EndMenu(level);

        add(bp, BorderLayout.CENTER);
        addKeyListener(this);

        ActionListener contPushed = e -> {
            endMenu.dispose();
            try {
                bp.reload();
                bp.repaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        };

        ActionListener quitPushed = e -> {
            dispose();
            endMenu.dispose();
        };

        endMenu.getCont().addActionListener(contPushed);
        endMenu.getQuit().addActionListener(quitPushed);

        setVisible(true);
    }

    /*
    37 -- Left
    38 -- Up
    39 -- Right
    40 -- Down
    */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int kCode = e.getKeyCode();
        //moving playerchar
        if (kCode >= 37 && kCode <= 40)
            movement(kCode);
        bp.repaint();//elegancia celbol
        try {
            checkWinning();
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void movement(int kCode) {
        int x = bp.playerX();
        int y = bp.playerY();
        if (kCode == KeyEvent.VK_LEFT) {
            x -= 60;
            newPositions(x,y,-60,0);
        } else if (kCode == KeyEvent.VK_RIGHT) {
            x += 60;
            newPositions(x,y,60,0);
        } else if (kCode == KeyEvent.VK_UP) {
            y -= 60;
            newPositions(x,y,0,-60);
        } else {
            y += 60;
            newPositions(x,y,0,60);
        }
    }

    private boolean frameCollision(int x, int y) {
        return x < 0 || y < 0 || x > 600 || y > 595;
    }

    private boolean wallCollision(int x, int y) {
        return bp.collisionWithWalls(x, y);
    }

    private int boxCollision(int x, int y) {
        return bp.collisionWithBox(x, y);
    }

    private void newPositions(int pX, int pY, int modX, int modY) {
        if (!frameCollision(pX, pY) && !wallCollision(pX, pY)) {
            int boxInd = boxCollision(pX, pY);
            if (boxInd > -1) {
                int bX = bp.getXfromBox(boxInd) + modX;
                int bY = bp.getYfromBox(boxInd) + modY;
                if (!wallCollision(bX, bY) & boxCollision(bX,bY) == -1)
                    bp.moveBox(boxInd, modX, modY);
                else
                    return;
            }
            bp.movePlayer(modX, modY);
        }
    }

    private void checkWinning() throws InterruptedException, IOException {
        if (bp.checkIfEnd()) {
            if (level == 2)
                endMenu.second();
            if (level == 1)
                level = 2;
            endMenu.setVisible(true);
        }
    }

}

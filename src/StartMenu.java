import javax.swing.*;
import java.awt.*;

public class StartMenu extends JFrame {

    private final JButton lev1;
    private final JButton lev2;
    private final JButton quit;

    public StartMenu() {
        setBounds(0, 0, 540, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(4,1));
        add(jp);

        JLabel message = new JLabel("Sokoban", SwingConstants.CENTER);
        message.setFont(new Font("Arial Black", Font.BOLD, 28));
        lev1 = new JButton("Level 1");
        lev1.setFont(new Font("Arial Black", Font.BOLD, 28));

        lev2 = new JButton("Level 2");
        lev2.setFont(new Font("Arial Black", Font.BOLD, 28));

        quit = new JButton("Quit");
        quit.setFont(new Font("Arial Black", Font.BOLD, 28));

        jp.add(message);
        jp.add(lev1);
        jp.add(lev2);
        jp.add(quit);

        setVisible(true);
    }

    public JButton getLev1() {
        return lev1;
    }

    public JButton getLev2() { return lev2; }

    public JButton getQuit() {
        return quit;
    }
}

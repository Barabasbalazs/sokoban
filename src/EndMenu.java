import javax.swing.*;
import java.awt.*;

public class EndMenu extends JFrame{

    private final JPanel jp;
    private final JButton cont;
    private final JButton quit;

    public EndMenu(int level) {
        setBounds(0, 0, 540, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp = new JPanel();
        if (level == 1)
            jp.setLayout(new GridLayout(3,1));
        else
            jp.setLayout(new GridLayout(2,1));
        add(jp);

        JLabel message = new JLabel("Level Finished!", SwingConstants.CENTER);
        message.setFont(new Font("Arial Black", Font.BOLD, 28));
        cont = new JButton("Continue");
        cont.setFont(new Font("Arial Black", Font.BOLD, 28));
        quit = new JButton("Quit");
        quit.setFont(new Font("Arial Black", Font.BOLD, 28));

        jp.add(message);
        if (level == 1)
            jp.add(cont);
        jp.add(quit);

    }

    public void second() {
        jp.setLayout(new GridLayout(2,1));
        jp.remove(cont);

    }

    public JButton getCont() {
        return cont;
    }

    public JButton getQuit() {
        return quit;
    }
}

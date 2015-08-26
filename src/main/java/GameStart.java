import javax.swing.*;

/**
 * Created by vas on 26.08.2015.
 */
public class GameStart {
    public static void main(String[] args) {
        GamePanel panel = new GamePanel();
        JFrame startFrame = new JFrame("Ворон");
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startFrame.setContentPane(panel);
        startFrame.pack();
        startFrame.setLocationRelativeTo(null); //окно по центру
        startFrame.setVisible(true);
        panel.start();
    }
}

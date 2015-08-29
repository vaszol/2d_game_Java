import java.awt.*;

/**
 * Created by vas on 29.08.2015.
 */
public class Menue {
    //Fields
    private int buttonWidth;
    private int buttonHeight;
    private Color color1;
    private String s;
    //Constructor

    public Menue() {
        buttonWidth = 120;
        buttonHeight = 60;
        color1 = Color.white;
        s = "Play!";
    }

    //Functions
    public void draw(Graphics2D g){
        g.setColor(color1);
        g.setStroke(new BasicStroke(3));
        g.drawRect(
                GamePanel.WIDTH / 2 - buttonWidth/2,
                GamePanel.HEIGHT / 2 - buttonHeight/2,
                buttonWidth, buttonHeight);
        g.setStroke(new BasicStroke(1));
        g.setColor(color1);
        g.setFont(new Font("Consoles", Font.BOLD, 40));
        long length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, (int)(GamePanel.WIDTH/2 - length/2), (int)(GamePanel.HEIGHT/2 + buttonHeight/4));
    }
}

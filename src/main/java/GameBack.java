import java.awt.*;

/**
 * Created by vas on 26.08.2015.
 */
public class GameBack {
    //Fields
    private Color color;

    public GameBack() {
        this.color = Color.BLUE;
    }



    //Functions
    public void update(){

    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillRect(0,0, GamePanel.WIDTH, GamePanel.HEIGHT);
    }
}

import java.awt.*;

/**
 * Created by vas on 26.08.2015.
 */
public class Bullet  {

    //Field
    private double x;
    private double y;
    private int r;

    private int speed;
    private Color color;

    //Constructor
    public Bullet() {
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
        r = 2;

        speed = 10;

        color = Color.WHITE;
    }

    //Functions

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public int getR() {
        return r;
    }

    public void update(){
        y -= speed;
    }

    public void draw(Graphics2D g){
        g.setColor(color);
        g.fillOval((int) x,(int) y, r, 2*r);
    }

    public boolean remove(){
        if(y < 0){
            return true;
        }
        return false;
    }



}

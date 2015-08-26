import java.awt.*;

/**
 * Created by vas on 26.08.2015.
 */
public class Player {

    //Field
    private double x;
    private double y;
    private int r;

    private double dx; //Move Coef
    private double dy; //Move Coef

    private int speed;


    private Color color1;
    private Color color2;

    public static boolean up;
    public static boolean left;
    public static boolean down;
    public static boolean right;

    public static boolean isFiring;

    //Constructor


    public Player() {
        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;
        r=5;

        dx = 0;
        dy = 0;

        speed = 5;

        color1 = Color.white;

        up=false;
        left=false;
        down=false;
        right=false;

        isFiring = false;
    }

    //Functions


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void update(){
        if (up && y>r){
            dy = -speed;
        }if (left && x > r){
            dx = -speed;
        }if (down && y < GamePanel.HEIGHT - r){
            dy = speed;
        }if (right && x < GamePanel.WIDTH - r){
            dx = speed;
        }

        if (up && left || up && right || down && left || down && right){
            dy = dy*Math.sin(45);
            dx = dx*Math.cos(45);
        }

        y += dy;
        x += dx;

        dy = 0;
        dx = 0;

        if (isFiring){
            GamePanel.bullets.add(new Bullet());
        }
    }

    public void draw(Graphics2D g){
        g.setColor(color1);
        g.fillOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color1.darker());
        g.drawOval((int) (x - r), (int) (y - r), 2 * r, 2 * r);
        g.setStroke(new BasicStroke(1));
    }

}

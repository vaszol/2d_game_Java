import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by vas on 26.08.2015.
 */
public class GamePanel extends JPanel implements Runnable {
    //Field
    public static int WIDTH = 400;
    public static int HEIGHT = 400;

    private Thread thread; //

    private BufferedImage image; //холст
    private Graphics2D g; //кисточка

    public static GameBack background;
    public static Player player;
    public static ArrayList<Bullet> bullets;

    //Constructor
    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(new Listners());
    }

    //Functions

    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    public void run() {

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); //размеры и способ растрирования
        g = (Graphics2D) image.getGraphics(); //привяжем холст к кисти
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();

        while (true){// TODO States

            gameUpdate();
            gameRender();
            gameDraw();

            try {
                thread.sleep(33); //TODO FPS
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    public void gameUpdate(){
        //Background update
        background.update();

        //Player update
        player.update();

        //Bullets update
        for(int i=0; i < bullets.size(); i++){
            bullets.get(i).update();
        }
    }

    public void gameRender(){
        //Background draw
        background.draw(g);

        //Player draw
        player.draw(g);

        //Bullets draw
        for(int i=0; i < bullets.size(); i++){
            bullets.get(i).draw(g);
        }
    }

    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0, null);
        g2.dispose(); //Clear Buffer
    }
}

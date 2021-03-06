import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by vas on 26.08.2015.
 */
public class GamePanel extends JPanel implements Runnable {
    //Field
    public static int WIDTH = 600;
    public static int HEIGHT = 600;

    public static int mouseX;
    public static int mouseY;
    public static boolean LeftMouse;

    private Thread thread; //

    private BufferedImage image; //холст
    private Graphics2D g; //кисточка

    private int FPS;
    private double millisToFPS;
    private long timerFPS;
    private int sleepTime;

    public static enum STATES{
        MENUE,
        PLAY
    }
    public static STATES state = STATES.MENUE;

    public static GameBack background;
    public static Player player;
    public static ArrayList<Bullet> bullets;
    public static ArrayList<Enemy> enemies;
    public static Wave wave;
    public static Menue menue;

    //Constructor
    public GamePanel(){
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();

        addKeyListener(new Listners());
        addMouseMotionListener(new Listners());
        addMouseListener(new Listners());
    }

    //Functions

    public void start(){
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        FPS = 30;
        millisToFPS = 1000/FPS;
        sleepTime = 0;

        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB); //размеры и способ растрирования
        g = (Graphics2D) image.getGraphics(); //привяжем холст к кисти
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        LeftMouse = false;

//        String name = g.getClass().getName();
//        System.out.println(name);
//        sun.java2d.SunGraphics2D  клас который занимается графикой


        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        wave = new Wave();
        menue = new Menue();

        while (true){// TODO States
            if(state.equals(STATES.MENUE)){
                background.update();
                background.draw(g);
                menue.update();
                menue.draw(g);
                gameDraw();
            }
            if(state.equals(STATES.PLAY)){
                gameUpdate();
                gameRender();
                gameDraw();
            }


            timerFPS = System.nanoTime();
//            long timer = System.nanoTime();


            timerFPS = (System.nanoTime() - timerFPS)/1000000;
            if(millisToFPS > timerFPS){
                sleepTime = (int) (millisToFPS - timerFPS);
            }else sleepTime = 1;

//            long elapsed = (System.nanoTime() - timer)/1000000;
//            System.out.println(elapsed);

            try {
                thread.sleep(sleepTime); //TODO FPS
                System.out.println(sleepTime);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 1;
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
            boolean remove = bullets.get(i).remove();
            if(remove){
                bullets.remove(i);
                i--;
            }
//            System.out.println(bullets.size());
        }

        //Enemies uodate
        for(int i=0; i < enemies.size(); i++){
            enemies.get(i).update();
        }

        //Bullets-enemies collide
        for(int i = 0; i < enemies.size(); i++){
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();
            for (int j = 0; j < bullets.size(); j++){
                Bullet b = bullets.get(j);
                double bx = b.getX();
                double by = b.getY();
                double dx = ex - bx;
                double dy = ey - by;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if((int) dist < e.getR() + b.getR()){
                    e.hit();
                    bullets.remove(j);
//                    j--;
                    boolean remove = e.remove();
                    if(remove){
                        enemies.remove(i);
                        i--;
                        break;
                    }
                }
            }
        }
        //Player-enemy colides
        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();
            double dx = ex - px;
            double dy = ey - py;
            double dist = Math.sqrt(dx * dx + dy *dy);
            if ((int) dist <= e.getR() + player.getR()){
                e.hit();
                player.hit();
                boolean remove = e.remove();
                if (remove){
                    enemies.remove(i);
                    i--;
                }
            }

        }
        wave.update();
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

        //Enemies draw
        for(int i=0; i < enemies.size(); i++){
            enemies.get(i).draw(g);
        }

        //Wave draw
        if(wave.showWave()){
            wave.draw(g);
        }
    }

    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0, null);
        g2.dispose(); //Clear Buffer
    }
}

import sun.rmi.runtime.Log;

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
    public static ArrayList<Enemy> enemies;

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
        String name = g.getClass().getName();
//        System.out.println(name);
//        sun.java2d.SunGraphics2D  клас который занимается графикой


        background = new GameBack();
        player = new Player();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        //TODO remove these enemies
        enemies.add(new Enemy(1, 1)); //добавим врагов для теста
        enemies.add(new Enemy(1, 1));

        while (true){// TODO States
//            long timer = System.nanoTime();
            gameUpdate();
            gameRender();
            gameDraw();

//            long elapsed = (System.nanoTime() - timer)/1000000;
//            System.out.println(elapsed);

            try {
                thread.sleep(20); //TODO FPS
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
                    break;
                }
            }
            boolean remove = e.remove();
            if(remove){
                enemies.remove(i);
                i--;
            }
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

        //Enemies draw
        for(int i=0; i < enemies.size(); i++){
            enemies.get(i).draw(g);
        }
    }

    private void gameDraw(){
        Graphics g2 = this.getGraphics();
        g2.drawImage(image,0,0, null);
        g2.dispose(); //Clear Buffer
    }
}

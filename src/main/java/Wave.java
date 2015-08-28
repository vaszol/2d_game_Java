import java.awt.*;

/**
 * Created by vas on 28.08.2015.
 */
public class Wave {

    //Fields
    private int waveNumber;
    private int waveMultiplier;

    private long waveTimer;
    private long waveTimerDiff;
    private long waveDelay;

    private String waveText;

    //Constructor

    public Wave() {
        waveNumber = 1;
        waveMultiplier = 5;

        waveTimer = 0;
        waveDelay = 5000;
        waveTimerDiff = 0;

        waveText = " В О Л Н А ";
    }

    //Function

    private void createEnemies() {
        int enemyCount = waveNumber * waveMultiplier;
        if(waveNumber < 4){
            while (enemyCount > 0){
                int type = 1;
                int rank = 1;
                GamePanel.enemies.add(new Enemy(type, rank));
                enemyCount -= type *rank;
            }
        }
        waveNumber++;
    }

    public void update(){
        if(GamePanel.enemies.size() == 0 && waveTimer == 0){
            waveTimer = System.nanoTime();
        }
        if(waveTimer > 0){
            waveTimerDiff += (System.nanoTime()-waveTimer)/1000000;
            waveTimer = System.nanoTime();
        }
        if (waveTimerDiff > waveDelay){
            createEnemies();
            waveTimer = 0;
            waveTimerDiff = 0;
        }
    }
    public boolean showWave(){
        if(waveTimer != 0){
            return  true;
        }else return false;
    }

    public void draw(Graphics2D g){
        double divider = waveDelay/180;
        double alpha = waveTimerDiff/divider;
        alpha = 255*Math.sin(Math.toRadians(alpha));
        if(alpha < 0 ) alpha = 0;
        if(alpha > 255) alpha = 255;
        g.setFont(new Font("consolas", Font.PLAIN, 20));
        g.setColor(new Color(255, 255, 255, (int) alpha));
        String s = " - " + waveNumber + "ая" + waveText + " - ";
        long length = (long) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, GamePanel.WIDTH/2 - (int) (length / 2), GamePanel.HEIGHT/2);
    }
}

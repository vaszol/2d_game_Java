import sun.security.krb5.internal.KRBError;

import java.awt.event.*;

/**
 * Created by vas on 26.08.2015.
 */
public class Listners implements KeyListener, MouseListener, MouseMotionListener {



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key== KeyEvent.VK_W){
            Player.up = true;
        }
        if (key== KeyEvent.VK_A){
            Player.left = true;
        }
        if (key== KeyEvent.VK_S){
            Player.down = true;
        }
        if (key== KeyEvent.VK_D){
            Player.right = true;
        }
        if (key== KeyEvent.VK_SPACE){
            Player.isFiring = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key== KeyEvent.VK_W){
            Player.up = false;
        }
        if (key== KeyEvent.VK_A){
            Player.left = false;
        }
        if (key== KeyEvent.VK_S){
            Player.down = false;
        }
        if (key== KeyEvent.VK_D){
            Player.right = false;
        }
        if (key== KeyEvent.VK_SPACE){
            Player.isFiring = false;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            GamePanel.player.isFiring = true;
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1){
            GamePanel.player.isFiring = false;
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }

    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }
}

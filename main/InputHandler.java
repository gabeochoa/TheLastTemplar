//Made by Notch for the LudumDare48 compo

package main;
import java.awt.event.*;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {
    public boolean[] keys = new boolean[65536];
    public int[] mouse = new int[5];
    public void mouseDragged(MouseEvent arg0) {
    }

    public void mouseMoved(MouseEvent arg0) {
    }

    public void mouseClicked(MouseEvent arg0) { 
    }

    public void mouseEntered(MouseEvent arg0) {
        mouse[3] = 1;
    }

    public void mouseExited(MouseEvent arg0) {
         mouse[3] = 0;
    }

    public void mousePressed(MouseEvent arg0) {
        mouse[0] = arg0.getButton();
        mouse[1] = arg0.getX();
        mouse[2] = arg0.getY();
    }

    public void mouseReleased(MouseEvent arg0) {
        mouse[0] = 0;//arg0.getButton();
        mouse[1] = 0;//arg0.getX();
        mouse[2] = 0;//arg0.getY();
    }

    public void focusGained(FocusEvent arg0) {
    }

    public void focusLost(FocusEvent arg0) {
        for (int i=0; i<keys.length; i++) {
            keys[i] = false;
        }
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); 
        if (code>0 && code<keys.length) {
            keys[code] = true;
        }
        
    }

    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); 
        if (code>0 && code<keys.length) {
            keys[code] = false;
        }
    }

    public void keyTyped(KeyEvent arg0) {
    }
}
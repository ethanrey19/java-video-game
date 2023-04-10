package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed;
    public boolean downPressed;
    public boolean leftPressed;
    public boolean rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // does not use ..
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode(); // returns the integer keyCode associated with the key in this event

        // if you press w key
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }

        // if you press s key
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }

        // if you press a key
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }

        // if you press d key
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        // if you press w key
        if(code == KeyEvent.VK_W){
            upPressed = false;
        }

        // if you press s key
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }

        // if you press a key
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }

        // if you press d key
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
    }
}

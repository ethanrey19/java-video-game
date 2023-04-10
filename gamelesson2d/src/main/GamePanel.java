package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // scales tile size up

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16; // columns of tiles displayed on the frame
    public final int maxScreenRow = 12; // row of tiles displayed on the frame

    public final int SCREEN_WIDTH = tileSize * maxScreenCol; // width of screen / 768 pixels
    public final int SCREEN_HEIGHT = tileSize * maxScreenRow; // height of screen / 578 pixels

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60; // caps fps at 60

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,keyHandler); // passes gamePanel and keyHandler to player class

    public GamePanel(){

        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // helps rendering and performance
        this.addKeyListener(keyHandler); // adds the key handler
        this.setFocusable(true); // with this, the main.GamePanel can be focused to receive key input.

    }

    public void startGameThread () {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){

            currentTime = System.nanoTime(); // gets currents time

            delta += (currentTime - lastTime) / drawInterval; // gets how much time has pasted

            timer += (currentTime - lastTime); // adds pastime to the timer

            lastTime = currentTime; // last time the last time it went through the loop

            if(delta>=1){
            update();
            repaint();
            delta--;
            drawCount++;
            }

            if (timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update(){
            player.update(); // calls players individual update class.
        }

        public void paintComponent (Graphics g){

            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g; // changes graphics to 2d

            tileManager.draw(g2); // calls the tiles on the background

            player.draw(g2); // calls players individual draw class and passes in the graphics

            g2.dispose(); // dispose of this graphics context and release any system recourses it is using / saves memory
    }
}

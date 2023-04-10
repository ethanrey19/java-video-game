package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler){

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.SCREEN_WIDTH/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.SCREEN_HEIGHT/2 - (gamePanel.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }

    // Sets player's default position
    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{

            up1 = ImageIO.read(new FileInputStream("res\\player\\boy_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("res\\player\\boy_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("res\\player\\boy_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res\\player\\boy_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res\\player\\boy_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("res\\player\\boy_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("res\\player\\boy_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("res\\player\\boy_right_2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //gets called 60 times in a second
    public void update(){

        // if a key is being pressed
        if(keyHandler.upPressed|| keyHandler.downPressed || keyHandler.leftPressed|| keyHandler.rightPressed){

            // if you press certain key
            if(keyHandler.upPressed){
                direction = "up";
                worldY -= speed;
            }else if(keyHandler.downPressed){
                direction = "down";
                worldY += speed;
            }else if (keyHandler.leftPressed){
                direction = "left";
                worldX -= speed;
            }else if (keyHandler.rightPressed){
                direction = "right";
                worldX += speed;
            }

            // changes the player image every 12 frames to make it look like walking
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1) {
                    spriteNum = 2;
                }else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2){
        //g2.setColor(Color.WHITE);
        //g2.fillRect(x,y,gamePanel.tileSize, gamePanel.tileSize);

        BufferedImage image = null;


        // if you press up key it will queue the up image
       if(direction == "up"){
           if(spriteNum == 1){
               image = up1;
           }else if(spriteNum == 2){
               image = up2;
           }
       }else if (direction == "down"){
           if(spriteNum == 1){
               image = down1;
           }else if(spriteNum == 2){
               image = down2;
           }
       }else if (direction == "left"){
           if(spriteNum == 1){
               image = left1;
           }else if(spriteNum == 2){
               image = left2;
           }
       }else if (direction == "right"){
           if(spriteNum == 1){
               image = right1;
           }else if(spriteNum == 2){
               image = right2;
           }
       }

       // draws the player image
       g2.drawImage(image,screenX,screenY, gamePanel.tileSize, gamePanel.tileSize,null);

    }
}

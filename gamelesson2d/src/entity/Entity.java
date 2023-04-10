package entity;

import java.awt.image.BufferedImage;

public class Entity {

    // This stores variables that will be used in player,monster and NPC classes

    public int worldX;
    public int worldY;
    public int speed;

    public BufferedImage up1;
    public BufferedImage up2;
    public BufferedImage down1;
    public BufferedImage down2;
    public BufferedImage left1;
    public BufferedImage left2;
    public BufferedImage right1;
    public BufferedImage right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;

        tile = new Tile[10]; // create 10 types of tiles
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("res/maps/world01.txt");
    }

    public void getTileImage(){

        try{

            File file = new File("res/tiles/earth.png");
            FileInputStream fis = new FileInputStream(file);
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(fis);

            file = new File("res/tiles/wall.png");
            fis = new FileInputStream(file);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(fis);

            file = new File("res/tiles/water.png");
            fis = new FileInputStream(file);
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(fis);

            file = new File("res/tiles/grass.png");
            fis = new FileInputStream(file);
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(fis);

            file = new File("res/tiles/tree.png");
            fis = new FileInputStream(file);
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(fis);

            file = new File("res/tiles/sand.png");
            fis = new FileInputStream(file);
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(fis);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // scans a text file one by one and save the numbers
    public void loadMap(String filePath){

        // uses a buffered reader to read the content of a text file
        try{
            InputStream inputStream = new FileInputStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){

                String line = bufferedReader.readLine(); // reads a line of text

                while(col < gamePanel.maxWorldCol){

                    String numbers[] = line.split(" "); // gets each number from the text file one by one and puts into array

                    int num = Integer.parseInt(numbers[col]); // uses col as an index for numbers array

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxWorldCol){
                    col = 0;
                    row ++;
                }
            }
            bufferedReader.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public void draw(Graphics2D graphics2D){

        //draw the tiles
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){

             int tileNum = mapTileNum[worldCol][worldRow]; // extracts a tile number which is stored in mapTileNum

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
             worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
             worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
             worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY){

                graphics2D.drawImage(tile[tileNum].image,screenX,screenY,gamePanel.tileSize, gamePanel.tileSize,null);
            }

            worldCol++;

            if(worldCol == gamePanel.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }

    }

}

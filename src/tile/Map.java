package tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GameEngine;

public class Map extends Tilemanager {

	GameEngine gp;
  BufferedImage worldMap[];
  public boolean miniMapOn = false;

  public Map(GameEngine gp) {
    super(gp);
    this.gp = gp;
    createWorldMap();
  }
  public void createWorldMap() {

    worldMap = new BufferedImage[gp.maxMap];
    int worldMapWidth = gp.tilesize * gp.maxworldcol;
    int worldMapHeight = gp.tilesize * gp.maxworldrow;

    for (int i = 0; i < gp.maxMap; i++) {
      worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = (Graphics2D) worldMap[i].createGraphics();

      int col = 0;
      int row = 0;

      while (col < gp.maxworldcol && row < gp.maxworldrow) {
        int tileNum = maptilenum[i][col][row];
        int x = gp.tilesize * col;
        int y = gp.tilesize * row;
        g2.drawImage(tile[tileNum].image, x, y, null);

        col++;
        if (col == gp.maxworldcol) {
          col = 0;
          row++;
        }
      }
      g2.dispose();
    }

  }

  public void drawFullMapScreen(Graphics2D g2) {
    // Background color
    g2.setColor(Color.black);
    g2.fillRect(0, 0, gp.screenwidth, gp.screenheight);

    //Draw Map
    int width = 500;
    int height = 500;
    int x = gp.screenwidth / 2 - width / 2;
    int y = gp.screenheight / 2 - height / 2;
    g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

    //Draw player
    double scale = (double)(gp.tilesize * gp.maxworldcol) / width;
    int playerX = (int)(x + gp.player.worldx / scale);
    int playerY = (int)(y + gp.player.worldy / scale);
    int playerSize = (int)(gp.tilesize / scale);
    g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);

    //Hint
    g2.setColor(Color.white);
    g2.drawString("Press M to close", 750, 550);
  }
  public void drawMiniMap(Graphics2D g2) {
    if (miniMapOn == true) {

      //DrawMap
      int width = 200;
      int height = 200;
      int x = gp.screenwidth - width - 50;
      int y = 50;

      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
      g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);

      //Draw player
      double scale = (double)(gp.tilesize * gp.maxworldcol) / width;
      int playerX = (int)(x + gp.player.worldx / scale);
      int playerY = (int)(y + gp.player.worldy / scale);
      int playerSize = (int)(gp.tilesize / 3);
      g2.drawImage(gp.player.down1, playerX - 6, playerY - 6, playerSize, playerSize, null);

      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
  }
}
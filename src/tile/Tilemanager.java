package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GameEngine;
import main.ImageUtility;

public class Tilemanager {
	GameEngine gp;
	
	public Tile[] tile = new Tile[6];
    public int maptilenum[][][];
//    boolean drawPath = true;
	
	public Tilemanager(GameEngine gp) {
		this.gp= gp;
		tile = new Tile[50];
		maptilenum = new int[gp.maxMap][gp.maxworldcol][gp.maxworldrow];
		
		gettileimage();
		loadmap("/map/map01.txt",0);
		loadmap("/map/interior01.txt",1); 
	}
	public void gettileimage() {
		
		setup(0, "sand", false);
		setup(1, "wall", true);
		setup(2, "water", true);
		setup(3, "grass", false);
		setup(4, "tree", true);
		setup(5, "earth", false);
		setup(6,"hut", false);
		setup(7,"wall", true);
		setup(8,"floor01", false);
		setup(9,"table01", true);
		
	}
	
	public void setup(int index, String imagePath, boolean collision) {
		ImageUtility uTool = new ImageUtility();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
			tile[index].image = uTool.scaledImage(tile[index].image, gp.tilesize, gp.tilesize);
			tile[index].collision = collision;
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadmap(String filepath, int map) {
		try {
			InputStream is = getClass().getResourceAsStream(filepath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row =0;
			while (col<gp.maxworldcol && row < gp.maxworldrow) {
				String line = br.readLine();
				//????????
				while (col < gp.maxworldcol) {
					String numbers[]= line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					maptilenum[map][col][row] = num;
					col ++;
					
				}
				if (col == gp.maxworldcol) {
					col = 0;
					row ++;
				}
			}
			br.close();
		}catch (Exception e) {
			
		}
	}
	
    public void draw(Graphics2D g2) {
    	int worldcol = 0;
    	int worldrow = 0;
 
    	while (worldcol<gp.maxworldcol &&  worldrow<gp.maxworldrow) {
    		
    		int tilenum = maptilenum[gp.currentMap][worldcol][worldrow]; // lấy ra số hiệu của tile (0,1,2,3,4,5)
    		
    		
    		int worldx = worldcol*gp.tilesize;// tilesize là kích cỡ 1 cột
    		int worldy = worldrow*gp.tilesize;// worldx, worldy: tọa độ pix trên hàng/cột
    		
    		int screenx = worldx - gp.player.worldx + gp.player.screenx;
    		int screeny = worldy - gp.player.worldy + gp.player.screeny;
    		
    		if (worldx + gp.tilesize > gp.player.worldx - gp.player.screenx &&
    			worldx - gp.tilesize < gp.player.worldx + gp.player.screenx &&
    			worldy + gp.tilesize > gp.player.worldy - gp.player.screeny &&
    			worldy - gp.tilesize < gp.player.worldy + gp.player.screeny) {
    			
    			g2.drawImage(tile[tilenum].image, screenx, screeny,gp.tilesize,gp.tilesize,null);
    		}
    		
    		worldcol ++;

    		if (worldcol == gp.maxworldcol) {
    			worldcol = 0;
   
    			worldrow ++;
   
    		}
    	}
//    	if (drawPath == true) {
//    	    g2.setColor(new Color(0, 0, 0, 70));
//
//    	    for (int i = 0; i < gp.pFinder.pathList.size(); i++) {
//    	        int worldX = gp.pFinder.pathList.get(i).col * gp.tilesize;
//    	        int worldY = gp.pFinder.pathList.get(i).row * gp.tilesize;
//    	        int screenX = worldX - gp.player.worldx + gp.player.screenx;
//    	        int screenY = worldY - gp.player.worldy + gp.player.screeny;
//
//    	        g2.fillRect(screenX, screenY, gp.tilesize, gp.tilesize);
//    	    }
//    	}

    }
}
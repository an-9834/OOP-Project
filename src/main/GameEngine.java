package main;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import pathfinding.PathFinder;
import entity.GameActor;
import entity.PlayerCharacter;
import tile.Map;
import tile.Tilemanager;
import interactivetile.InteractiveTileEntity;

public class GameEngine extends JPanel implements Runnable {
	// CHỈNH MÀN HÌNH
	final int originaltilesize = 16; //16x16: NHÂN VẬT - NPC - Ô TILE TRÊN MAP
	final int scale = 3; //16x16 look like 48x48 
	public final int tilesize = originaltilesize*scale;
	public final int maxscreencol = 20;
	public final int maxscreenrow = 12;
	
	public final int screenwidth = tilesize*maxscreencol;// 960
	public final int screenheight = tilesize*maxscreenrow;//576
	
	
	//world setting
	public final int maxworldcol = 60;
	public final int maxworldrow = 60;
	public final int maxMap = 10;
	public int currentMap = 0;
	// FOR FULL SCREEN
	int screenwidth2 = screenwidth;
	int screenheight2 = screenheight;
	BufferedImage tempScreen;
	Graphics2D g2;
	public boolean fullScreenOn = false;
	
	int FPS = 60;
	
	//SYSTEM
	public Tilemanager tileM = new Tilemanager(this);
	public KeyboardInputManager keyH = new KeyboardInputManager(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionHandle ccheck = new CollisionHandle(this);
	public GameWorldInitializer aSetter = new GameWorldInitializer(this);
	public GameUIManager ui = new GameUIManager(this);
	public EventHandler eHandler = new EventHandler(this);
	Config config = new Config(this);
	public PathFinder pFinder = new PathFinder(this);
	Map map = new Map(this);

	Thread gameThread; //keep runing util u stop

	//GameActor AND OBJECT
	public PlayerCharacter player = new PlayerCharacter(this, keyH);
	public GameActor obj[][] =  new GameActor[maxMap][30];
	public GameActor npc[][] = new GameActor[maxMap][10];
	public GameActor monster[][] = new GameActor[maxMap][30];
	public InteractiveTileEntity iTile[][] = new InteractiveTileEntity[maxMap][50]; 
	public GameActor projectile [] [] = new GameActor [maxMap] [20];
//	public ArrayList<GameActor> project tile list = new ArrayList<>();
	public ArrayList<GameActor> particleList = new ArrayList<>();
	ArrayList<GameActor> GameActorList = new ArrayList<>();
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	public final int mapState = 9;
	
	
	// CONSTRUCTOR
	public GameEngine() {
		this.setPreferredSize(new Dimension(screenwidth,screenheight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true); // all the drawing from this componet done offscreen
		this.addKeyListener(this.keyH); // có
		this.setFocusable(true);// có
		this.requestFocusInWindow();
	
	}
	
	public void setupGame () {
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
//		playMusic(0);
		gameState = titleState;
		
		tempScreen = new BufferedImage(screenwidth, screenheight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) tempScreen.getGraphics();
		
		if (fullScreenOn == true) {
			  setFullScreen();
		}
	}
	public void resetGame(boolean restart) {
		player.setDefaultPositions();
	    player.restoreStatus();
	    aSetter.setNPC();
	    aSetter.setMonster();
	    
	    if(restart == true) {
	    	player.setdefaultval();
		    aSetter.setObject();
		    aSetter.setInteractiveTile();
	    }
	}
	public void setFullScreen() {
		
		// GET LOCAL SCREEN DEVICE
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    GraphicsDevice gd = ge.getDefaultScreenDevice();
	    gd.setFullScreenWindow(Main.window);
	    
	 // GET FULL SCREEN WIDTH AND HEIGHT
	    screenwidth2 = Main.window.getWidth();
	    screenheight2 = Main.window.getHeight();
	    
	}
	public void startgamethread() {
		this.gameThread = new Thread(this); // gắn biển gameThread bằng 1 giá trị Thread lấy GameEngine là tham số
		this.gameThread.start();
	}
	
	//game loop
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lasttime = System.nanoTime();
		long currenttime;
		long timer = 0;
		while (gameThread != null) {
			
			currenttime =  System.nanoTime();
			delta += (currenttime-lasttime)/drawInterval;
			timer += (currenttime-lasttime);
			lasttime= currenttime ;
			if (delta >=1) {
				update();
				drawToTempScreen(); // draw everything to the buffered image
				drawToScreen(); // draw the buffered image to the screen
				delta --;
			}
			if (timer>= 1000000000) {
				
				timer=0;
			}
		
			
		}
		// TODO Auto-generated method stub
		
	}
	public void update() {
		
		if(gameState == playState) {
			//PLAYER
			player.update();
			//NPC
			for(int i = 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
			for(int i = 0; i < monster[1].length; i++) {
				if(monster[currentMap][i] != null) {
					if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
						monster[currentMap][i].update();
					}
					if(monster[currentMap][i].alive == false) {
						monster[currentMap][i].checkDrop();
						monster[currentMap][i] = null;
					}
				}
			}
			for(int i = 0; i < projectile[1].length; i++) {
				if(projectile[currentMap][i] != null) {
					if(projectile[currentMap][i].alive == true ) {
						projectile[currentMap][i].update();
					}
					if(projectile[currentMap][i].alive == false) {
						projectile[currentMap][i] = null;
					}
				}
			}
			for(int i = 0; i < particleList.size(); i++) {
				if(particleList.get(i) != null) {
					if(particleList.get(i).alive == true ) {
						particleList.get(i).update();
					}
					if(particleList.get(i).alive == false) {
						particleList.remove(i);
					}
				}
			}
			for(int i = 0; i<iTile[1].length; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].update();
				}
			}
		}
		if(gameState == pauseState) {
			
		}

	}
	public void drawToTempScreen() {
		
		//DEBUG
		long drawStart = 0;
		if(keyH.showDebugText == true) {
			drawStart = System.nanoTime();
		}
		
		// TITLE SCREEN
		if (gameState == titleState) {
		    ui.draw(g2);
		}
		//MAP SCREEN
		else if(gameState == mapState) {
			map.drawFullMapScreen(g2);
		}
		// OTHERS
		else {
			// TILE
			tileM.draw(g2);
			
			for(int i=0; i<iTile[1].length; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].draw(g2);
				}
			}
			// ADD GameActor TO THE LIST
			GameActorList.add(player);
			for (int i =0; i< npc[1].length; i++) {
				if (npc[currentMap][i] != null) {
					GameActorList.add(npc[currentMap][i]);
				}
			}
			for (int i = 0; i < obj[1].length; i++) {
				if(obj[currentMap][i] != null) {
					GameActorList.add(obj[currentMap][i]);
				}
			}
			for (int i = 0; i < monster[1].length; i++) {
				if(monster[currentMap][i] != null) {
					GameActorList.add(monster[currentMap][i]);
				}
			}
			for (int i = 0; i < projectile[1].length; i++) {
				if(projectile[currentMap][i] != null) {
					GameActorList.add(projectile[currentMap][i]);
				}
			}
			for (int i = 0; i < particleList.size(); i++) {
				if(particleList.get(i) != null) {
					GameActorList.add(particleList.get(i));
				}
			}
			
			
			// SORT
			Collections.sort(GameActorList, new Comparator<GameActor>() {
				public int compare(GameActor e1,GameActor e2) {
					int result = Integer.compare(e1.worldy, e2.worldy);
					return result;
				}
			});
			//DRAW GameActor
			for (int i = 0; i < GameActorList.size(); i++) {
				GameActorList.get(i).draw(g2);
			}
			//EMPTY GameActor LIST
			GameActorList.clear();
			
			//MINI MAP
			map.drawMiniMap(g2);
			
			//UI 
			ui.draw(g2);
		}
		
		//DEBUG
		if(keyH.showDebugText == true) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			
			g2.setFont(new Font("Arial",Font.PLAIN,20));
			g2.setColor(Color.white);
			int x = 10;
			int y = 400;
			int lineheight = 20;
			g2.drawString("WorldX"+ player.worldx , x, y);
			y+= lineheight;
			g2.drawString("WorldY"+ player.worldy , x, y);
			g2.drawString("Col"+ (player.worldx+player.solidarea.x) , x, y);
			g2.drawString("Row"+ (player.worldy+player.solidarea.y) , x, y);
			g2.drawString("Draw time: " + passed, x, y);
			System.out.println("Draw time:" + passed);
		}
		
	}
	public void drawToScreen() {
	    Graphics g = getGraphics();
	    g.drawImage(tempScreen, 0, 0, screenwidth2, screenheight2, null);
	    g.dispose();
	}
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}

}

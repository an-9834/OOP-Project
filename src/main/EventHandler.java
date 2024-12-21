package main;

import entity.GameActor;

public class EventHandler {

	GameEngine gp;
	EventTriggerZone eventRect [][][];
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	int tempMap, tempCol, tempRow;
	
	public EventHandler(GameEngine gp) {
		this.gp = gp;
		
		eventRect = new EventTriggerZone[gp.maxMap][gp.maxworldcol][gp.maxworldrow];
		
		int map =0;
		int col = 0;
		int row = 0;
		while (map < gp.maxMap && col < gp.maxworldcol && row < gp.maxworldrow) {		
			eventRect[map][col][row] = new EventTriggerZone();
			eventRect[map][col][row].x = 23;
			eventRect[map][col][row].y = 23;
			eventRect[map][col][row].width = 2;
			eventRect[map][col][row].height = 2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
			
			col++;
			if(col == gp.maxworldcol) {
				col = 0;
				row++;
				
				if (row == gp.maxworldcol) {
					row = 0;
					map++;
				}
			}
		}
	}
		
	
	public void checkEvent() {
		
		int xDistance = Math.abs(gp.player.worldx - previousEventX);
		int yDistance = Math.abs(gp.player.worldy - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if (distance > gp.tilesize) {
			canTouchEvent = true;
		}
		if (canTouchEvent == true) {
			if (hit(0,15,12,"any") == true) {damagePit(gp.dialogueState);}
			else if (hit(0,20,17,"any") == true) {damagePit(gp.dialogueState);}
			else if (hit(0,28,12,"any") == true) {healingPool(gp.dialogueState);}
			else if (hit(1,19,47,"any") == true) {healingPool(gp.dialogueState);}
			else if (hit(0,49,7, "up") == true) {teleport(1,12,13);}
			else if (hit(1,49,48, "any") == true) {teleport(0,49,7);}
			else if (hit(1,12,9, "up") == true) {speak(gp.npc[1][0]);}
			else if (hit(1,13,47, "any") == true) {teleport(1,40,6);}
			else if (hit(1,40,6, "any") == true) {teleport(1,13,47);}
			else if (hit(1,27,10,"right") == true) {damagePit(gp.dialogueState);}
			else if (hit(1,40,26,"up") == true) {damagePit(gp.dialogueState);}
			else if (hit(1,45,17,"left") == true) {damagePit(gp.dialogueState);}
			else if (hit(1,19,17,"right") == true) {damagePit(gp.dialogueState);}
		}
	}
	public boolean hit(int map, int col, int row, String reqDirection) {
		boolean hit = false;
		
		if (map == gp.currentMap) {
			gp.player.solidarea.x = gp.player.worldx +  gp.player.solidarea.x;
			gp.player.solidarea.y = gp.player.worldy +  gp.player.solidarea.y;
			eventRect[map][col][row].x = col*gp.tilesize + eventRect[map][col][row].x;
			eventRect[map][col][row].y = row*gp.tilesize + eventRect[map][col][row].y;
			
			if(gp.player.solidarea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldx;
					previousEventY = gp.player.worldy;
				}
			}
			gp.player.solidarea.x = gp.player.solidAreaDefaultx;
			gp.player.solidarea.y = gp.player.solidAreaDefaulty;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		return hit;
	}
	public void damagePit( int gameState) {
		
		gp.gameState = gameState;
		gp.playSE(6);
		gp.ui.currentDialogue = "You fall into a pit!";
		gp.player.life -=1;
		canTouchEvent = false;
	}
	public void healingPool(int gameState) {
		
		if (gp.keyH.enterPressed == true) {
			gp.gameState = gameState;
			gp.player.attackCancel = true;
			gp.playSE(2);
			gp.ui.currentDialogue = "You drink the water.\nYour life and mana has been recovered. ";
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.aSetter.setMonster();
		}
	}
	public void teleport(int map, int col, int row) {
		
		gp.gameState = gp.transitionState;
		tempMap = map;
		tempCol = col;
		tempRow = row;
		canTouchEvent = false;
		gp.playSE(13);
	}
	public void speak(GameActor entity) {
		if(gp.keyH.enterPressed == true) {
		gp.gameState =  gp.dialogueState;
		gp.player.attackCancel = true;
		entity.speak();
		}
	}
}

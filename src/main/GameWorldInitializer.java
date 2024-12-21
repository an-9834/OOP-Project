package main;

import entity.NPC_Doraemon;
import entity.NPC_Girl;
import monster.MON_OrangeSlime;
import monster.MON_Trollgoblin;
import gameobject.OBJ_Chest;
import gameobject.OBJ_Coin_Bronze;
import gameobject.OBJ_Door;
import gameobject.OBJ_Heart;
import gameobject.OBJ_Mana;
import gameobject.OBJ_Key;
import gameobject.OBJ_PotionBlood;
import gameobject.OBJ_Nothing;
import gameobject.OBJ_PotionMana;
import interactivetile.InteractiveDryTree;
public class GameWorldInitializer {

	GameEngine gp;

	public GameWorldInitializer(GameEngine gp) {

		this.gp = gp ;

	}

	public void setObject() {
		
		int mapNum = 0;
		int i = 0;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*15;
		gp.obj[mapNum][i].worldy = gp.tilesize*12;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*22;
		gp.obj[mapNum][i].worldy = gp.tilesize*34;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*35;
		gp.obj[mapNum][i].worldy = gp.tilesize*30;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*34;
		gp.obj[mapNum][i].worldy = gp.tilesize*30;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*33;
		gp.obj[mapNum][i].worldy = gp.tilesize*30;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*23;
		gp.obj[mapNum][i].worldy = gp.tilesize*15;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*17;
		gp.obj[mapNum][i].worldy = gp.tilesize*15;
		i++;
	
		gp.obj[mapNum][i] = new OBJ_Door(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*9;
		gp.obj[mapNum][i].worldy = gp.tilesize*9;
		i++;
		gp.obj[mapNum][i] = new OBJ_Nothing(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*9;
		gp.obj[mapNum][i].worldy = gp.tilesize*8;
		i++;
		
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_PotionBlood(gp));
		gp.obj[mapNum][i].worldx = gp.tilesize*19;
		gp.obj[mapNum][i].worldy = gp.tilesize*8;
		i++;	
		
		gp.obj[mapNum][i] = new OBJ_Heart(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*7;
		gp.obj[mapNum][i].worldy = gp.tilesize*13;
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*27;
		gp.obj[mapNum][i].worldy = gp.tilesize*36;
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*24;
		gp.obj[mapNum][i].worldy = gp.tilesize*15;
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*28;
		gp.obj[mapNum][i].worldy = gp.tilesize*36;
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*26;
		gp.obj[mapNum][i].worldy = gp.tilesize*36;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Mana(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*22;
		gp.obj[mapNum][i].worldy = gp.tilesize*33;
		i++;
		gp.obj[mapNum][i] = new OBJ_Mana(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*15;
		gp.obj[mapNum][i].worldy = gp.tilesize*35;
		i++;
		gp.obj[mapNum][i] = new OBJ_Mana(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*16;
		gp.obj[mapNum][i].worldy = gp.tilesize*15;
		i++;
		gp.obj[mapNum][i] = new OBJ_Mana(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*17;
		gp.obj[mapNum][i].worldy = gp.tilesize*26;
		i++;

		//Map1
		mapNum = 1;
		i = 0;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_Key(gp));
		gp.obj[mapNum][i].worldx = gp.tilesize*42;
		gp.obj[mapNum][i].worldy = gp.tilesize*39;
		i++;
		
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_PotionBlood(gp));
		gp.obj[mapNum][i].worldx = gp.tilesize*46;
		gp.obj[mapNum][i].worldy = gp.tilesize*17;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_PotionMana(gp));
		gp.obj[mapNum][i].worldx = gp.tilesize*40;
		gp.obj[mapNum][i].worldy = gp.tilesize*27;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_PotionBlood(gp));
		gp.obj[mapNum][i].worldx = gp.tilesize*26;
		gp.obj[mapNum][i].worldy = gp.tilesize*10;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_PotionMana(gp));
		gp.obj[mapNum][i].worldx = gp.tilesize*31;
		gp.obj[mapNum][i].worldy = gp.tilesize*6;
		i++;
		gp.obj[mapNum][i] = new OBJ_Chest(gp);
		gp.obj[mapNum][i].setLoot(new OBJ_PotionBlood(gp));
		gp.obj[mapNum][i].worldx = gp.tilesize*18;
		gp.obj[mapNum][i].worldy = gp.tilesize*17;
		i++;
		gp.obj[mapNum][i] = new OBJ_Heart(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*45;
		gp.obj[mapNum][i].worldy = gp.tilesize*31;
		i++;
		gp.obj[mapNum][i] = new OBJ_Mana(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*30;
		gp.obj[mapNum][i].worldy = gp.tilesize*52;
		i++;
		gp.obj[mapNum][i] = new OBJ_Mana(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*28;
		gp.obj[mapNum][i].worldy = gp.tilesize*50;
		i++;
		gp.obj[mapNum][i] = new OBJ_Mana(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*26;
		gp.obj[mapNum][i].worldy = gp.tilesize*48;
		i++;
		gp.obj[mapNum][i] = new OBJ_Mana(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*24;
		gp.obj[mapNum][i].worldy = gp.tilesize*46;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*31;
		gp.obj[mapNum][i].worldy = gp.tilesize*43;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*29;
		gp.obj[mapNum][i].worldy = gp.tilesize*41;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*27;
		gp.obj[mapNum][i].worldy = gp.tilesize*39;
		i++;
		gp.obj[mapNum][i] = new OBJ_Coin_Bronze(gp);
		gp.obj[mapNum][i].worldx = gp.tilesize*25;
		gp.obj[mapNum][i].worldy = gp.tilesize*37;
		i++;
	}
	
	public void setNPC() {
		
		int mapNum = 0;
		int i = 0;
		//MAP 0
		gp.npc[mapNum][i] = new NPC_Girl(gp);
		gp.npc[mapNum][i].worldx = gp.tilesize*9;
		gp.npc[mapNum][i].worldy = gp.tilesize*8;
		i++;
		
		//MAP 1
		mapNum = 1;
		i = 0;
		gp.npc[mapNum][i] = new NPC_Doraemon(gp);
		gp.npc[mapNum][i].worldx = gp.tilesize*12;
		gp.npc[mapNum][i].worldy = gp.tilesize*7;
		i++;
	}
	public void setMonster() {
		//MAP 0
		int mapNum = 0;
		int i = 0;
		
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*20;
		gp.monster[mapNum][i].worldy = gp.tilesize*45;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*46;
		gp.monster[mapNum][i].worldy = gp.tilesize*14;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*36;
		gp.monster[mapNum][i].worldy = gp.tilesize*30;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*27;
		gp.monster[mapNum][i].worldy = gp.tilesize*42;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*31;
		gp.monster[mapNum][i].worldy = gp.tilesize*44;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*36;
		gp.monster[mapNum][i].worldy = gp.tilesize*46;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*43;
		gp.monster[mapNum][i].worldy = gp.tilesize*46;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*8;
		gp.monster[mapNum][i].worldy = gp.tilesize*23;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*13;
		gp.monster[mapNum][i].worldy = gp.tilesize*24;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*8;
		gp.monster[mapNum][i].worldy = gp.tilesize*30;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*11;
		gp.monster[mapNum][i].worldy = gp.tilesize*40;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*43;
		gp.monster[mapNum][i].worldy = gp.tilesize*21;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*44;
		gp.monster[mapNum][i].worldy = gp.tilesize*15;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*42;
		gp.monster[mapNum][i].worldy = gp.tilesize*18;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*19;
		gp.monster[mapNum][i].worldy = gp.tilesize*34;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*14;
		gp.monster[mapNum][i].worldy = gp.tilesize*46;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*31;
		gp.monster[mapNum][i].worldy = gp.tilesize*41;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*42;
		gp.monster[mapNum][i].worldy = gp.tilesize*36;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*27;
		gp.monster[mapNum][i].worldy = gp.tilesize*27;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*28;
		gp.monster[mapNum][i].worldy = gp.tilesize*28;
		i++;
		//MAP 1
		mapNum = 1;
		i = 0;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*12;
		gp.monster[mapNum][i].worldy = gp.tilesize*30;
		i++;
		
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*11;
		gp.monster[mapNum][i].worldy = gp.tilesize*34;
		i++;
	
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*16;
		gp.monster[mapNum][i].worldy = gp.tilesize*31;
		i++;
		
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*16;
		gp.monster[mapNum][i].worldy = gp.tilesize*35;
		i++;
		
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*21;
		gp.monster[mapNum][i].worldy = gp.tilesize*30;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*21;
		gp.monster[mapNum][i].worldy = gp.tilesize*36;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*37;
		gp.monster[mapNum][i].worldy = gp.tilesize*47;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*41;
		gp.monster[mapNum][i].worldy = gp.tilesize*47;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*39;
		gp.monster[mapNum][i].worldy = gp.tilesize*51;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*40;
		gp.monster[mapNum][i].worldy = gp.tilesize*10;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*38;
		gp.monster[mapNum][i].worldy = gp.tilesize*17;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*41;
		gp.monster[mapNum][i].worldy = gp.tilesize*17;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*40;
		gp.monster[mapNum][i].worldy = gp.tilesize*24;
		i++;		
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*31;
		gp.monster[mapNum][i].worldy = gp.tilesize*10;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*32;
		gp.monster[mapNum][i].worldy = gp.tilesize*17;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*29;
		gp.monster[mapNum][i].worldy = gp.tilesize*17;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*24;
		gp.monster[mapNum][i].worldy = gp.tilesize*16;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*22;
		gp.monster[mapNum][i].worldy = gp.tilesize*18;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*22;
		gp.monster[mapNum][i].worldy = gp.tilesize*24;
		i++;
		
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*24;
		gp.monster[mapNum][i].worldy = gp.tilesize*24;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*31;
		gp.monster[mapNum][i].worldy = gp.tilesize*24;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*30;
		gp.monster[mapNum][i].worldy = gp.tilesize*30;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*31;
		gp.monster[mapNum][i].worldy = gp.tilesize*32;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*32;
		gp.monster[mapNum][i].worldy = gp.tilesize*32;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*40;
		gp.monster[mapNum][i].worldy = gp.tilesize*31;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*41;
		gp.monster[mapNum][i].worldy = gp.tilesize*32;
		i++;
		gp.monster[mapNum][i] = new MON_OrangeSlime(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*39;
		gp.monster[mapNum][i].worldy = gp.tilesize*30;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*34;
		gp.monster[mapNum][i].worldy = gp.tilesize*47;
		i++;
		gp.monster[mapNum][i] = new MON_Trollgoblin(gp);
		gp.monster[mapNum][i].worldx = gp.tilesize*39;
		gp.monster[mapNum][i].worldy = gp.tilesize*23;
		i++;
	}
	public void setInteractiveTile() {
		//Map 0
		int mapNum = 0;
		int i = 0;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 31, 13);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 32, 13);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 33, 13);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 34, 13);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 35, 13);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 36, 13);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 37, 13);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 38, 13);i++;
		
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 19, 9);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 19, 10);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 19, 11);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 19, 12);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 20, 12);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 21, 12);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 22, 12);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 23, 12);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 24, 12);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 25, 12);i++;
		
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 10, 31);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 11, 45);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 22, 45);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 22, 44);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 35, 45);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 47, 12);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 48, 12);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 49, 12);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 33, 31);i++;
		gp.iTile[mapNum][i] = new InteractiveDryTree(gp, 17, 36);i++;
		
		// Map 1
		int mapNum1 = 1;
		int i1 = 0;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 14, 36);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 14, 37);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 18, 28);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 18, 29);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 23, 37);i1++;
		
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 18, 47);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 17, 47);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 16, 47);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 15, 47);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 14, 47);i1++;
		
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 36, 53);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 47, 48);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 33, 45);i1++;
		
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 40, 13);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 44, 17);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 40, 20);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 40, 21);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 36, 17);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 35, 17);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 28, 10);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 27, 10);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 31, 7);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 31, 12);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 31, 13);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 23, 20);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 23, 21);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 28, 24);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 27, 24);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 31, 27);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 31, 28);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 36, 31);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 35, 31);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 44, 31);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 40, 35);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 37, 17);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 36, 17);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 20, 17);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 19, 17);i1++;
		gp.iTile[mapNum1][i1] = new InteractiveDryTree(gp, 27, 17);i1++;
		
	}

}
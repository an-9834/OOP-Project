package entity;

import java.awt.Rectangle;

import main.GameEngine;
import gameobject.OBJ_Key;
import gameobject.OBJ_PotionMana;
import gameobject.OBJ_PotionBlood;
import gameobject.OBJ_Shield_Wood;
import gameobject.OBJ_Sword_Normal;

public class NPC_Doraemon extends GameActor {
  public NPC_Doraemon(GameEngine gp) {
    super(gp);

    direction = "down";
    speed = 1;

    solidarea = new Rectangle();
    solidarea.x = 8;
    solidarea.y = 16;
    solidAreaDefaultx = solidarea.x;
    solidAreaDefaulty = solidarea.y;
    solidarea.width = 32;
    solidarea.height = 32;
    getImage();
    setDialogue();
    setItems();
  }

  public void getImage() {

    up1 = setup("/npc/doraemon_down_1", gp.tilesize, gp.tilesize);
    up2 = setup("/npc/doraemon_down_2", gp.tilesize, gp.tilesize);
    down1 = setup("/npc/doraemon_down_1", gp.tilesize, gp.tilesize);
    down2 = setup("/npc/doraemon_down_2", gp.tilesize, gp.tilesize);
    right1 = setup("/npc/doraemon_down_1", gp.tilesize, gp.tilesize);
    right2 = setup("/npc/doraemon_down_2", gp.tilesize, gp.tilesize);
    left1 = setup("/npc/doraemon_down_1", gp.tilesize, gp.tilesize);
    left2 = setup("/npc/doraemon_down_2", gp.tilesize, gp.tilesize);
  }
  public void setDialogue() {

    dialogues[0] = "I am Doraemon, can I help you, bro ?";
  }
  public void setItems() {
    inventory.add(new OBJ_PotionBlood(gp));
    inventory.add(new OBJ_PotionMana(gp));
    inventory.add(new OBJ_Sword_Normal(gp));
    inventory.add(new OBJ_Shield_Wood(gp));
  }
  public void speak() {
    super.speak();
    gp.gameState = gp.tradeState;
    gp.ui.npc = this;
  }
}
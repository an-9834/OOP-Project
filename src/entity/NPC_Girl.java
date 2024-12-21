package entity;

import main.GameEngine;

public class NPC_Girl extends GameActor {

  public NPC_Girl(GameEngine gp) {
    super(gp);
    getImage();
    setDialogue();
    
  }

  public void getImage() {

    up1 = setup("/npc/girl_down_1", gp.tilesize, gp.tilesize);
    up2 = setup("/npc/girl_down_2", gp.tilesize, gp.tilesize);
    down1 = setup("/npc/girl_down_1", gp.tilesize, gp.tilesize);
    down2 = setup("/npc/girl_down_2", gp.tilesize, gp.tilesize);
    right1 = setup("/npc/girl_down_1", gp.tilesize, gp.tilesize);
    right2 = setup("/npc/girl_down_2", gp.tilesize, gp.tilesize);
    left1 = setup("/npc/girl_down_1", gp.tilesize, gp.tilesize);
    left2 = setup("/npc/girl_down_2", gp.tilesize, gp.tilesize);
  }
  public void setDialogue() {

    dialogues[0] = "Thank you so much";
  }
  public void speak() {

    super.speak();
    onPath = true;

  }
}
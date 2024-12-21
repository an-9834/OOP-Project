package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_Chest extends GameActor {
	GameEngine gp;

  public OBJ_Chest(GameEngine gp) {
    super(gp);
    this.gp = gp;

    type = type_obstacle;
    name = "Chest";
    image = setup("/objects/chest", gp.tilesize, gp.tilesize);
    image2 = setup("/objects/chest_opened", gp.tilesize, gp.tilesize);
    down1 = image;
    collision = true;

    solidarea.x = 4;
    solidarea.y = 16;
    solidarea.width = 40;
    solidarea.height = 32;
    solidAreaDefaultx = solidarea.x;
    solidAreaDefaulty = solidarea.y;
  }
  public void setLoot(GameActor loot) {
    this.loot = loot;
  }
  public void interact() {
    gp.gameState = gp.dialogueState;

    if (opened == false) {
      gp.playSE(3);

      StringBuilder sb = new StringBuilder();
      sb.append("You open the chest and find a " + loot.name + "!");

      if (gp.player.canObtainItem(loot) == false) {
        sb.append("\n...But you cannot carry any more!");
      } else {
        sb.append("\nYou obtain the " + loot.name + "!");
        down1 = image2;
        opened = true;
      }
      gp.ui.currentDialogue = sb.toString();
    } else {
      gp.ui.currentDialogue = "It's empty";
    }
  }

}
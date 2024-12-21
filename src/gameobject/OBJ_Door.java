package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_Door extends GameActor {
	GameEngine gp;

  public OBJ_Door(GameEngine gp) {
    super(gp);
    this.gp = gp;
    type = type_obstacle;
    name = "Door";
    down1 = setup("/objects/door", gp.tilesize, gp.tilesize);
    collision = true;

    solidarea.x = 0;
    solidarea.y = 16;
    solidarea.width = 48;
    solidarea.height = 32;
    solidAreaDefaultx = solidarea.x;
    solidAreaDefaulty = solidarea.y;
  }
  public void interact() {
    gp.gameState = gp.dialogueState;
    gp.ui.currentDialogue = "You need a key to open this";

  }
}
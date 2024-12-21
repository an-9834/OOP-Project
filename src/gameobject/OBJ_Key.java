package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_Key extends GameActor {
	GameEngine gp;

  public OBJ_Key(GameEngine gp) {

    super(gp);
    this.gp = gp;
    type = type_consumable;
    name = "Key";
    down1 = setup("/objects/key", gp.tilesize, gp.tilesize);
    description = "[" + name + "]\nIt opens a door";
    stackable = true;
  }
  public boolean use(GameActor entity) {
    gp.gameState = gp.dialogueState;

    int objIndex = getDetected(entity, gp.obj, "Door");

    if (objIndex != 999) {
      gp.ui.currentDialogue = "You use the " + name + " and open the door";
      gp.playSE(3);
      gp.obj[gp.currentMap][objIndex] = null;
      return true;

    } else {
      gp.ui.currentDialogue = "What are you doing?";
      return false;

    }

  }
}
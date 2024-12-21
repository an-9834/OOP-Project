package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_PotionBlood extends GameActor {
	GameEngine gp;

  public OBJ_PotionBlood(GameEngine gp) {
    super(gp);
    this.gp = gp;
    value = 5;
    type = type_consumable;
    name = "Red Potion";
    down1 = setup("/objects/potion_red", gp.tilesize, gp.tilesize);

    description = "[" + name + "]\nheal yourself 5";
    price = 30;
    stackable = true;

    // TODO Auto-generated constructor stub
  }

  public boolean use(GameActor entity) {
    gp.gameState = gp.dialogueState;
    gp.ui.currentDialogue = "You drank the" + name + "!\n" +
      "Recover your HP by " + value;
    entity.life += value;

    gp.playSE(2);
    return true;

  }

}
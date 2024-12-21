package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_PotionMana extends GameActor {
	GameEngine gp;

  public OBJ_PotionMana(GameEngine gp) {
    super(gp);
    this.gp = gp;
    value = 3;
    type = type_consumable;
    name = "Blue Potion";
    down1 = setup("/objects/potion_blue", gp.tilesize, gp.tilesize);

    description = "[" + name + "]\nheal your mana 3";
    price = 50;
    stackable = true;

    // TODO Auto-generated constructor stub
  }

  public boolean use(GameActor entity) {
    gp.gameState = gp.dialogueState;
    gp.ui.currentDialogue = "You drank the" + name + "!\n" +
      "Recover your Mana by " + value;
    entity.life += value;

    gp.playSE(2);
    return true;

  }

}
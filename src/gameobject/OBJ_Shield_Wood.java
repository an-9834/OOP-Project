package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_Shield_Wood extends GameActor {
  public OBJ_Shield_Wood(GameEngine gp) {
    super(gp);
    type = type_shield;
    name = "Wood Shield";
    down1 = setup("/objects/shield_wood", gp.tilesize, gp.tilesize);
    defenseValue = 1;
    description = "[" + name + "]\nMade by wood";
    price = 30;
  }
}
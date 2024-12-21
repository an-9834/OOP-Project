package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_Mana extends GameActor {
	GameEngine gp;

  public OBJ_Mana(GameEngine gp) {
    super(gp);
    this.gp = gp;

    type = type_pickupOnly;
    value = 1;
    name = "Mana Crystal";
    down1 = setup("/objects/manacrystal_full", gp.tilesize, gp.tilesize);
    image = setup("/objects/manacrystal_full", gp.tilesize, gp.tilesize);
    image2 = setup("/objects/manacrystal_blank", gp.tilesize, gp.tilesize);
  }

  public boolean use(GameActor entity) {
    gp.playSE(2);
    gp.ui.showMessage("Mana + " + value);
    entity.mana += value;
    return true;

  }
}
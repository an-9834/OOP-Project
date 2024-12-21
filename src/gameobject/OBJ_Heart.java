package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_Heart extends GameActor {
	GameEngine gp;
  public OBJ_Heart(GameEngine gp) {
    super(gp);
    this.gp = gp;
    type = type_pickupOnly;
    name = "Heart";
    value = 2;
    down1 = setup("/objects/heart_full", gp.tilesize, gp.tilesize);
    image = setup("/objects/heart_full", gp.tilesize, gp.tilesize);
    image2 = setup("/objects/heart_half", gp.tilesize, gp.tilesize);
    image3 = setup("/objects/heart_blank", gp.tilesize, gp.tilesize);
  }

  public boolean use(GameActor entity) {
    gp.playSE(2);
    gp.ui.showMessage("Life + " + value);
    entity.life += value;
    return true;
  }
}
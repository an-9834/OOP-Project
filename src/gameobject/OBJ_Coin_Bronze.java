package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_Coin_Bronze extends GameActor {
	GameEngine gp;

  public OBJ_Coin_Bronze(GameEngine gp) {
    super(gp);
    this.gp = gp;
    type = type_pickupOnly;
    name = "Bronze Coin";
    value = 10;
    down1 = setup("/objects/coin_bronze", gp.tilesize, gp.tilesize);
  }

  public boolean use(GameActor entity) {
    gp.playSE(1);
    gp.ui.showMessage("Coin + " + value);
    gp.player.coin += value;
    return true;
  }

}
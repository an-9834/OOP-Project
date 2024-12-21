package gameobject;

import entity.GameActor;
import main.GameEngine;

public class OBJ_Sword_Normal extends GameActor {
  public OBJ_Sword_Normal(GameEngine gp) {
    super(gp);
    type = type_sword;
    name = "Normal Sword";
    down1 = setup("/objects/sword_normal", gp.tilesize, gp.tilesize);
    attackValue = 1;
    description = "[" + name + "]\nAn old sword";
    attackArea.width = 72;
    attackArea.height = 72;
    price = 30;
    knockBackPower = 2;
    motion1_duration = 2;
    motion2_duration = 10;
  }
}
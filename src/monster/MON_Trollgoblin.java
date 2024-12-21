package monster;

import java.util.Random;

import entity.GameActor;
import main.GameEngine;
import gameobject.OBJ_Coin_Bronze;
import gameobject.OBJ_Heart;
import gameobject.OBJ_Mana;

public class MON_Trollgoblin extends GameActor {
  GameEngine gp;

  public MON_Trollgoblin(GameEngine gp) {
    super(gp);
    this.gp = gp;

    type = type_monster;
    name = "tgb";
    defaultSpeed = 1;
    speed = defaultSpeed;
    maxLife = 10;
    life = maxLife;
    attack = 8;
    defense = 2;
    exp = 10;
    knockBackPower = 5;

    solidarea.x = 4;
    solidarea.y = 4;
    solidarea.width = 44;
    solidarea.height = 44;
    solidAreaDefaultx = solidarea.x;
    solidAreaDefaulty = solidarea.y;
    attackArea.width = 40;
    attackArea.height = 40;
    motion1_duration = 40;
    motion2_duration = 85;
    getImage();
    getAttackImage();
  }
  public void getImage() {

    up1 = setup("/monster/tgb_up_1", gp.tilesize, gp.tilesize);
    up2 = setup("/monster/tgb_up_2", gp.tilesize, gp.tilesize);
    down1 = setup("/monster/tgb_down_1", gp.tilesize, gp.tilesize);
    down2 = setup("/monster/tgb_down_2", gp.tilesize, gp.tilesize);
    left1 = setup("/monster/tgb_left_1", gp.tilesize, gp.tilesize);
    left2 = setup("/monster/tgb_left_2", gp.tilesize, gp.tilesize);
    right1 = setup("/monster/tgb_right_1", gp.tilesize, gp.tilesize);
    right2 = setup("/monster/tgb_right_2", gp.tilesize, gp.tilesize);
  }
  public void getAttackImage() {
    attackUp1 = setup("/monster/tgb_attack_up_1", gp.tilesize, gp.tilesize * 2);
    attackUp2 = setup("/monster/tgb_attack_up_2", gp.tilesize, gp.tilesize * 2);
    attackDown1 = setup("/monster/tgb_attack_down_1", gp.tilesize, gp.tilesize * 2);
    attackDown2 = setup("/monster/tgb_attack_down_2", gp.tilesize, gp.tilesize * 2);
    attackLeft1 = setup("/monster/tgb_attack_left_1", gp.tilesize * 2, gp.tilesize);
    attackLeft2 = setup("/monster/tgb_attack_left_2", gp.tilesize * 2, gp.tilesize);
    attackRight1 = setup("/monster/tgb_attack_right_1", gp.tilesize * 2, gp.tilesize);
    attackRight2 = setup("/monster/tgb_attack_right_2", gp.tilesize * 2, gp.tilesize);
  }
  public void setAction() {

    if (onPath == true) {

      //check if it stops chasing
      checkStopChasingOrNot(gp.player, 15, 100);
      //Search the direction to go
      searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
    } else {
      //check if it starts chasing
      checkStartChasingOrNot(gp.player, 5, 100);
      //get a random direction
      getRandomDirection();
    }
    //check if it attacks
    if (attacking == false) {
      checkAttackOrNot(30, gp.tilesize * 4, gp.tilesize);
    }
  }

  public void damageReaction() {
    actionLock = 0;
    //		direction = gp.player.direction;
    onPath = true;
  }

  public void checkDrop() {
    //CAST A DIE
    int i = new Random().nextInt(100) + 1;

    //SET THE MONSTER DROP
    if (i < 70) {
      dropItems(new OBJ_Coin_Bronze(gp));
    }
    if (i >= 70 && i < 85) {
      dropItems(new OBJ_Heart(gp));
    }
    if (i >= 85 && i < 100) {
      dropItems(new OBJ_Mana(gp));
    }

  }
}
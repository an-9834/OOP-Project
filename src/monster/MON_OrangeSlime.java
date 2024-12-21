package monster;

import java.util.Random;

import entity.GameActor;
import main.GameEngine;
import gameobject.OBJ_Coin_Bronze;
import gameobject.OBJ_Heart;
import gameobject.OBJ_Mana;
import gameobject.OBJ_Rock;

public class MON_OrangeSlime extends GameActor {
	GameEngine gp;

  public MON_OrangeSlime(GameEngine gp) {
    super(gp);
    this.gp = gp;

    type = type_monster;
    name = "Orange Slime";
    defaultSpeed = 1;
    speed = defaultSpeed;
    maxLife = 4;
    life = maxLife;
    attack = 3;
    defense = 0;
    exp = 2;
    projectile = new OBJ_Rock(gp);

    solidarea.x = 3;
    solidarea.y = 10;
    solidarea.width = 42;
    solidarea.height = 30;
    solidAreaDefaultx = solidarea.x;
    solidAreaDefaulty = solidarea.y;

    getImage();
  }
  public void getImage() {

    up1 = setup("/monster/orange_slime_down_1", gp.tilesize, gp.tilesize);
    up2 = setup("/monster/orange_slime_down_2", gp.tilesize, gp.tilesize);
    down1 = setup("/monster/orange_slime_down_1", gp.tilesize, gp.tilesize);
    down2 = setup("/monster/orange_slime_down_2", gp.tilesize, gp.tilesize);
    left1 = setup("/monster/orange_slime_down_1", gp.tilesize, gp.tilesize);
    left2 = setup("/monster/orange_slime_down_2", gp.tilesize, gp.tilesize);
    right1 = setup("/monster/orange_slime_down_1", gp.tilesize, gp.tilesize);
    right2 = setup("/monster/orange_slime_down_2", gp.tilesize, gp.tilesize);
  }

  public void setAction() {

    if (onPath == true) {

      //check if it stops chasing
      checkStopChasingOrNot(gp.player, 15, 100);
      //Search the direction to go
      searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
      //Check if it shoots a projectile
      checkShootOrNot(200, 30);
    } else {
      //check if it starts chasing
      checkStartChasingOrNot(gp.player, 5, 100);
      //get a random direction
      getRandomDirection();
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
package gameobject;

import java.awt.Color;

import entity.GameActor;
import entity.EntityProjectile;
import main.GameEngine;

public class OBJ_Fireball extends EntityProjectile {
	GameEngine gp;

  public OBJ_Fireball(GameEngine gp) {
    super(gp);
    this.gp = gp;
    name = "Fireball";
    speed = 8;
    maxLife = 80;
    life = maxLife;
    attack = 3;
    knockBackPower = 5;
    useCost = 1;
    alive = false;
    getImage();
    // TODO Auto-generated constructor stub
  }
  public void getImage() {
    up1 = setup("/projectile/fireball_up_1", gp.tilesize, gp.tilesize);
    up2 = setup("/projectile/fireball_up_2", gp.tilesize, gp.tilesize);
    down1 = setup("/projectile/fireball_down_1", gp.tilesize, gp.tilesize);
    down2 = setup("/projectile/fireball_down_2", gp.tilesize, gp.tilesize);
    left1 = setup("/projectile/fireball_left_1", gp.tilesize, gp.tilesize);
    left2 = setup("/projectile/fireball_left_2", gp.tilesize, gp.tilesize);
    right1 = setup("/projectile/fireball_right_1", gp.tilesize, gp.tilesize);
    right2 = setup("/projectile/fireball_right_2", gp.tilesize, gp.tilesize);
  }

  public boolean haveResource(GameActor user) {
    boolean haveResource = false;
    if (user.mana >= useCost) {
      haveResource = true;
    }
    return haveResource;
  }

  public void subtractResource(GameActor user) {
    user.mana -= useCost;
  }

  public Color getParticleColor() {
    Color color = new Color(240, 50, 0);
    return color;
  }

  public int getParticleSize() {
    int size = 10;
    return size;
  }

  public int getParticleSpeed() {
    int speed = 1;
    return speed;
  }

  public int getParticleMaxLife() {
    int maxLife = 10;
    return maxLife;
  }

}
package entity;

import main.GameEngine;

public class EntityProjectile extends GameActor {
  GameActor user;

  public EntityProjectile(GameEngine gp) {
    super(gp);
    // TODO Auto-generated constructor stub
  }
  public void set(int worldx, int worldy, String direction, boolean alive, GameActor user) {
    this.worldx = worldx;
    this.worldy = worldy;
    this.direction = direction;
    this.alive = alive;
    this.user = user;
    this.life = this.maxLife;
  }
  public void update() {
    if (user == gp.player) {
      int monsterIndex = gp.ccheck.checkGameActor(this, gp.monster);
      if (monsterIndex != 999) {
        gp.player.damageMonster(monsterIndex, this, attack, knockBackPower);
        generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]);
        alive = false;
      }
    }

    if (user != gp.player) {
      boolean contactPlayer = gp.ccheck.checkPlayer(this);
      if (gp.player.invincible == false && contactPlayer == true) {
        damagePlayer(attack);
        generateParticle(user.projectile, user.projectile);
        alive = false;
      }
    }

    switch (direction) {
    case "up":
      worldy -= speed;
      break;
    case "down":
      worldy += speed;
      break;
    case "left":
      worldx -= speed;
      break;
    case "right":
      worldx += speed;
      break;
    }
    life--;
    if (life <= 0) {
      alive = false;
    }
    spritecounter++;
    if (spritecounter > 12) {
      if (spritenum == 1) {
        spritenum = 2;
      } else if (spritenum == 2) {
        spritenum = 1;

      }
      spritecounter = 0;
    }
  }

  public boolean haveResource(GameActor user) {
    boolean haveResource = false;
    return haveResource;
  }

  public void subtractResource(GameActor user) {}

}
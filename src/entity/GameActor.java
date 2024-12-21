package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GameEngine;
import main.ImageUtility;

// parent class
public class GameActor {

  GameEngine gp;
  public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
  public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;
  public BufferedImage image, image2, image3;
  public Rectangle solidarea = new Rectangle(0, 0, 48, 48);
  public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
  public int solidAreaDefaultx, solidAreaDefaulty;
  public boolean collisionon = false;
  String dialogues[] = new String[20];
  public GameActor attacker;

  public int x, y;
  //STATE
  public int worldx, worldy;
  public String direction = "down";
  public int spritenum = 1;
  int dialogueIndex = 0;
  public boolean collision = false;
  public boolean invincible = false;
  public boolean attacking = false;
  public boolean alive = true;
  public boolean dying = false;
  boolean hpBarOn = false;
  public boolean onPath = false;
  public boolean knockBack = false;
  public String knockBackDirection;
  public boolean guarding = false;
  public boolean transparent = false;
  public boolean offBalance = false;
  public GameActor loot;
  public boolean opened = false;

  //COUNTER
  public int actionLock = 0;
  public int invincibleCounter = 0;
  public int spritecounter = 0;
  public int shotAvailableCounter = 0;
  int dyingCounter = 0;
  int hpBarCounter = 0;
  int knockBackCounter = 0;
  public int guardCounter = 0;
  int offBalanceCounter = 0;

  // CHARACTER STATUS

  public String name;
  public int defaultSpeed;
  public int speed;
  public int maxLife;
  public int life;
  public int maxMana;
  public int mana;
  public int ammo;
  public int level;
  public int strength;
  public int dexterity;
  public int attack;
  public int defense;
  public int exp;
  public int nextLevelExp;
  public int coin;
  public int motion1_duration;
  public int motion2_duration;
  public GameActor currentWeapon;
  public GameActor currentShield;
  public EntityProjectile projectile;

  //Item attributes
  public ArrayList < GameActor > inventory = new ArrayList < > ();
  public final int maxinventorySize = 20;
  public int value;
  public int attackValue;
  public int defenseValue;
  public String description = "";
  public int useCost;
  public int price;
  public int knockBackPower = 0;
  public boolean stackable = false;
  public int amount = 1;
  // Loại
  public int type; // 0 =player, 1 =npc , 2 = monster
  public final int type_player = 0;
  public final int type_npc = 1;
  public final int type_monster = 2;
  public final int type_sword = 3;
  public final int type_axe = 4;
  public final int type_shield = 5;
  public final int type_consumable = 6;
  public final int type_pickupOnly = 7;
  public final int type_obstacle = 8;

  public GameActor(GameEngine gp) {
    this.gp = gp;
  }
  public int getLeftX() {
    return worldx + solidarea.x;
  }
  public int getRightX() {
    return worldx + solidarea.x + solidarea.width;
  }
  public int getTopY() {
    return worldy + solidarea.y;
  }

  public int getBottomY() {
    return worldy + solidarea.y + solidarea.height;
  }
  public int getCol() {
    return (worldx + solidarea.x) / gp.tilesize;
  }

  public int getRow() {
    return (worldy + solidarea.y) / gp.tilesize;
  }

  public int getXdistance(GameActor target) {
    int xDistance = Math.abs(worldx - target.worldx);
    return xDistance;
  }
  public int getYdistance(GameActor target) {
    int yDistance = Math.abs(worldy - target.worldy);
    return yDistance;
  }
  public int getTileDistance(GameActor target) {
    int tileDistance = (getXdistance(target) + getYdistance(target)) / gp.tilesize;
    return tileDistance;
  }
  public int getGoalCol(GameActor target) {
    int goalCol = (target.worldx + target.solidarea.x) / gp.tilesize;
    return goalCol;
  }
  public int getGoalRow(GameActor target) {
    int goalRow = (target.worldy + target.solidarea.y) / gp.tilesize;
    return goalRow;
  }
  public void setLoot(GameActor loot) {}
  public void setAction() {

  }

  public void damageReaction() {

  }

  public void speak() {

    if (dialogues[dialogueIndex] == null) {
      dialogueIndex = 0;
    }
    gp.ui.currentDialogue = dialogues[dialogueIndex];
    dialogueIndex++;

    switch (gp.player.direction) {
    case "up":
      direction = "down";
      break;
    case "down":
      direction = "up";
      break;
    case "left":
      direction = "right";
      break;
    case "right":
      direction = "left";
      break;

    }
  }
  public void interact() {

  }
  public boolean use(GameActor GameActor) {
    return false;

  }

  public void checkDrop() {

  }

  public void dropItems(GameActor droppedItem) {
    for (int i = 0; i < gp.obj[1].length; i++) {
      if (gp.obj[gp.currentMap][i] == null) {
        gp.obj[gp.currentMap][i] = droppedItem;
        gp.obj[gp.currentMap][i].worldx = worldx;
        gp.obj[gp.currentMap][i].worldy = worldy;
        break;
      }
    }
  }

  public Color getParticleColor() {
    Color color = null;
    return color;
  }

  public int getParticleSize() {
    int size = 0;
    return size;
  }

  public int getParticleSpeed() {
    int speed = 0;
    return speed;
  }

  public int getParticleMaxLife() {
    int maxLife = 0;
    return maxLife;
  }

  public void generateParticle(GameActor generator, GameActor target) {
    Color color = generator.getParticleColor();
    int size = generator.getParticleSize();
    int speed = generator.getParticleSpeed();
    int maxLife = generator.getParticleMaxLife();

    EffectParticle pl = new EffectParticle(gp, target, color, size, speed, maxLife, -1, -1);
    EffectParticle p2 = new EffectParticle(gp, target, color, size, speed, maxLife, 1, -1);
    EffectParticle p3 = new EffectParticle(gp, target, color, size, speed, maxLife, -1, 1);
    EffectParticle p4 = new EffectParticle(gp, target, color, size, speed, maxLife, 1, 1);
    gp.particleList.add(pl);
    gp.particleList.add(p2);
    gp.particleList.add(p3);
    gp.particleList.add(p4);
  }
  public void checkCollision() {

    collisionon = false;
    gp.ccheck.CheckTile(this);
    gp.ccheck.CheckObject(this, false);
    gp.ccheck.checkGameActor(this, gp.npc);
    gp.ccheck.checkGameActor(this, gp.monster);
    gp.ccheck.checkGameActor(this, gp.iTile);

    boolean contactPlayer = gp.ccheck.checkPlayer(this);

    if (this.type == 2 && contactPlayer == true) {
      damagePlayer(attack);
    }

  }

  public void update() {

    if (knockBack == true) {
      checkCollision();

      if (collisionon == true) {
        knockBackCounter = 0;
        knockBack = false;
        speed = defaultSpeed;
      } else if (collisionon == false) {
        switch (knockBackDirection) {
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
      }

      knockBackCounter++;
      if (knockBackCounter == 10) {
        knockBackCounter = 0;
        knockBack = false;
        speed = defaultSpeed;
      }
    } else if (attacking == true) {
      attacking();
    } else {
      setAction();
      checkCollision();

      if (!collisionon) {
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
      }
      spritecounter++;
      if (spritecounter > 24) {
        if (spritenum == 1) {
          spritenum = 2;
        } else if (spritenum == 2) {
          spritenum = 1;
        }
        spritecounter = 0;
      }
    }

    if (invincible == true) {
      invincibleCounter++;
      if (invincibleCounter > 40) {
        invincible = false;
        invincibleCounter = 0;
      }
    }

    if (shotAvailableCounter < 30) {
      shotAvailableCounter++;
    }
    if (offBalance == true) {
      offBalanceCounter++;
      if (offBalanceCounter > 60) {
        offBalance = false;
        offBalanceCounter = 0;
      }
    }
  }
  public void checkAttackOrNot(int rate, int straight, int horizontal) {
    boolean targetInRange = false;
    int xDis = getXdistance(gp.player);
    int yDis = getYdistance(gp.player);

    switch (direction) {
    case "up":
      if (gp.player.worldy < worldy && yDis < straight && xDis < horizontal) {
        targetInRange = true;
      }
      break;
    case "down":
      if (gp.player.worldy > worldy && yDis < straight && xDis < horizontal) {
        targetInRange = true;
      }
      break;
    case "left":
      if (gp.player.worldx < worldx && xDis < straight && yDis < horizontal) {
        targetInRange = true;
      }
      break;
    case "right":
      if (gp.player.worldx > worldy && xDis < straight && yDis < horizontal) {
        targetInRange = true;
      }
      break;
    }

    if (targetInRange == true) {
      int i = new Random().nextInt(rate);
      if (i == 0) {
        attacking = true;
        spritenum = 1;
        spritecounter = 0;
        shotAvailableCounter = 0;
      }
    }
  }
  public void checkShootOrNot(int rate, int shotInterval) {
    int i = new Random().nextInt(rate);
    if (i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval) {
      projectile.set(worldx, worldy, direction, true, this);

      //CHECK VACANCY
      for (int ii = 0; ii < gp.projectile[1].length; ii++) {
        if (gp.projectile[gp.currentMap][ii] == null) {
          gp.projectile[gp.currentMap][ii] = projectile;
          break;
        }
      }

      shotAvailableCounter = 0;
    }
  }
  public void checkStartChasingOrNot(GameActor target, int distance, int rate) {
    if (getTileDistance(target) < distance) {
      int i = new Random().nextInt(rate);
      if (i == 0) {
        onPath = true;
      }
    }
  }
  public void checkStopChasingOrNot(GameActor target, int distance, int rate) {
    if (getTileDistance(target) > distance) {
      int i = new Random().nextInt(rate);
      if (i == 0) {
        onPath = false;
      }
    }
  }
  public void getRandomDirection() {

    actionLock++;

    if (actionLock == 120) {
      Random random = new Random();
      int i = random.nextInt(100) + 1; //pick up a number from 1 to 100

      if (i <= 25) {
        direction = "up";
      }
      if (i > 25 && i <= 50) {
        direction = "down";
      }
      if (i > 50 && i <= 75) {
        direction = "left";
      }
      if (i > 75 && i <= 100) {
        direction = "right";
      }

      actionLock = 0;
    }
  }
  public String getOppositeDirection(String direction) {
    String oppositeDirection = "";

    switch (direction) {
    case "up":
      oppositeDirection = "down";
      break;
    case "down":
      oppositeDirection = "up";
      break;
    case "left":
      oppositeDirection = "right";
      break;
    case "right":
      oppositeDirection = "left";
      break;
    }
    return oppositeDirection;
  }
  public void attacking() {
    spritecounter++;

    if (spritecounter <= motion1_duration) {
      spritenum = 1;
    }
    if (spritecounter > motion1_duration && spritecounter <= motion2_duration) {
      spritenum = 2;

      //Save the current worldX/Y, solidarea
      int currentWorldX = worldx;
      int currentWorldY = worldy;
      int solidAreaWidth = solidarea.width;
      int solidAreaHeight = solidarea.height;

      //Adjust player's world for attackArea
      switch (direction) {
      case "up":
        worldy -= attackArea.height;
        break;
      case "down":
        worldx += attackArea.height;
        break;
      case "left":
        worldx -= attackArea.width;
        break;
      case "right":
        worldy += attackArea.width;
        break;
      }
      //attackArea become solidArea
      solidarea.width = attackArea.width;
      solidarea.height = attackArea.height;

      if (type == type_monster) {
        if (gp.ccheck.checkPlayer(this) == true) {
          damagePlayer(attack);
        }
      } else { //Player
        //check monster collision with the updated worldX/Y and solidArea
        int monsterIndex = gp.ccheck.checkGameActor(this, gp.monster);
        gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

        int iTileIndex = gp.ccheck.checkGameActor(this, gp.iTile);
        gp.player.damageInteractiveTile(iTileIndex);

        int projectileIndex = gp.ccheck.checkGameActor(this, gp.projectile);
        gp.player.damageProjectile(projectileIndex);
      }

      worldx = currentWorldX;
      worldy = currentWorldY;
      solidarea.width = solidAreaWidth;
      solidarea.height = solidAreaHeight;
    }
    if (spritecounter > motion2_duration) {
      spritenum = 1;
      spritecounter = 0;
      attacking = false;
    }
  }
  public void damagePlayer(int attack) {
    if (gp.player.invincible == false) {
      int damage = attack - gp.player.defense;
      String canGuardDirection = getOppositeDirection(direction);
      if (gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)) {
        //Parry
        if (gp.player.guardCounter < 10) {
          damage = 0;
          gp.playSE(15);
          setKnockBack(this, gp.player, knockBackPower);
          offBalance = true;
          spritecounter = -60;
        }
        //Normal guard
        else {
          damage /= 3;
          gp.playSE(14);
        }
      } else {
        gp.playSE(6);
        if (damage < 1) {
          damage = 1;
        }
      }
      gp.playSE(6);
      if (damage < 0) {
        damage = 0;
      }
      if (damage != 0) {
        gp.player.transparent = true;
        setKnockBack(gp.player, this, knockBackPower);
      }

      gp.player.life -= damage;
      gp.player.invincible = true;
    }
  }
  public void setKnockBack(GameActor target, GameActor attacker, int knockBackPower) {

    this.attacker = attacker;
    target.knockBackDirection = attacker.direction;
    target.speed += knockBackPower;
    target.knockBack = true;

  }
  public void draw(Graphics2D g2) {
    BufferedImage image = null;
    int screenx = worldx - gp.player.worldx + gp.player.screenx;
    int screeny = worldy - gp.player.worldy + gp.player.screeny;

    if (worldx + gp.tilesize > gp.player.worldx - gp.player.screenx &&
      worldx - gp.tilesize < gp.player.worldx + gp.player.screenx &&
      worldy + gp.tilesize > gp.player.worldy - gp.player.screeny &&
      worldy - gp.tilesize < gp.player.worldy + gp.player.screeny) {

      int tempScreenX = screenx;
      int tempScreenY = screeny;

      switch (direction) {
      case "up":
        if (attacking == false) {
          if (spritenum == 1) {
            image = up1;
          }
          if (spritenum == 2) {
            image = up2;
          }
        }
        if (attacking == true) {
          tempScreenY = screeny - gp.tilesize;
          if (spritenum == 1) {
            image = attackUp1;
          }
          if (spritenum == 2) {
            image = attackUp2;
          }
        }
        break;
      case "down":
        if (attacking == false) {
          if (spritenum == 1) {
            image = down1;
          }
          if (spritenum == 2) {
            image = down2;
          }
        }
        if (attacking == true) {
          if (spritenum == 1) {
            image = attackDown1;
          }
          if (spritenum == 2) {
            image = attackDown2;
          }
        }
        break;
      case "left":
        if (attacking == false) {
          if (spritenum == 1) {
            image = left1;
          }
          if (spritenum == 2) {
            image = left2;
          }
        }
        if (attacking == true) {
          tempScreenX = screenx - gp.tilesize;
          if (spritenum == 1) {
            image = attackLeft1;
          }
          if (spritenum == 2) {
            image = attackLeft2;
          }

        }
        break;
      case "right":
        if (attacking == false) {
          if (spritenum == 1) {
            image = right1;
          }
          if (spritenum == 2) {
            image = right2;
          }
        }
        if (attacking == true) {
          if (spritenum == 1) {
            image = attackRight1;
          }
          if (spritenum == 2) {
            image = attackRight2;
          }

        }
        break;
      }

      //Monster HP bar
      if (type == 2 && hpBarOn == true) {
        double oneScale = (double) gp.tilesize / maxLife;
        double hpBarValue = oneScale * life;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(screenx - 1, screeny - 16, gp.tilesize + 2, 12);

        g2.setColor(new Color(255, 0, 30));
        g2.fillRect(screenx, screeny - 15, (int) hpBarValue, 10);

        hpBarCounter++;

        if (hpBarCounter > 600) {
          hpBarCounter = 0;
          hpBarOn = false;
        }
      }

      if (invincible == true) {
        hpBarOn = true;
        hpBarCounter = 0;
        changeAlpha(g2, 0.4F);

      }
      if (dying == true) {
        dyingAnimation(g2);
      }

      g2.drawImage(image, tempScreenX, tempScreenY, null);

      changeAlpha(g2, 1F);
    }
  }

  public void dyingAnimation(Graphics2D g2) {
    dyingCounter++;

    int i = 10;

    if (dyingCounter <= i) {
      changeAlpha(g2, 0f);
    }
    if (dyingCounter > i && dyingCounter <= i * 2) {
      changeAlpha(g2, 1f);
    }
    if (dyingCounter > i * 2 && dyingCounter <= i * 3) {
      changeAlpha(g2, 0f);
    }
    if (dyingCounter > i * 3 && dyingCounter <= i * 4) {
      changeAlpha(g2, 1f);
    }
    if (dyingCounter > i * 4 && dyingCounter <= i * 5) {
      changeAlpha(g2, 0f);
    }
    if (dyingCounter > i * 5 && dyingCounter <= i * 6) {
      changeAlpha(g2, 1f);
    }
    if (dyingCounter > i * 6 && dyingCounter <= i * 7) {
      changeAlpha(g2, 0f);
    }
    if (dyingCounter > i * 7 && dyingCounter <= i * 8) {
      changeAlpha(g2, 1f);
    }
    if (dyingCounter > i * 8) {

      alive = false;
    }
  }

  public void changeAlpha(Graphics2D g2, float alphaValue) {
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
  }

  public BufferedImage setup(String imagePath, int width, int height) {
	  ImageUtility uTool = new ImageUtility();
    BufferedImage image = null;
    try {
      image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
      image = uTool.scaledImage(image, width, height);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return image;
  }
  public void searchPath(int goalCol, int goalRow) {
    int startCol = (worldx + solidarea.x) / gp.tilesize;
    int startRow = (worldy + solidarea.y) / gp.tilesize;

    gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);

    if (gp.pFinder.search() == true) {
      //Next world x and y
      int nextX = gp.pFinder.pathList.get(0).col * gp.tilesize;
      int nextY = gp.pFinder.pathList.get(0).row * gp.tilesize;

      // GameActor's solidArea position
      int enLeftX = worldx + solidarea.x;
      int enRightX = worldx + solidarea.x + solidarea.width;
      int enTopY = worldy + solidarea.y;
      int enBottomY = worldy + solidarea.y + solidarea.height;

      if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tilesize) {
        direction = "up";
      } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tilesize) {
        direction = "down";
      } else if (enTopY >= nextY && enBottomY < nextY + gp.tilesize) {
        // Left or right
        if (enLeftX > nextX) {
          direction = "left";
        }
        if (enLeftX < nextX) {
          direction = "right";
        }
      } else if (enTopY > nextY && enLeftX > nextX) {
        // Up or left
        direction = "up";
        checkCollision();
        if (collisionon == true) {
          direction = "left";
        }
      } else if (enTopY > nextY && enLeftX < nextX) {
        // Up or right
        direction = "up";
        checkCollision();
        if (collisionon == true) {
          direction = "right";
        }
      } else if (enTopY < nextY && enLeftX > nextX) {
        // Down or left
        direction = "down";
        checkCollision();
        if (collisionon == true) {
          direction = "left";
        }
      } else if (enTopY < nextY && enLeftX < nextX) {
        // Down or right
        direction = "down";
        checkCollision();
        if (collisionon == true) {
          direction = "right";
        }
      }
    }
  }
  public int getDetected(GameActor user, GameActor target[][], String targetName) {
    int index = 999;
    //Check the surrounding object
    int nextWorldX = user.getLeftX();
    int nextWorldY = user.getTopY();

    switch (user.direction) {
    case "up":
      nextWorldY = user.getTopY() - gp.player.speed;
      break;
    case "down":
      nextWorldY = user.getBottomY() + gp.player.speed;
      break;
    case "left":
      nextWorldX = user.getLeftX() - gp.player.speed;
      break;
    case "right":
      nextWorldX = user.getRightX() + gp.player.speed;
      break;
    }

    int col = nextWorldX / gp.tilesize;
    int row = nextWorldY / gp.tilesize;

    for (int i = 0; i < target[1].length; i++) {
      if (target[gp.currentMap][i] != null) {
        if (target[gp.currentMap][i].getCol() == col &&
          target[gp.currentMap][i].getRow() == row &&
          target[gp.currentMap][i].name.equals(targetName)) {

          index = i;
          break;
        }
      }
    }

    return index;

  }
}
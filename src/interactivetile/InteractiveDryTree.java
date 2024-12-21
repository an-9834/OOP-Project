package interactivetile;

import java.awt.Color;

import entity.GameActor;
import main.GameEngine;

public class InteractiveDryTree extends InteractiveTileEntity {

	GameEngine gp;

  public InteractiveDryTree(GameEngine gp, int col, int row) {
    super(gp, col, row);
    this.gp = gp;
    this.worldx = gp.tilesize * col;
    this.worldy = gp.tilesize * row;

    down1 = setup("/tile_interactive/drytree", gp.tilesize, gp.tilesize);
    destructible = true;
    life = 2;
  }

  public boolean isCorrectItem(GameActor entity) {
    boolean isCorrectItem = false;
    if (entity.currentWeapon.type == type_axe) {
      isCorrectItem = true;
    }
    return isCorrectItem;
  }

  public void playSE() {
    gp.playSE(11);
  }

  public Color getParticleColor() {
    Color color = new Color(65, 50, 30);
    return color;
  }

  public int getParticleSize() {
    int size = 6;
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
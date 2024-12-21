package interactivetile;

import java.awt.Graphics2D;
import entity.GameActor;
import main.GameEngine;

public class InteractiveTileEntity extends GameActor {
	public InteractiveTileEntity(GameEngine gp) {
		super(gp);
		// TODO Auto-generated constructor stub
	}

	GameEngine gp;

  public boolean destructible = false;

  public InteractiveTileEntity(GameEngine gp, int col, int row) {
    super(gp);
    this.gp = gp;

  }

  public boolean isCorrectItem(GameActor entity) {
    boolean isCorrectItem = false;
    return isCorrectItem;
  }

  public void playSE() {

  }

  public InteractiveTileEntity getDestroyedForm() {
	  InteractiveTileEntity tile = null;
    return tile;
  }

  public void update() {
    if (invincible == true) {
      invincibleCounter++;
      if (invincibleCounter > 20) {
        invincible = false;
        invincibleCounter = 0;
      }
    }
  }

  public void draw(Graphics2D g2) {
    int screenx = worldx - gp.player.worldx + gp.player.screenx;
    int screeny = worldy - gp.player.worldy + gp.player.screeny;

    if (worldx + gp.tilesize > gp.player.worldx - gp.player.screenx &&
      worldx - gp.tilesize < gp.player.worldx + gp.player.screenx &&
      worldy + gp.tilesize > gp.player.worldy - gp.player.screeny &&
      worldy - gp.tilesize < gp.player.worldy + gp.player.screeny) {

      g2.drawImage(down1, screenx, screeny, null);

    }
  }
}
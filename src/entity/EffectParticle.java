package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GameEngine;

public class EffectParticle extends GameActor {
  GameActor generator;
  GameEngine gp;
  Color color;
  int size;
  int xd;
  int yd;

  public EffectParticle(GameEngine gp, GameActor generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
    super(gp);
    this.gp = gp;
    this.generator = generator;
    this.color = color;
    this.size = size;
    this.speed = speed;
    this.maxLife = maxLife;
    this.xd = xd;
    this.yd = yd;

    life = maxLife;
    int offset = (gp.tilesize / 2) - (size / 2);

    worldx = generator.worldx + offset;
    worldy = generator.worldy + offset;

  }

  public void update() {
    life--;

    if (life < maxLife / 2) {
      yd++;
    }

    worldx += xd * speed;
    worldy += yd * speed;

    if (life == 0) {
      alive = false;
    }
  }

  public void draw(Graphics2D g2) {
    int screenx = worldx - gp.player.worldx + gp.player.screenx;
    int screeny = worldy - gp.player.worldy + gp.player.screeny;

    g2.setColor(color);
    g2.fillRect(screenx, screeny, size, size);
  }
}
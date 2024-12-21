 package gameobject;

 import java.awt.Color;

 import entity.GameActor;
 import entity.EntityProjectile;
 import main.GameEngine;

 public class OBJ_Rock extends EntityProjectile {
	 GameEngine gp;

   public OBJ_Rock(GameEngine gp) {
     super(gp);
     this.gp = gp;
     name = "Fireball";

     speed = 4;
     maxLife = 80;
     life = maxLife;
     attack = 2;
     useCost = 1;
     alive = false;
     getImage();
     // TODO Auto-generated constructor stub
   }
   public void getImage() {
     up1 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
     up2 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
     down1 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
     down2 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
     left1 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
     left2 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
     right1 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);
     right2 = setup("/projectile/rock_down_1", gp.tilesize, gp.tilesize);

   }

   public boolean haveResource(GameActor user) {
     boolean haveResource = false;
     if (user.ammo >= useCost) {
       haveResource = true;
     }
     return haveResource;
   }

   public void subtractResource(GameActor user) {
     user.ammo -= useCost;
   }

   public Color getParticleColor() {
     Color color = new Color(40, 50, 0);
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
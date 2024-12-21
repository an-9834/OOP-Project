package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GameEngine;
import main.KeyboardInputManager;
import gameobject.OBJ_Fireball;
import gameobject.OBJ_PotionMana;
import gameobject.OBJ_PotionBlood;
import gameobject.OBJ_Shield_Wood;
import gameobject.OBJ_Sword_Normal;

public class PlayerCharacter extends GameActor {

	KeyboardInputManager keyH;
	public final int screenx; 
	public final int screeny;
	int standCounter = 0;
	public boolean attackCancel = false;
	
	
	public PlayerCharacter(GameEngine gp, KeyboardInputManager keyH ) {
		super(gp);
		this.keyH = keyH;
		
		screenx = gp.screenwidth/2-(gp.tilesize/2);
		screeny = gp.screenheight/2-(gp.tilesize/2);
		
		
		solidarea = new Rectangle();
		solidarea.x = 8;
		solidarea.y = 16;
		
		solidAreaDefaultx =solidarea.x; 
		solidAreaDefaulty = solidarea.y;
		
		solidarea.width = 32;
		solidarea.height = 32;
		
		
//		attackArea.width = 36;
//		attackArea.height = 36;
		
		setdefaultval();
	}
	
	public void setdefaultval() {
//		worldx = gp.tilesize*12;
//		worldy = gp.tilesize*13;
		worldx = gp.tilesize*9;
		worldy = gp.tilesize*12;	
		defaultSpeed = 4;
		speed = defaultSpeed;
		gp.currentMap = 0;
		speed = 4;
		direction= "down";
		
		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		maxMana = 4;
		mana = maxMana;
		strength = 1; 
		dexterity = 1; 
		exp = 0;
		nextLevelExp = 6;
		coin  = 0;
		currentWeapon = new OBJ_Sword_Normal(gp);
		
		currentShield = new OBJ_Shield_Wood(gp);
		projectile = new OBJ_Fireball(gp);
		
		attack = getAttack();
		defense = getDefense(); 
		
		getImage();
		getAttackImage();
		getGuardImage();
		setItems();
	}
	public void setDefaultPositions() {
	    worldx = gp.tilesize * 9;
	    worldy = gp.tilesize * 12;
	    direction = "down";
	}
	public void restoreStatus() {
	    life = maxLife;
	    mana = maxMana;
	    invincible = false;
	    transparent = false;
	    attacking = false;
	    guarding = false;
	    knockBack = false;
	}
	public void setItems() { 
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);						
		inventory.add(new OBJ_PotionBlood(gp));
		inventory.add(new OBJ_PotionMana(gp));


	}
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		motion1_duration = currentWeapon.motion1_duration;
		motion2_duration = currentWeapon.motion2_duration;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
	}
	public int getCurrentWeaponSlot() {
		int currentWeaponSlot = 0;
		for (int i = 0; i < inventory.size(); i ++) {
			if (inventory.get(i) == currentWeapon) {
				currentWeaponSlot = i;
			}
		}
		return currentWeaponSlot;
	}
	public int getCurrentShieldSlot() {
		int currentShieldSlot = 0;
		for (int i = 0; i < inventory.size(); i ++) {
			if (inventory.get(i) == currentShield) {
				currentShieldSlot = i;
			}
		}
		return currentShieldSlot;
	}
	
	public void getImage() {
	    up1 = setup("/player/boy_up_1", gp.tilesize, gp.tilesize);
	    up2 = setup("/player/boy_up_2", gp.tilesize, gp.tilesize);
	    down1 = setup("/player/boy_down_1", gp.tilesize, gp.tilesize);
	    down2 = setup("/player/boy_down_2", gp.tilesize, gp.tilesize);
	    left1 = setup("/player/boy_left_1", gp.tilesize, gp.tilesize);
	    left2 = setup("/player/boy_left_2", gp.tilesize, gp.tilesize);
	    right1 = setup("/player/boy_right_1", gp.tilesize, gp.tilesize);
	    right2 = setup("/player/boy_right_2", gp.tilesize, gp.tilesize);
	}

	public void getAttackImage() {
		if (currentWeapon.type== type_sword||currentWeapon.type==type_axe) {
	    attackUp1 = setup("/player/boy_attack_up_1", gp.tilesize, gp.tilesize*2);
	    attackUp2 = setup("/player/boy_attack_up_2", gp.tilesize, gp.tilesize*2);
	    attackDown1 = setup("/player/boy_attack_down_1", gp.tilesize, gp.tilesize*2);
	    attackDown2 = setup("/player/boy_attack_down_2", gp.tilesize, gp.tilesize*2);
	    attackLeft1 = setup("/player/boy_attack_left_1", gp.tilesize*2, gp.tilesize);
	    attackLeft2 = setup("/player/boy_attack_left_2", gp.tilesize*2, gp.tilesize);
	    attackRight1 = setup("/player/boy_attack_right_1", gp.tilesize*2, gp.tilesize);
	    attackRight2 = setup("/player/boy_attack_right_2", gp.tilesize*2, gp.tilesize);
		}	
	}
	public void getGuardImage() {
		guardUp = setup("/player/boy_guard_up",gp.tilesize,gp.tilesize);
		guardDown = setup("/player/boy_guard_down",gp.tilesize,gp.tilesize);
		guardLeft = setup("/player/boy_guard_left",gp.tilesize,gp.tilesize);
		guardRight = setup("/player/boy_guard_right",gp.tilesize,gp.tilesize);
	}
	// update method get call 60times per sec
	public void update() {
    	if (knockBack == true) {
    		
	        collisionon = false;
	        gp.ccheck.CheckTile(this);
	        gp.ccheck.CheckObject(this, true);
	        gp.ccheck.checkGameActor(this, gp.npc);
	        gp.ccheck.checkGameActor(this, gp.monster);
	        gp.ccheck.checkGameActor(this,gp.iTile);
    		
    		if (collisionon == true) {
    		    knockBackCounter = 0;
    		    knockBack = false;
    		    speed = defaultSpeed;
    		}
    		else if (collisionon == false) {
    		    switch (knockBackDirection) {
    		        case "up": worldy -= speed; break;
    		        case "down": worldy += speed; break;
    		        case "left": worldx -= speed; break;
    		        case "right": worldx += speed; break;
    		    }
    		}

    		knockBackCounter++;
    		if (knockBackCounter == 10) {
    		    knockBackCounter = 0;
    		    knockBack = false;
    		    speed = defaultSpeed;
    		}	
    	}
    	else if (attacking == true) {
			attacking();
		}
		else if(keyH.spacePressed == true) {
			guarding = true;
			guardCounter++;
		}
		else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
	        // Xác định hướng di chuyển dựa vào các phím điều khiển
	        if (keyH.upPressed == true) {
	            direction = "up";
	        } else if (keyH.downPressed == true) {
	            direction = "down";
	        } else if (keyH.leftPressed == true) {
	            direction = "left";
	        } else if (keyH.rightPressed == true) {
	            direction = "right";
	        }

	         //Kiểm tra va chạm với tiles
	        collisionon = false;
	        gp.ccheck.CheckTile(this);

	        // Kiểm tra va chạm với đối tượng và thực hiện nhặt đồ
	        int objectIndex = gp.ccheck.CheckObject(this, true);
	        pickUpObject(objectIndex);
	        
	        //CHECK NPC COLLISION
	        int npcIndex = gp.ccheck.checkGameActor(this, gp.npc);
	        interactNPC(npcIndex);
	        
	        //CHECK MONSTER COLLISION
	        int monsterIndex = gp.ccheck.checkGameActor(this, gp.monster);
	        contactMonster(monsterIndex);
	        
	        //CHECK INTERACTIVE TILE COLLISION
	        int iTileIndex = gp.ccheck.checkGameActor(this, gp.iTile);
	        damageInteractiveTile(iTileIndex);
	        
	        // CHECK EVENT
	        gp.eHandler.checkEvent();
	        

	        // Nếu không có va chạm, người chơi có thể di chuyển
	        if (collisionon == false && keyH.enterPressed == false) {
	            switch (direction) {
	                case "up": worldy -= speed; break;
	                case "down": worldy += speed; break;
	                case "left": worldx -= speed; break;
	                case "right": worldx += speed; break;
	            }
	        }
	        
	        if(keyH.enterPressed == true && attackCancel == false) {
	        	gp.playSE(7);
	        	attacking = true;
	        	spritecounter = 0;
	        }
	        	
	        attackCancel = false;
			gp.keyH.enterPressed = false;
			guarding = false;
			guardCounter = 0;


	        // Điều khiển sprite animation
	        spritecounter++;
	        if (spritecounter > 15) {
	            if (spritenum == 1) {
	                spritenum = 2;
	            } else if (spritenum == 2) {
	                spritenum = 1;
	            }
	            spritecounter = 0;
	        }
	    } else {
	    	standCounter++;
	    	if(standCounter == 20) {
	    		spritenum = 1;
	    		standCounter = 0;
	    	}
	    	guarding = false;
	    	guardCounter = 0;
	    }
		if (gp.keyH.shotPressed== true && projectile.alive==false
				&& shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
			// đặt tọa độ mặc định
			projectile.set(worldx, worldy, direction, true,this);
			
			//SUBTRACT THE COST
			projectile.subtractResource(this);

//			CHECK VACANCY
			for (int i = 0; i < gp.projectile[1].length; i++) {
			    if (gp.projectile[gp.currentMap][i] == null) {
			        gp.projectile[gp.currentMap][i] = projectile;
			        break;
			    }
			}

			
			shotAvailableCounter = 0;
			gp.playSE(10);
			
		}
		
	    //This needs to be outside of key if atatement!
	    if (invincible == true) {
	    	invincibleCounter++;
	    	if(invincibleCounter > 60) {
	    		invincible = false;
	    		transparent = false;
	    		invincibleCounter = 0;
	    	}
	    }
	    
	    if (shotAvailableCounter < 30) {
	    	shotAvailableCounter++;
	    }
	    
	    if(life > maxLife) {
			life = maxLife;
		}	
	    
	    if(mana > maxMana) {
			mana = maxMana;
		}
	    if (life <= 0) {
	    	  gp.gameState = gp.gameOverState;
	    	  gp.ui.commandNum = -1;
	    	  gp.stopMusic();
	    	  gp.playSE(12);
	    }
	}


	public void pickUpObject(int i) {
	    if (i != 999) {
	    	String objectName = gp.obj[gp.currentMap][i].name;

	    	switch (objectName) {
	    	case "Nothing":
	    	    gp.ui.gameFinished = true;
	    	    gp.stopMusic();
	    	    gp.playSE(4);
	    	    break;

	    	}

	    	//	PICKUP ONLY ITEMS
	    	if(gp.obj[gp.currentMap][i].type == type_pickupOnly) {
	    		gp.obj[gp.currentMap][i].use(this);
	    		gp.obj[gp.currentMap][i] = null;
	    	}
	    	//OBSTACLE
	    	else if (gp.obj[gp.currentMap][i].type == type_obstacle) {
	    	    if (keyH.enterPressed == true) {
	    	    	attackCancel = true;
	    	        gp.obj[gp.currentMap][i].interact();
	    	    }
	    	}

	    	//INVENTORY ITEMS
	    	else {
	    		String text;
		        if (canObtainItem(gp.obj[gp.currentMap][i]) == true) {
		        	gp.playSE(1);
		        	text = "!";
		        	
		        }
		        else {
		        	text = "Cant carry more!";
		        }
		        gp.ui.showMessage(text);
		        gp.obj[gp.currentMap][i]= null;
	    	}
	    	
	    }
	}

	public void interactNPC(int i) {
		if (gp.keyH.enterPressed == true) {
			if(i != 999) {
				attackCancel = true;
				gp.gameState = gp.dialogueState;
				gp.npc[gp.currentMap][i].speak();
			}
			
		}
	}

	public void contactMonster(int i ) {
		if ( i != 999) {
			if (invincible == false && gp.monster[gp.currentMap][i].dying == false) {
				gp.playSE(6);
				
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if(damage < 1) {
					damage = 1;
				}
				
				life -= damage;
				invincible = true;
				transparent = true;
			}			
		}
	}
	
	public void damageMonster(int i,GameActor attacker, int attack, int knockBackPower) {
		if(i != 999) {
			if (gp.monster[gp.currentMap][i].invincible == false) {
				gp.playSE(5);
				
				if (knockBackPower > 0) {
				    setKnockBack(gp.monster[gp.currentMap][i],attacker, knockBackPower);
				}
				if (gp.monster[gp.currentMap][i].offBalance == true) {
					attack *= 5;
				}
								
				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if(damage < 0) {
					damage = 0;
				}
				
				gp.monster[gp.currentMap][i].life -= damage;
				gp.ui.showMessage(damage + "damage!");
				
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();
				
				if (gp.monster[gp.currentMap][i].life <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.showMessage("Killed the " + gp.monster[gp.currentMap][i].name + "!");
					gp.ui.showMessage("Exp " + gp.monster[gp.currentMap][i].exp);
					exp += gp.monster[gp.currentMap][i].exp;
					checkLevelUp();
				}
			}
		}
	}

	public void damageInteractiveTile(int i) {
		if(i != 999 && gp.iTile[gp.currentMap][i].destructible == true && gp.iTile[gp.currentMap][i].invincible == false) {
			gp.iTile[gp.currentMap][i].playSE();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			if(gp.iTile[gp.currentMap][i].life == 0) {
				gp.iTile[gp.currentMap][i] = null;
			}
			
		}
		

		
	}
	public void damageProjectile(int i) {
	    if (i != 999) {
	        GameActor projectile = gp.projectile[gp.currentMap][i];
	        projectile.alive = false;
	        generateParticle(projectile, projectile);
	    }
	}

	public void checkLevelUp() {
		if(exp >= nextLevelExp) {
			level++;
			nextLevelExp = nextLevelExp * 2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level" + level + " now!\nYou feel stronger!";
		}
	}
	
	public void selectItem() {
		int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol,gp.ui.playerSlotRow);
		if (itemIndex < inventory.size()) {
			GameActor selectedItem = inventory.get(itemIndex);
			if (selectedItem.type== type_sword || selectedItem.type == type_axe) {
				currentWeapon= selectedItem;
				attack = getAttack();
				getAttackImage();
				
			}
			if (selectedItem.type== type_shield) {
				currentShield = selectedItem;
				defense = getDefense();
				
			}
			if (selectedItem.type == type_consumable) {
				if (selectedItem.use(this) == true) {
					if (selectedItem.use(this) == true) {
					    if (selectedItem.amount > 1) {
					        selectedItem.amount--;
					    } else {
					        inventory.remove(itemIndex);
					    }
					}

				}												
			}
		}
	}
	public int searchItemInInventory(String itemName) {
	    int itemIndex = 999;

	    for (int i = 0; i < inventory.size(); i++) {
	        if (inventory.get(i).name.equals(itemName)) {
	            itemIndex = i;
	            break;
	        }
	    }
	    return itemIndex;
	}
	public boolean canObtainItem(GameActor item) {
	    boolean canObtain = false;

	    // CHECK IF STACKABLE
	    if (item.stackable == true) {
	        int index = searchItemInInventory(item.name);

	        if (index != 999) {
	            inventory.get(index).amount++;
	            canObtain = true;
	        } else {
	            // New item so need to check vacancy
	            if (inventory.size() != maxinventorySize) {
	                inventory.add(item);
	                canObtain = true;
	            }
	        }
	    } else { 
	        // NOT STACKABLE so check vacancy
	        if (inventory.size() != maxinventorySize) {
	            inventory.add(item);
	            canObtain = true;
	        }
	    }

	    return canObtain;
	}


	public void draw(Graphics2D g2) {
		g2.setColor(new Color(0,0,0,0));
		g2.fillRect(x, y, gp.tilesize, gp.tilesize);
		BufferedImage image = null;
		int tempScreenX = screenx;
		int tempScreenY = screeny;
		
		switch(direction) {
	    case "up":
	        if (attacking == false) {
	            if (spritenum == 1) { image = up1; }
	            if (spritenum == 2) { image = up2; }
	        }
	        if (attacking == true) {
	        	tempScreenY = screeny - gp.tilesize;
	            if (spritenum == 1) { image = attackUp1; }
	            if (spritenum == 2) { image = attackUp2; }
	        }
	        if(guarding == true) {
	        	image = guardUp;
	        }
	        break;
	    case "down":
	        if (attacking == false) {
	            if (spritenum == 1) { image = down1; }
	            if (spritenum == 2) { image = down2; }
	        }
	        if (attacking == true) {
	            if (spritenum == 1) { image = attackDown1; }
	            if (spritenum == 2) { image = attackDown2; }
	        }
	        if(guarding == true) {
	        	image = guardDown;
	        }
	        break;
	    case "left":
	        if (attacking == false) {
	            if (spritenum == 1) { image = left1; }
	            if (spritenum == 2) { image = left2; }
	        }
	        if (attacking == true) {
	        	tempScreenX = screenx - gp.tilesize;
	            if (spritenum == 1) { image = attackLeft1; }
	            if (spritenum == 2) { image = attackLeft2; }
	        
	        }
	        if(guarding == true) {
	        	image = guardLeft;
	        }
	        break;
	    case "right":
	        if (attacking == false) {
	            if (spritenum == 1) { image = right1; }
	            if (spritenum == 2) { image = right2; }
	        }
	        if (attacking == true) {
	            if (spritenum == 1) { image = attackRight1; }
	            if (spritenum == 2) { image = attackRight2; }
	        
	        }
	        if(guarding == true) {
	        	image = guardRight;
	        }
	        break;
	}

		if (transparent == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}
		
		
		g2.drawImage(image, tempScreenX, tempScreenY,null);
		//RESET ALPHA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		//DEBUG
//		g2.setFont(new Font("Arial", Font.PLAIN,26));
//		g2.setColor(Color.white);
//		g2.drawString("Invincible:"+invincibleCounter, 10, 400);
	}

}
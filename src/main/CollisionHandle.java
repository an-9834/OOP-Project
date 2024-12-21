package main;

import entity.GameActor;

public class CollisionHandle {
  GameEngine gp;

  public CollisionHandle(GameEngine gp) {
    this.gp = gp;
  }

  // Method để check va chạm của GameActor với tiles
  public void CheckTile(GameActor GameActor) {
    int GameActorleftworldx = GameActor.worldx + GameActor.solidarea.x;
    int GameActorrightwordx = GameActor.worldx + GameActor.solidarea.x + GameActor.solidarea.width;
    int GameActortopworldy = GameActor.worldy + GameActor.solidarea.y;
    int GameActorbotworldy = GameActor.worldy + GameActor.solidarea.y + GameActor.solidarea.height;

    int GameActorleftcol = GameActorleftworldx / gp.tilesize;
    int GameActorrightcol = GameActorrightwordx / gp.tilesize;
    int GameActortoprow = GameActortopworldy / gp.tilesize;
    int GameActorbotrow = GameActorbotworldy / gp.tilesize;

    int tilenum1, tilenum2;

    String direction = GameActor.direction;
    if (GameActor.knockBack == true) {
      direction = GameActor.knockBackDirection;
    }

    switch (direction) {
    case "up":
      GameActortoprow = (GameActortopworldy - GameActor.speed) / gp.tilesize;
      tilenum1 = gp.tileM.maptilenum[gp.currentMap][GameActorleftcol][GameActortoprow];
      tilenum2 = gp.tileM.maptilenum[gp.currentMap][GameActorrightcol][GameActortoprow];
      if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
        GameActor.collisionon = true;
      }
      break;
    case "down":
      GameActorbotrow = (GameActorbotworldy + GameActor.speed) / gp.tilesize;
      tilenum1 = gp.tileM.maptilenum[gp.currentMap][GameActorleftcol][GameActorbotrow];
      tilenum2 = gp.tileM.maptilenum[gp.currentMap][GameActorrightcol][GameActorbotrow];
      if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
        GameActor.collisionon = true;
      }
      break;
    case "left":
      GameActorleftcol = (GameActorleftworldx - GameActor.speed) / gp.tilesize;
      tilenum1 = gp.tileM.maptilenum[gp.currentMap][GameActorleftcol][GameActortoprow];
      tilenum2 = gp.tileM.maptilenum[gp.currentMap][GameActorleftcol][GameActorbotrow];
      if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
        GameActor.collisionon = true;
      }
      break;
    case "right":
      GameActorrightcol = (GameActorrightwordx + GameActor.speed) / gp.tilesize;
      tilenum1 = gp.tileM.maptilenum[gp.currentMap][GameActorrightcol][GameActortoprow];
      tilenum2 = gp.tileM.maptilenum[gp.currentMap][GameActorrightcol][GameActorbotrow];
      if (gp.tileM.tile[tilenum1].collision == true || gp.tileM.tile[tilenum2].collision == true) {
        GameActor.collisionon = true;
      }
      break;
    }
  }

  // Method để check va chạm của GameActor với objects
  public int CheckObject(GameActor GameActor, boolean player) {
    int index = 999;
    for (int i = 0; i < gp.obj[1].length; i++) {
      if (gp.obj[gp.currentMap][i] != null) {
        //Get GameActor's soild area position
        GameActor.solidarea.x = GameActor.worldx + GameActor.solidarea.x;
        GameActor.solidarea.y = GameActor.worldy + GameActor.solidarea.y;
        //Get the object'ssolid area position
        gp.obj[gp.currentMap][i].solidarea.x = gp.obj[gp.currentMap][i].worldx + gp.obj[gp.currentMap][i].solidarea.x;
        gp.obj[gp.currentMap][i].solidarea.y = gp.obj[gp.currentMap][i].worldy + gp.obj[gp.currentMap][i].solidarea.y;

        String direction = GameActor.direction;
        if (GameActor.knockBack == true) {
          direction = GameActor.knockBackDirection;
        }
        switch (direction) {
        case "up":
          GameActor.solidarea.y -= GameActor.speed;
          break;
        case "down":
          GameActor.solidarea.y += GameActor.speed;
          break;
        case "left":
          GameActor.solidarea.x -= GameActor.speed;
          break;
        case "right":
          GameActor.solidarea.x += GameActor.speed;
          break;
        }
        if (GameActor.solidarea.intersects(gp.obj[gp.currentMap][i].solidarea)) {
          if (gp.obj[gp.currentMap][i].collision == true) {
            GameActor.collisionon = true;
          }
          if (player == true) {
            index = i;
          }
        }
        // Đặt lại tọa độ gốc của solidArea
        GameActor.solidarea.x = GameActor.solidAreaDefaultx;
        GameActor.solidarea.y = GameActor.solidAreaDefaulty;
        gp.obj[gp.currentMap][i].solidarea.x = gp.obj[gp.currentMap][i].solidAreaDefaultx;
        gp.obj[gp.currentMap][i].solidarea.y = gp.obj[gp.currentMap][i].solidAreaDefaulty;
      }
    }
    return index;
  }

  //NPC OR MONSTER
  public int checkGameActor(GameActor GameActor, GameActor[][] target) {
    int index = 999;
    String direction = GameActor.direction;
    if (GameActor.knockBack == true) {
      direction = GameActor.knockBackDirection;
    }
    for (int i = 0; i < target[1].length; i++) {
      if (target[gp.currentMap][i] != null) {
        //Get GameActor's soild area position
        GameActor.solidarea.x = GameActor.worldx + GameActor.solidarea.x;
        GameActor.solidarea.y = GameActor.worldy + GameActor.solidarea.y;
        //Get the object'ssolid area position
        target[gp.currentMap][i].solidarea.x = target[gp.currentMap][i].worldx + target[gp.currentMap][i].solidarea.x;
        target[gp.currentMap][i].solidarea.y = target[gp.currentMap][i].worldy + target[gp.currentMap][i].solidarea.y;

        switch (direction) {
        case "up":
          GameActor.solidarea.y -= GameActor.speed;
          break;
        case "down":
          GameActor.solidarea.y += GameActor.speed;
          break;
        case "left":
          GameActor.solidarea.x -= GameActor.speed;
          break;
        case "right":
          GameActor.solidarea.x += GameActor.speed;
          break;
        }
        if (GameActor.solidarea.intersects(target[gp.currentMap][i].solidarea)) {
          if (target[gp.currentMap][i] != GameActor) {
            GameActor.collisionon = true;
            index = i;
          }
        }
        // Đặt lại tọa độ gốc của solidArea
        GameActor.solidarea.x = GameActor.solidAreaDefaultx;
        GameActor.solidarea.y = GameActor.solidAreaDefaulty;
        target[gp.currentMap][i].solidarea.x = target[gp.currentMap][i].solidAreaDefaultx;
        target[gp.currentMap][i].solidarea.y = target[gp.currentMap][i].solidAreaDefaulty;
      }
    }
    return index;
  }
  public boolean checkPlayer(GameActor GameActor) {

    boolean contactPlayer = false;

    //Get GameActor's soild area position
    GameActor.solidarea.x = GameActor.worldx + GameActor.solidarea.x;
    GameActor.solidarea.y = GameActor.worldy + GameActor.solidarea.y;
    //Get the object'ssolid area position
    gp.player.solidarea.x = gp.player.worldx + gp.player.solidarea.x;
    gp.player.solidarea.y = gp.player.worldy + gp.player.solidarea.y;

    switch (GameActor.direction) {
    case "up":
      GameActor.solidarea.y -= GameActor.speed;
      break;
    case "down":
      GameActor.solidarea.y += GameActor.speed;
      break;
    case "left":
      GameActor.solidarea.x -= GameActor.speed;
      break;
    case "right":
      GameActor.solidarea.x += GameActor.speed;
      break;
    }
    if (GameActor.solidarea.intersects(gp.player.solidarea)) {
      GameActor.collisionon = true;
      contactPlayer = true;
    }

    // Đặt lại tọa độ gốc của solidArea
    GameActor.solidarea.x = GameActor.solidAreaDefaultx;
    GameActor.solidarea.y = GameActor.solidAreaDefaulty;
    gp.player.solidarea.x = gp.player.solidAreaDefaultx;
    gp.player.solidarea.y = gp.player.solidAreaDefaulty;

    return contactPlayer;
  }

}
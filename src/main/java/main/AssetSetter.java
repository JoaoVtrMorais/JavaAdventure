package main;

import enemy.ENEMY_Slime;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject() {
    }

    public void setNPC() {}

    public void setEnemy() {
        gp.enemy[0] = new ENEMY_Slime(gp);
        gp.enemy[0].worldX = gp.tileSize * 15;
        gp.enemy[0].worldY = gp.tileSize * 10;

        gp.enemy[1] = new ENEMY_Slime(gp);
        gp.enemy[1].worldX = gp.tileSize * 16;
        gp.enemy[1].worldY = gp.tileSize * 11;

        gp.enemy[2] = new ENEMY_Slime(gp);
        gp.enemy[2].worldX = gp.tileSize * 12;
        gp.enemy[2].worldY = gp.tileSize * 10;

        gp.enemy[3] = new ENEMY_Slime(gp);
        gp.enemy[3].worldX = gp.tileSize * 14;
        gp.enemy[3].worldY = gp.tileSize * 15;

        gp.enemy[4] = new ENEMY_Slime(gp);
        gp.enemy[4].worldX = gp.tileSize * 17;
        gp.enemy[4].worldY = gp.tileSize * 11;

    }
}

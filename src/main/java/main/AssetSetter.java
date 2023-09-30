package main;

import object.Coin;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject() {

        gp.obj[0] = new Coin(gp);
        gp.obj[0].worldX = 2 * gp.tileSize;
        gp.obj[0].worldY = 3 * gp.tileSize;

        gp.obj[1] = new Coin(gp);
        gp.obj[1].worldX = 4 * gp.tileSize;
        gp.obj[1].worldY = 3 * gp.tileSize;

        gp.obj[2] = new Coin(gp);
        gp.obj[2].worldX = 5 * gp.tileSize;
        gp.obj[2].worldY = 3 * gp.tileSize;

        gp.obj[3] = new Coin(gp);
        gp.obj[3].worldX = 7 * gp.tileSize;
        gp.obj[3].worldY = 3 * gp.tileSize;


    }
}

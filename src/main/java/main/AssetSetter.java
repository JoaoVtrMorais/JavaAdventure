package main;

import entity.NPC_1;
import object.Coin;
import object.OBJ_CoffeeBasic;

public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    
    public void setObject() {
    }

    public void setNPC() {

        gp.npc[0] = new NPC_1(gp);
        gp.npc[0].worldX = gp.tileSize * 10;
        gp.npc[0].worldY = gp.tileSize * 7;

        gp.npc[1] = new NPC_1(gp);
        gp.npc[1].worldX = gp.tileSize * 12;
        gp.npc[1].worldY = gp.tileSize * 9;
    }
}

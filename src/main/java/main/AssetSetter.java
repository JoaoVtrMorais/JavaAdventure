package main;

import entity.NPC_1;
import monster.MON_skull;
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
    }

    public void setMonster() {
        gp.monster[0] = new MON_skull(gp);
        gp.monster[0].worldX = gp.tileSize * 15;
        gp.monster[0].worldY = gp.tileSize * 10;
    }
}

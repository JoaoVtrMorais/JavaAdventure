package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player (GamePanel gp, KeyHandler keyH) {

        super(gp);

        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 5;
        worldY = gp.tileSize * 5;
        speed = 4;
        direction = "down";

        //  PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {

        up1 = setup("/player/CharacterUp_1");
        up2 = setup("/player/CharacterUp_2");
        down1 = setup("/player/CharacterDown_1");
        down2 = setup("/player/CharacterDown_2");
        left1 = setup("/player/CharacterLeft_1");
        left2 = setup("/player/CharacterLeft_2");
        right1 = setup("/player/CharacterRight_1");
        right2 = setup("/player/CharacterRight_2");

    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed) {

            if (keyH.upPressed) {
                direction = "up";
            } else if (keyH.downPressed) {
                direction = "down";
            } else if (keyH.leftPressed) {
                direction = "left";
            } else if (keyH.rightPressed) {
                direction = "right";

            }

            // VERIFICA COLISÃO
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // VERIFICA COLISÃO COM OBJETO
            int objIndex = gp.cChecker.checkObject(this, true) ;
            pickUpObject(objIndex);

            // VERIFICA COLISÃO COM NPC
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // VERIFICA EVENTO
            gp.eHandler.checkEvent();

            gp.keyH.enterPressed = false;

            // SE COLISÃO É FALSA, O JOGADOR PODE SE MOVER
            if (collisionOn == false) {

                switch (direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    
    public void pickUpObject(int i) {
        if(i != 999) {

        }
    }

    public void interactNPC(int i) {
        if(i != 999) {

            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void draw(Graphics2D g2) {

    // g2.setColor(Color.white);
    // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, null);

    }
}

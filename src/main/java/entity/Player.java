package entity;

import main.GamePanel;
import main.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {

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

        attackArea.width = 50;
        attackArea.height = 50;
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 15;
        worldY = gp.tileSize * 13;
        speed = 4;
        direction = "down";

        //  PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }

    public void setDefaultPositions() {
        worldX = gp.tileSize * 15;
        worldY = gp.tileSize * 13;
        direction = "down";
    }

    public void restoreLife() {

        life = maxLife;
        invincible = false;
    }

    public void getPlayerImage() {
        img = importImg("/player/Character_Run.png");
        img2 = importImg2("/player/Character_Run2.png");

        down1 = img.getSubimage(0, 0, 16, 16);
        down2 = img.getSubimage(16, 0, 16, 16);
        down3 = img.getSubimage(32, 0, 16, 16);
        down4 = img.getSubimage(48, 0, 16, 16);
        down5 = img.getSubimage(64, 0, 16, 16);
        down6 = img.getSubimage(80, 0, 16, 16);
        right1 = img.getSubimage(0, 16, 16, 16);
        right2 = img.getSubimage(16, 16, 16, 16);
        right3 = img.getSubimage(32, 16, 16, 16);
        right4 = img.getSubimage(48, 16, 16, 16);
        right5 = img.getSubimage(64, 16, 16, 16);
        right6 = img.getSubimage(80, 16, 16, 16);
        left1 = img2.getSubimage(0, 16, 16, 16);
        left2 = img2.getSubimage(16, 16, 16, 16);
        left3 = img2.getSubimage(32, 16, 16, 16);
        left4 = img2.getSubimage(48, 16, 16, 16);
        left5 = img2.getSubimage(64, 16, 16, 16);
        left6 = img2.getSubimage(80, 16, 16, 16);
        up1 = img.getSubimage(0, 32, 16, 16);
        up2 = img.getSubimage(16, 32, 16, 16);
        up3 = img.getSubimage(32, 32, 16, 16);
        up4 = img.getSubimage(48, 32, 16, 16);
        up5 = img.getSubimage(64, 32, 16, 16);
        up6 = img.getSubimage(80, 32, 16, 16);

    }

    public void getPlayerAttackImage() {

        img = importImg("/player/Character_AttackSword1.png");

        img2 = importImg2("/player/Character_AttackSword2.png");

        attackDown1 = img.getSubimage(0, 0, 48, 48);
        attackDown2 = img.getSubimage(48, 0, 48, 48);
        attackDown3 = img.getSubimage(96, 0, 48, 48);
        attackDown4 = img.getSubimage(144, 0, 48, 48);
        attackRight1 = img.getSubimage(0, 48, 48, 48);
        attackRight2 = img.getSubimage(48, 48, 48, 48);
        attackRight3 = img.getSubimage(96, 48, 48, 48);
        attackRight4 = img.getSubimage(144, 48, 48, 48);
        attackUp1 = img.getSubimage(0, 96, 48, 48);
        attackUp2 = img.getSubimage(48, 96, 48, 48);
        attackUp3 = img.getSubimage(96, 96, 48, 48);
        attackUp4 = img.getSubimage(144, 96, 48, 48);
        attackLeft1 = img2.getSubimage(0, 48, 48, 48);
        attackLeft2 = img2.getSubimage(48, 48, 48, 48);
        attackLeft3 = img2.getSubimage(96, 48, 48, 48);
        attackLeft4 = img2.getSubimage(144, 48, 48, 48);
    }

    public void update() {

        if (attacking == true) {
            attacking();
        } else if (keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed || keyH.enterPressed) {

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
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // VERIFICA COLISÃO COM NPC
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // VERIFICA COLISÃO COM MONSTRO
            int monsterIndex = gp.cChecker.checkEntity(this, gp.enemy);
            contactEnemy(monsterIndex);

            // VERIFICA EVENTO
            gp.eHandler.checkEvent();

            // SE COLISÃO É FALSA, O JOGADOR PODE SE MOVER
            if (collisionOn == false && keyH.enterPressed == false) {

                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 4;
                } else if (spriteNum == 4) {
                    spriteNum = 5;
                } else if (spriteNum == 5) {
                    spriteNum = 6;
                } else if (spriteNum == 6) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if (life <= 0) {
            gp.gameState = gp.gameOverState;
            //gp.playSE(12);
        }

    }

    public void attacking() {

        spriteCounter++;

        if (spriteCounter <= 2) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 15) {
            spriteNum = 2;
        }
        if (spriteCounter > 15 && spriteCounter <= 25) {
            spriteNum = 3;
        }
        if (spriteCounter > 25 && spriteCounter <= 35) {
            spriteNum = 4;

            // Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Adjust player's worldX/Y for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX -= attackArea.width;
                    break;
            }

            // AttackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;
            // Check monster collision with the updated worldX, worldY and solidArea
            int enemyIndex = gp.cChecker.checkEntity(this, gp.enemy);
            damageEnemy(enemyIndex);

            // After checking collision, resolve the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;

        }
        if (spriteCounter > 35) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

        }
    }

    public void interactNPC(int i) {

        if (gp.keyH.enterPressed == true) {

            if (i != 999) {
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();

            } else {
                //gp.playSE(1);
                attacking = true;
            }
        }
    }

    public void contactEnemy(int i) {
        if (i != 999) {
            if (invincible == false) {
                //gp.playSE(1);
                life -= 1;
                invincible = true;
            }
        }
    }

    public void damageEnemy(int i) {

        if (i != 999) {
            if (!gp.enemy[i].invincible) {

                //gp.playSE(1);
                gp.enemy[i].life -= 1;
                gp.enemy[i].invincible = true;
                gp.enemy[i].damageReaction();

                if (gp.enemy[i].life <= 0) gp.enemy[i].dying = true;
            }
        }
    }
    public void draw (Graphics2D g2) {

            BufferedImage image = null;
            int tempScreenX = screenX;
            int tempScreenY = screenY;

            switch (direction) {
                case "up":

                    if (!attacking) {
                        if (spriteNum == 1) image = up1;
                        if (spriteNum == 2) image = up2;
                        if (spriteNum == 3) image = up3;
                        if (spriteNum == 4) image = up4;
                        if (spriteNum == 5) image = up5;
                        if (spriteNum == 6) image = up6;
                    }

                    if (attacking) {
                        tempScreenX -= gp.tileSize;
                        tempScreenY -= gp.tileSize;
                        if (spriteNum == 1) image = attackUp1;
                        if (spriteNum == 2) image = attackUp2;
                        if (spriteNum == 3) image = attackUp3;
                        if (spriteNum == 4) image = attackUp4;
                    }

                    break;
                case "down":

                    if (!attacking) {
                        if (spriteNum == 1) image = down1;
                        if (spriteNum == 2) image = down2;
                        if (spriteNum == 3) image = down3;
                        if (spriteNum == 4) image = down4;
                        if (spriteNum == 5) image = down5;
                        if (spriteNum == 6) image = down6;
                    }

                    if (attacking) {
                        tempScreenX -= gp.tileSize;
                        tempScreenY -= gp.tileSize;
                        if (spriteNum == 1) image = attackDown1;
                        if (spriteNum == 2) image = attackDown2;
                        if (spriteNum == 3) image = attackDown3;
                        if (spriteNum == 4) image = attackDown4;
                    }

                    break;

                case "left":

                    if (!attacking) {
                        if (spriteNum == 1) image = left1;
                        if (spriteNum == 2) image = left2;
                        if (spriteNum == 3) image = left3;
                        if (spriteNum == 4) image = left4;
                        if (spriteNum == 5) image = left5;
                        if (spriteNum == 6) image = left6;
                    }

                    if (attacking) {
                        tempScreenX -= gp.tileSize;
                        tempScreenY -= gp.tileSize;
                        if (spriteNum == 1) image = attackLeft1;
                        if (spriteNum == 2) image = attackLeft2;
                        if (spriteNum == 3) image = attackLeft3;
                        if (spriteNum == 4) image = attackLeft4;
                    }

                    break;
                case "right":

                    if (!attacking) {
                        if (spriteNum == 1) image = right1;
                        if (spriteNum == 2) image = right2;
                        if (spriteNum == 3) image = right3;
                        if (spriteNum == 4) image = right4;
                        if (spriteNum == 5) image = right5;
                        if (spriteNum == 6) image = right6;
                    }

                    if (attacking) {
                        tempScreenX -= gp.tileSize;
                        tempScreenY -= gp.tileSize;
                        if (spriteNum == 1) image = attackRight1;
                        if (spriteNum == 2) image = attackRight2;
                        if (spriteNum == 3) image = attackRight3;
                        if (spriteNum == 4) image = attackRight4;
                    }

                    break;
            }

            if (invincible == true) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
            }

            if (!attacking) g2.drawImage(image, tempScreenX, tempScreenY, 48, 48, null);

            if (attacking) g2.drawImage(image, tempScreenX, tempScreenY, 144, 144, null);

            // Reset alpha

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}


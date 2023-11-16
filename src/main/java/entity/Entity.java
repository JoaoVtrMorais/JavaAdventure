package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Entity {

    GamePanel gp;

    public BufferedImage img, img2, up1, up2, up3, up4, up5, up6,
                         down1, down2, down3, down4, down5, down6,
                         left1, left2, left3, left4, left5, left6,
                         right1, right2, right3, right4, right5, right6;
    public BufferedImage attackUp1, attackUp2, attackUp3, attackUp4,
                         attackDown1, attackDown2, attackDown3, attackDown4,
                         attackLeft1, attackLeft2, attackLeft3, attackLeft4,
                         attackRight1, attackRight2, attackRight3, attackRight4;
    public BufferedImage image, image2, image3, image4;
    public Rectangle solidArea = new Rectangle(0, 0, 48,48);
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    String dialogues[] = new String[20];

    // STATE
    public int worldX, worldY;
    public String direction = "down";
    public int spriteNum = 1;
    int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false;
    boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean hpBarOn = false;

    // COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    // CHARACTER ATTRIBUTES
    public int type; // 0 = player, 1 = npc, 2 = enemy
    public String name;
    public int speed;
    public int maxLife;
    public int life;

    public Entity (GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {}

    public void damageReaction() {}

    public void speak() {

        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.message = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.enemy);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.player.invincible == false) {
                //gp.playSE(1);
                gp.player.life -= 1;
                gp.player.invincible = true;
            }
        }

        // SE COLISÃO É FALSA, A ENTIDADE PODE SE MOVER
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

        if (invincible == true) {
            invincibleCounter++;
            if (invincibleCounter > 40) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (direction) {
                case "up":
                    if (spriteNum == 1) image = up1;
                    if (spriteNum == 2) image = up2;
                    if (spriteNum == 3) image = up3;
                    if (spriteNum == 4) image = up4;
                    if (spriteNum == 5) image = up5;
                    if (spriteNum == 6) image = up6;
                    break;
                case "down":
                    if (spriteNum == 1) image = down1;
                    if (spriteNum == 2) image = down2;
                    if (spriteNum == 3) image = down3;
                    if (spriteNum == 4) image = down4;
                    if (spriteNum == 5) image = down5;
                    if (spriteNum == 6) image = down6;
                    break;
                case "left":
                    if (spriteNum == 1) image = left1;
                    if (spriteNum == 2) image = left2;
                    if (spriteNum == 3) image = left3;
                    if (spriteNum == 4) image = left4;
                    if (spriteNum == 5) image = left5;
                    if (spriteNum == 6) image = left6;
                    break;
                case "right":
                    if (spriteNum == 1) image = right1;
                    if (spriteNum == 2) image = right2;
                    if (spriteNum == 3) image = right3;
                    if (spriteNum == 4) image = right4;
                    if (spriteNum == 5) image = right5;
                    if (spriteNum == 6) image = right6;
                    break;
            }

            // Enemy HP bar
            if (type == 2 && hpBarOn == true) {

                double oneScale = (double)gp.tileSize / maxLife;
                double hpBarValue = oneScale * life;

                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                hpBarCounter++;

                if (hpBarCounter > 600) {
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }

            if (invincible == true) {
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2,0.4F);
            }

            if (dying == true) {
                dyingAnimation(g2);
            }

            g2.drawImage(image, screenX, screenY, 48, 48, null);

            changeAlpha(g2,1F);
        }
    }

    public void dyingAnimation(Graphics2D g2) {

        dyingCounter++;

        int i = 5;

        if (dyingCounter <= i) changeAlpha(g2, 0f);
        if (dyingCounter > i && dyingCounter <= i * 2) changeAlpha(g2, 1f);
        if (dyingCounter > i * 2 && dyingCounter <= i * 3) changeAlpha(g2, 0f);
        if (dyingCounter > i * 3 && dyingCounter <= i * 4) changeAlpha(g2, 1f);
        if (dyingCounter > i * 4 && dyingCounter <= i * 5) changeAlpha(g2, 0f);
        if (dyingCounter > i * 5 && dyingCounter <= i * 6) changeAlpha(g2, 1f);
        if (dyingCounter > i * 6 && dyingCounter <= i * 7) changeAlpha(g2, 1f);
        if (dyingCounter > i * 8 && dyingCounter <= i * 8) changeAlpha(g2, 1f);
        if (dyingCounter > i * 8) {
            dying = false;
            alive = false;
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue) {

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }

    public BufferedImage setup(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public BufferedImage importImg(String path) {
        InputStream is = getClass().getResourceAsStream(path);

        try {
            assert is != null;
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public BufferedImage importImg2(String path) {
        InputStream is = getClass().getResourceAsStream(path);

        try {
            assert is != null;
            img2 = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img2;
    }
}

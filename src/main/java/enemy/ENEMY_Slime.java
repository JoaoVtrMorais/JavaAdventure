package enemy;

import entity.Entity;
import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class ENEMY_Slime extends Entity {

    GamePanel gp;

    public ENEMY_Slime(GamePanel gp) {
        super(gp);

        this.gp = gp;

        type = 2;
        name = "Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage() {

        importImg("/enemies/Enemy_Slime/Enemy_Slime_Walk.png");
        importImg2("/enemies/Enemy_Slime/Enemy_Slime_Walk2.png");

        up1 = img.getSubimage(0, 0, 16, 16);
        up2 = img.getSubimage(16, 0, 16, 16);
        up3 = img.getSubimage(32, 0, 16, 16);
        up4 = img.getSubimage(48, 0, 16, 16);
        up5 = img.getSubimage(64, 0, 16, 16);
        up6 = img.getSubimage(80, 0, 16, 16);
        down1 = img2.getSubimage(0, 0, 16, 16);
        down2 = img2.getSubimage(16, 0, 16, 16);
        down3 = img2.getSubimage(32, 0, 16, 16);
        down4 = img2.getSubimage(48, 0, 16, 16);
        down5 = img2.getSubimage(64, 0, 16, 16);
        down6 = img2.getSubimage(80, 0, 16, 16);
        left1 = img.getSubimage(0, 0, 16, 16);
        left2 = img.getSubimage(16, 0, 16, 16);
        left3 = img.getSubimage(32, 0, 16, 16);
        left4 = img.getSubimage(48, 0, 16, 16);
        left5 = img.getSubimage(64, 0, 16, 16);
        left6 = img.getSubimage(80, 0, 16, 16);
        right1 = img2.getSubimage(0, 0, 16, 16);
        right2 = img2.getSubimage(16, 0, 16, 16);
        right3 = img2.getSubimage(32, 0, 16, 16);
        right4 = img2.getSubimage(48, 0, 16, 16);
        right5 = img2.getSubimage(64, 0, 16, 16);
        right6 = img2.getSubimage(80, 0, 16, 16);
    }

    @Override
    public void setAction () {

        actionLockCounter ++;

        if (actionLockCounter == 120) {
            var random = new Random();
            int i = random.nextInt(100)+1; // Escolhe um número de 1 a 100

            if (i <= 25) direction = "up";
            if (i > 25 && i <= 50) direction = "down";
            if (i > 50 && i <= 75) direction = "left";
            if (i > 75 && i <= 100) direction = "right";

            actionLockCounter = 0;
        }

    }

    @Override
    public void damageReaction() {

        actionLockCounter = 0;
        direction = gp.player.direction;
    }
}

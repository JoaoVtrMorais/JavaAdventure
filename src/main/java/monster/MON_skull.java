package monster;

import entity.Entity;
import main.GamePanel;

import java.util.Random;

public class MON_skull extends Entity {
    public MON_skull(GamePanel gp) {
        super(gp);

        type = 2;
        name = "Skull";
        speed = 2;
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

        up1 = setup("");
        up2 = setup("");
        down1 = setup("");
        down2 = setup("");
        left1 = setup("");
        left2 = setup("");
        right1 = setup("");
        right2 = setup("");
    }

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
}

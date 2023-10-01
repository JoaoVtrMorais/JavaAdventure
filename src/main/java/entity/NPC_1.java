package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_1 extends Entity {

    public NPC_1(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
    }

    public void getImage() {

        up1 = setup("/player/CharacterUp_1");
        up2 = setup("/player/CharacterUp_2");
        down1 = setup("/player/CharacterDown_1");
        down2 = setup("/player/CharacterDown_2");
        left1 = setup("/player/CharacterLeft_1");
        left2 = setup("/player/CharacterLeft_2");
        right1 = setup("/player/CharacterRight_1");
        right2 = setup("/player/CharacterRight_2");
    }

    public void setAction() {

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

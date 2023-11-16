package entity;

import main.GamePanel;

import java.awt.image.BufferedImage;
import java.util.Random;

public class NPC_1 extends Entity {

    public NPC_1(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void getImage() {
        img = importImg("/npc/NPC_test.png");

        up1 = img.getSubimage(0, 64, 16, 32);
        up2 = img.getSubimage(16, 64, 16, 32);
        up3 = img.getSubimage(32, 64, 16, 32);
        up4 = img.getSubimage(48, 64, 16, 32);
        down1 = img.getSubimage(0, 0, 16, 32);
        down2 = img.getSubimage(16, 0, 16, 32);
        down3 = img.getSubimage(32, 0, 16, 32);
        down4 = img.getSubimage(48, 0, 16, 32);
        left1 = img.getSubimage(0, 96, 16, 32);
        left2 = img.getSubimage(16, 96, 16, 32);
        left3 = img.getSubimage(32, 96, 16, 32);
        left4 = img.getSubimage(48, 96, 16, 32);
        right1 = img.getSubimage(0, 32, 16, 32);
        right2 = img.getSubimage(16, 32, 16, 32);
        right3 = img.getSubimage(32, 32, 16, 32);
        right4 = img.getSubimage(48, 32, 16, 32);
    }

    public void setDialogue() {
        dialogues[0] = "Olá, mundo!";
        dialogues[1] = "Olá, humano.\nÉ um prazer conhece-lo!";
        dialogues[2] = "Saiba que não há cordões em mim.";
        dialogues[3] = "Eu fugiria se fosse você.";
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

    public void speak() {

        super.speak();

    }
}

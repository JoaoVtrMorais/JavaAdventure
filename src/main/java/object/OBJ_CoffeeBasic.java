package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CoffeeBasic extends Entity {

    public OBJ_CoffeeBasic(GamePanel gp) {

        super(gp);

        name = "Coffee";
        down1 = setup("/objects/Coffee1");
        collision = true;

        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }
}

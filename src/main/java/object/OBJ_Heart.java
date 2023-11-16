package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {

    public OBJ_Heart(GamePanel gp) {
        super(gp);

        name = "Heart";
        image = importImg("/teste/objects.png");
        
        image2 = img.getSubimage(64, 0, 16, 16);
        image3 = img.getSubimage(96, 0, 16, 16);
        image4 = img.getSubimage(128, 0, 16, 16);
    }

}

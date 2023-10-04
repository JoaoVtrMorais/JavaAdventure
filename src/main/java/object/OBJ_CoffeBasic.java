package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CoffeBasic extends SuperObject{

    GamePanel gp;

    public OBJ_CoffeBasic(GamePanel gp) {

        this.gp = gp;

        name = "Boots";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("objects/Coffee1.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Portrait extends SuperObject{

    GamePanel gp;

    public OBJ_Portrait(GamePanel gp) {

        this.gp = gp;

        name = "Vitality";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/PlayerPortrait_UI.png"));
            image = uTool.scaleImage(image, gp.originalTileSize * 4, gp.originalTileSize * 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package object;

import entity.Entity;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class OBJ_Portrait extends Entity {

    public OBJ_Portrait(GamePanel gp) {
        super(gp);
        
        UtilityTool uTool = new UtilityTool();
        
        //name = "Portrait";
        //image = setup("/objects/PlayerPortrait_UI");
        //image = uTool.scaleImage(image, gp.tileSize * 2, gp.tileSize * 2);
    }
}

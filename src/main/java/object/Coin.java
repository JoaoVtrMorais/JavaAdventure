package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Coin extends SuperObject {

    GamePanel gp;

    public Coin(GamePanel gp) {

        this.gp = gp;

        name = "Moeda";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/Coin_Object.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}

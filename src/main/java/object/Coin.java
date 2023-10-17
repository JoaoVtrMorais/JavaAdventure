package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Coin extends Entity {

    public Coin(GamePanel gp) {
        super(gp);

        name = "Moeda";
        down1 = setup("objects/Coin_Object.png");
    }
}

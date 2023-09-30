package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class obj extends SuperObject {

    public obj() {

        name = "Objeto";

        try {
            image = ImageIO.read(getClass().getResourceAsStream(""));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Portrait extends Entity {

    public OBJ_Portrait(GamePanel gp) {
        super(gp);

        name = "Portrait";
        down1 = setup("/objects/PlayerPortrait_UI");
    }
}

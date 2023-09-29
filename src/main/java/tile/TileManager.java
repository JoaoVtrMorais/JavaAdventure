package tile;

import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import main.GamePanel;
import java.io.IOException;

public class TileManager {
    
    GamePanel gp;
    Tile[] tile;
    
    public TileManager(GamePanel gp) {
        
        this.gp = gp;
        
        tile = new Tile[10];
        
        getTileImage();
    }
    
    public void getTileImage() {
        try {
            
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/WoodenFloor.png"));
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2) {
        
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        
        while(col < gp.maxScreenCol && row < gp.MaxScreenRow) {
            
            g2.drawImage(tile[0].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            
            if (col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            } 
        }
    }
}
package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial;
    
    public UI(GamePanel gp) {
        this.gp = gp;
        
        arial = new Font("Arial", Font.PLAIN, 40);
    }
    
    public void draw(Graphics2D g2) {
        
        this.g2 = g2;
        
        if (gp.gameState == gp.playState) {
            
        }
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }   
    }
    public void drawPauseScreen() {
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        
        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text) {
        
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - lenght;
        return x;
    }
}

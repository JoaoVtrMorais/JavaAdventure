package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.IllegalFormatCodePointException;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80;
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: primeira tela, 1: segunda tela

    public UI(GamePanel gp) {
        this.gp = gp;
        
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80 = new Font("Arial", Font.PLAIN, 80);
    }
    
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(arial_80);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }   
    }

    public void drawTitleScreen() {

        if (titleScreenState == 0) {
            g2.setColor(Color.black);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

            // NOME DO JOGO
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 85));
            String text = "Java Coffee Shop";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;

            // SOMBREAMENTO
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);
            // COR PRINCIPAL
            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // IMAGEM
            x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
            y += gp.tileSize * 2;
            g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3.5;
            g2.drawString(text, x, y);
            if(commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }
        } else if (titleScreenState == 1) {

            // TELA DE CRIAÇÃO DE PERSONAGEM
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 50F));

            String text = "Selecione seu barista!";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Barista Teste 1";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Barista Teste 2";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Barista Teste 3";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawString(">", x - gp.tileSize, y);
            }

            text = "Back";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 3) {
                g2.drawString(">", x - gp.tileSize, y);
            }

        }

    }

    public void drawPauseScreen() {

        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;
        
        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text) {
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}

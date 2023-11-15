package main;

import entity.Entity;
import object.OBJ_Portrait;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage portrait_empty, heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public int commandNum = 0;
    public int titleScreenState = 0; // 0: primeira tela, 1: segunda tela

    public UI(GamePanel gp) {
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CREATE HUD OBJECT
        Entity heart = new OBJ_Heart(gp);
        //Entity portrait = new OBJ_Portrait(gp);
        //portrait_empty = portrait.image;
        heart_full = heart.image2;
        heart_half = heart.image3;
        heart_blank = heart.image4;
    }
    
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerHud();
        }
        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerHud();
            drawPauseScreen();
        }
        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerHud();
            drawDialogueScreen();
        }

    }

    public void drawPlayerHud() {

        int x = 25;
        int y = 25;

        // DRAW PLAYER'S PORTRAIT
        //g2.drawImage(portrait_empty, x, y, null);

        x = 115;
        y = 35;
        int i = 0;

        // DRAW MAX HEART
        while (i < gp.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, 48, 48, null);
            i++;
            x += gp.tileSize;
        }

        // RESET

        x = 115;
        y = 35;
        i = 0;

        // DRAW CURRENT LIFE
        while (i < gp.player.life) {
            g2.drawImage(heart_half, x, y, 48, 48, null);
            i++;
            if(i < gp.player.life) {
                g2.drawImage(heart_full, x, y, 48, 48, null);
            }
            i++;
            x += gp.tileSize;
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
            x = gp.screenWidth / 2 - 39;
            y += gp.tileSize * 2 - 40;
            g2.drawImage(gp.player.down1, x, y, 128, 128, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 3.5 + 40;
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

    public void drawDialogueScreen() {

        // WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28F));
        x += gp.tileSize;
        y += gp.tileSize;

        for (String line : message.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height,35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);

    }

    public int getXforCenteredText(String text) {
        
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}

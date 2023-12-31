package main;

import JavaGUI.progressBarDemo;
import entity.Entity;
import entity.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // CONFIGURAÇÕES DA TELA
    public final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 960 pixels
    public final int screenHeight = tileSize* maxScreenRow; // 576 pixels

    // CONFIGURAÇÕES DO MUNDO
    public final int maxWorldCol = 36;
    public final int maxWorldRow = 24;

    // TELA CHEIA
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenOn = false;

    // FPS
    int FPS = 60;
    
    // SISTEMA
    TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Config config = new Config(this);
    Thread gameThread;
    int kills = 0;
    
    // ESTADO DO JOGO
    public int gameState;
    public final int  titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int optionsState = 5;
    public final int gameOverState = 6;
    public final int thanksState = 7;

    // ENTIDADE E OBJETO
    public Player player = new Player(this, keyH);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity enemy[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();
    
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame() {

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
        //playMusic(0);
        gameState = titleState;

        tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
        g2 = (Graphics2D)tempScreen.getGraphics();

        if (fullScreenOn) {
            setFullScreen();
        }
    }

    public void retry() {
        player.setDefaultPositions();
        player.restoreLife();
        aSetter.setNPC();
        aSetter.setEnemy();
        kills = 0;
    }

    public void restart() {
        player.setDefaultValues();
        player.setDefaultPositions();
        player.restoreLife();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
        kills = 0;
    }

    public void setFullScreen() {

        // ENCONTRA O TAMANHO DA TELA DO DISPOSITIVO
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        gd.setFullScreenWindow(progressBarDemo.window);

        // ENCONTRA A LARGURA E ALTURA TOTAL DA TELA
        screenWidth2 = progressBarDemo.window.getWidth();
        screenHeight2 = progressBarDemo.window.getHeight();
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                drawToTempScreen(); // Desenha tudo na buffered image
                drawToScreen(); // Desenha a buffered image na tela
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        
        if (gameState == playState) {
            // JOGADOR
            player.update();
            
            //NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }

            // ENEMY

            for (int i = 0; i < enemy.length; i++) {
                if (enemy[i] != null) {
                    if (enemy[i].alive == true && enemy[i].dying == false) {
                        enemy[i].update();
                    }
                    if (enemy[i].alive == false) {
                        enemy[i] = null;
                        kills++;
                    }

                }
            }
        }
        if (gameState == pauseState) {
          // nothing
        }

        if (kills == 5) {
            gameState = thanksState;
            kills = 0;
        }
        
    }

    public void drawToTempScreen() {

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // TITLE SCREEN
        if (gameState == titleState) {
            ui.draw(g2);
        }
        // OTHERS
        else {
            // TILE
            tileM.draw(g2);

            entityList.add(player);

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }

            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    entityList.add(obj[i]);
                }
            }

            for (int i = 0; i < enemy.length; i++) {
                if (enemy[i] != null) {
                    entityList.add(enemy[i]);
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {

                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // DRAW ENTITIES
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }
            // EMPTY ENTITY LIST
            entityList.clear();

            //UI
            ui.draw(g2);
        }

        // DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
    }

    public void drawToScreen() {
        Graphics g = getGraphics();
        g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2, null);
        g.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {

        music.stop();
    }

    public void playSE(int i) {

        se.setFile(i);
        //se.play();
    }
}

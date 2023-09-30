package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // CONFIGURAÇÕES DA TELA
    final int originalTileSize = 32; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 10;
    public final int maxScreenRow = 7;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize* maxScreenRow;

    // CONFIGURAÇÕES DO MUNDO
    public final int maxWorldCol = 10;
    public final int maxWorldRow = 7;

    // FPS
    int FPS = 60;
    
    // SISTEMA
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;
    
    // ESTADO DO JOGO
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    
    // ENTIDADE E OBJETO
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame() {
        aSetter.setObject();
        gameState = playState;

        //playMusic(0);
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
                repaint();
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
            player.update();
        }
        if (gameState == pauseState) {
            
        }
        
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyH.checkDrawTime == true) {
            drawStart = System.nanoTime();
        }


        // TILE
        tileM.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);
        
        //UI
        ui.draw(g2);
        g2.dispose();

        // DEBUG
        if (keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time: " + passed, 10, 400);
            System.out.println("Draw Time: " + passed);
        }
    }

    public void playMusic(int i) {

        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {

        music.stop();
    }

    public void playerSE(int i) {

        se.setFile(i);
        //se.play();
    }
}

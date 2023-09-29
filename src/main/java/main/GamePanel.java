package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // CONFIGURAÇÕES DA TELA
    final int originalTileSize = 32; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; //48x48 tile
    public final int maxScreenCol = 10;
    public final int MaxScreenRow = 7;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize* MaxScreenRow; // 576 pixels

    int FPS = 60;
    
    // SISTEMA
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    // ESTADO DO JOGO
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;
    
    // ENTIDADE E OBJETO
    Player player = new Player(this, keyH);
    
    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame() {
        gameState = playState;
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
        
        // TILE
        tileM.draw(g2);
        
        // PLAYER
        player.draw(g2);
        
        //UI
        ui.draw(g2);
        g2.dispose();
    }
}

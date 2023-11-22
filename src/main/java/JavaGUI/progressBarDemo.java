package JavaGUI;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class progressBarDemo {

    GamePanel gp;
    public static JFrame window;
    JFrame frame = new JFrame();
    JProgressBar bar = new JProgressBar(0 , 100);

    public progressBarDemo() {

        bar.setValue(0);
        bar.setBounds(10, 500, 924, 20);
        bar.setStringPainted(true);
        bar.setFont(new Font("Roboto", Font.BOLD, 15));
        bar.setForeground(Color.red);

        frame.getContentPane().setBackground(Color.black);
        frame.add(bar);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(960, 576);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        fill();

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Java Adventure 0.1");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        //gamePanel.config.loadConfig();

        if (gamePanel.fullScreenOn) {
            window.setUndecorated(true);
        }

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }

    public void fill() {
        int counter = 0;

        while (counter <= 100) {
            bar.setValue(counter);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter += 1;
        }
        bar.setString("Done");
        frame.setVisible(false);
    }
}

package main;

import JavaGUI.progressBarDemo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class MainApp {

    public static JFrame window;

    //@Override
    //public void start(Stage stage) throws IOException {
    //    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/telas/sample.fxml"));
    //    Scene scene = new Scene(fxmlLoader.load());
    //    stage.setTitle("Main Games");
    //    stage.setResizable(false);
    //    stage.setScene(scene);
    //    stage.show();
    //}

    public static void main(String[] args) {

        progressBarDemo demo = new progressBarDemo();

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Java Adventure 0.1");

        var gamePanel = new GamePanel();
        window.add(gamePanel);

        gamePanel.config.loadConfig();

        if (gamePanel.fullScreenOn) {
            window.setUndecorated(true);
        }

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}

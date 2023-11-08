package interfaces;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import main.GamePanel;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadingController implements Initializable {

    @FXML
    ProgressBar myProgressBar;

    @FXML
    Label myLabel;

    public Task<ObservableList> task = new Task<ObservableList>() {
        @Override
        protected ObservableList call() throws Exception {
            for (int i = 0; i < 101; i++) {
                updateProgress(i, 99);
                updateMessage("" + i);
                Thread.sleep(200);
            }
            return null;
        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myProgressBar.progressProperty().bind(task.progressProperty());
        task.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                myLabel.setText(t1 + "%");
            }
        });
        new Thread(task).start();
        task.setOnSucceeded(ev->{
            myLabel.setText("Carregado");
            myProgressBar.getScene().getWindow().hide();
            JFrame window = new JFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("Java Coffee Shop");

            var gamePanel = new GamePanel();
            window.add(gamePanel);

            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);

            gamePanel.setupGame();
            gamePanel.startGameThread();
        });
    }
}

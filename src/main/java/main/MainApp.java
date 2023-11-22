package main;

import ConectaBD.Conecta_MySql;
import ConectaBD.User;
import JavaGUI.progressBarDemo;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.*;
import java.sql.*;

public class MainApp extends Application {

    Stage window;
    Scene scene1, scene2, scene3;

    Conecta_MySql conecta = new Conecta_MySql();

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Bem vindo(a) ao Main");

//        Rectangle r = new Rectangle();
//        r.setX(450);
//        r.setY(300);
//        r.setWidth(450);
//        r.setHeight(550);
//        r.setArcWidth(10);
//        r.setArcHeight(10);
//        r.setFill(Color.rgb(21, 21, 21));

        Label label1 = new Label("Entre na sua conta da Main");

        Label emailLabel = new Label("Endereço de email");
        TextField emailInput = new TextField();
        emailInput.setPromptText("Endereço de email");

        Label passwordLabel = new Label("Senha");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Senha");

        Button button1 = new Button();
        button1.setText("ENTRAR");
        button1.setMinWidth(310);
        button1.setMinHeight(50);
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Email: " + emailInput.getText());
                String email = emailInput.getText();
                System.out.println("Senha: " + passwordInput.getText());
                String senha = passwordInput.getText();

                conecta.user = conecta.getAuthenticatedUser(email, senha);

                if (conecta.user != null) {
                    System.out.println("Logado com sucesso.");
                    System.out.println("E-mail: " + conecta.user.email);
                    System.out.println("Username: " + conecta.user.username);
                    System.out.println("Password: " + conecta.user.senha);

                    window.close();
                    progressBarDemo demo = new progressBarDemo();
                } else {
                    JOptionPane.showMessageDialog(null,
                            "E-mail ou senha inválida.",
                            "Tente de novo!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        Hyperlink hyperlink1 = new Hyperlink("Esqueceu sua senha?");

        Hyperlink hyperlink2 = new Hyperlink("Cadastre-se");
        hyperlink2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(scene2);
            }
        });

        VBox layout1 = new VBox(20);
        layout1.setPadding(new Insets(20, 20, 20, 20));
        layout1.getChildren().addAll(label1, emailLabel, emailInput, passwordLabel, passwordInput, button1, hyperlink1,
                hyperlink2);

        scene1 = new Scene(layout1, 350, 450);
        scene1.getStylesheets().add("/style.css");

        Label emailLabel2 = new Label("Endereço de email");

        TextField emailInput2 = new TextField();
        emailInput2.setPromptText("Endereço de email");

        Label nameLabel = new Label("Nome");

        TextField nameInput = new TextField();
        nameInput.setPromptText("Nome");

        Label exibitionNameLabel = new Label("Nome de exibição");

        TextField exibitionNameInput = new TextField();
        exibitionNameInput.setPromptText("Nome de exibição");

        Label passwordLabel2 = new Label("Senha");

        PasswordField passwordInput2 = new PasswordField();
        passwordInput2.setPromptText("Senha");

        Button button2 = new Button();
        button2.setText("Cadastrar");
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Email: " + emailInput2.getText());
                String email = emailInput.getText();
                System.out.println("Nome: " + nameInput.getText());
                String nome = nameInput.getText();
                System.out.println("Nome de exibição: " + exibitionNameInput.getText());
                String nomeExibicao = exibitionNameInput.getText();
                System.out.println("Senha: " + passwordInput2.getText());
                String senha = passwordInput.getText();

                conecta.dataBaseInsert(email, nome, nomeExibicao, senha);
                window.close();
                progressBarDemo demo = new progressBarDemo();
            }
        });
        button2.setMinWidth(310);
        button2.setMinHeight(50);

        Hyperlink hyperlink3 = new Hyperlink("Voltar");
        hyperlink3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(scene1);
            }
        });

        VBox layout2 = new VBox(20);
        layout2.setPadding(new Insets(20, 20, 20, 20));
        layout2.getChildren().addAll(emailLabel2, emailInput2, nameLabel, nameInput, exibitionNameLabel,
                                     exibitionNameInput, passwordLabel2, passwordInput2, button2, hyperlink3);

        scene2 = new Scene(layout2, 350, 550);
        scene2.getStylesheets().add("/style.css");

        window.setScene(scene1);
        window.show();
    }
}


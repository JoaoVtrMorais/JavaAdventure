package interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;

public class Controller  {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    String email1;
    String senha;

    public User user;

    public User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String localBD = "localhost";
        final String LINK = "jdbc:mysql://" + localBD + ":3306/main_users";
        final String usuario = "root";
        final String senha1 = "root";

        try {
            Connection conexao_MySql = DriverManager.getConnection(LINK, usuario, senha1);
            System.out.println("conexão OK!");

            Statement stmt = conexao_MySql.createStatement();
            String sql = "SELECT * FROM users WHERE email = ? AND senha = ?";
            PreparedStatement preparedStatement = conexao_MySql.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.username = resultSet.getString("username");
                user.email = resultSet.getString("email");
                user.senha = resultSet.getString("senha");
            }

            stmt.close();
            conexao_MySql.close();

        } catch (SQLException e) {
            throw new
                    RuntimeException("Ocorreu um problema na conexão com o BD", e);
        }

        return user;
    }

    public void login(ActionEvent event) throws IOException {

        email1 = email.getText();
        senha = password.getText();

        user = getAuthenticatedUser(email1, senha);

        if (user != null) {
            System.out.println("Logado com sucesso.");
            System.out.println("E-mail: " + user.email);
            System.out.println("Username: " + user.username);
            System.out.println("Password: " + user.senha);

            root = FXMLLoader.load(getClass().getResource("/telas/loading.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            JOptionPane.showMessageDialog(null,
                                "E-mail ou senha inválida.",
                                "Tente de novo!", JOptionPane.ERROR_MESSAGE);
        }

    }
}
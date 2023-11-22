package ConectaBD;

import java.sql.*;

public class Conecta_MySql {
    public User user;
    private static Connection conexao_MySql = null;
    private static String localBD = "localhost";
    private static String LINK =
            "jdbc:mysql://" + localBD + ":3306/main_users";
    private static final String usuario = "root";
    private static final String senha = "root";

    public Connection connectionMySql() {
        try {
            conexao_MySql =
                    DriverManager.getConnection(LINK, usuario, senha);
            System.out.println("conexão OK!");
        } catch (SQLException e) {
            throw new
                    RuntimeException("Ocorreu um problema na conexão com o BD", e);
        }
        return conexao_MySql;
    }

    public User getAuthenticatedUser(String email, String password) {
        User user = null;
        Connection connection = connectionMySql();
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM users WHERE email = ? AND senha = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
            connection.close();
        } catch (SQLException e) {
            throw new
                    RuntimeException("Ocorreu um problema na conexão com o BD", e);
        }

        return user;
    }

    public void dataBaseInsert(String email, String nome, String nomeExibicao, String senha) {
        Connection connection = connectionMySql();
        String sql = "INSERT INTO users (email, nome, username, senha) VALUES (?,?,?,?)";
        PreparedStatement preparedStmt;
        try {
            preparedStmt = connection.prepareStatement(sql);
            //Efetua a troca do '?' pelos valores na query
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, nome);
            preparedStmt.setString(3, nomeExibicao);
            preparedStmt.setString(4, senha);
            preparedStmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

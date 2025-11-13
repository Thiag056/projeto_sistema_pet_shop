package ucb.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/pet_shop";
    private static final String USER = "root";
    private static final String PASSWORD = "045607";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);


            System.out.println(" Conexão com o banco de dados 'pet_shop' estabelecida com sucesso!");

            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println(" Erro: Driver JDBC não encontrado. Verifique a dependência/JAR no IntelliJ.");
            throw new SQLException("Driver JDBC não encontrado.", e);
        } catch (SQLException e) {
            System.err.println(" Erro de conexão com o banco de dados! Verifique URL, Usuário e Senha.");
            System.err.println("Detalhes: " + e.getMessage());
            throw e;
        }
    }
}
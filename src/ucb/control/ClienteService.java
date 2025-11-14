package ucb.control;

import ucb.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteService {


    public Cliente criarCliente(String cpf, String nome, String sobrenome, String telefone) {
        if (buscarClientePorCpf(cpf).isPresent()) {
            return null;
        }


        String sql = "INSERT INTO Cliente (cpf, nome, sobrenome, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, nome);
            stmt.setString(3, sobrenome);
            stmt.setString(4, telefone); // Último parâmetro

            stmt.executeUpdate();

            return new Cliente(cpf, nome, sobrenome, telefone);

        } catch (SQLException e) {
            System.err.println("Erro SQL ao criar cliente: " + e.getMessage());
            return null;
        }
    }


    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT cpf, nome, sobrenome, telefone FROM Cliente";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("sobrenome"),
                        rs.getString("telefone") // 4º parâmetro
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("Erro SQL ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }


    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        String sql = "SELECT cpf, nome, sobrenome, telefone FROM Cliente WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente(
                            rs.getString("cpf"),
                            rs.getString("nome"),
                            rs.getString("sobrenome"),
                            rs.getString("telefone")
                    );
                    return Optional.of(cliente);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro SQL ao buscar cliente: " + e.getMessage());
        }
        return Optional.empty();
    }


    public boolean atualizarCliente(String cpf, String nome, String sobrenome, String telefone) {

        String sql = "UPDATE Cliente SET nome = ?, sobrenome = ?, telefone = ? WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, sobrenome);
            stmt.setString(3, telefone); // 3º parâmetro
            stmt.setString(4, cpf);      // 4º parâmetro (WHERE clause)

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro SQL ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }

    public boolean excluirCliente(String cpf) {
        String sql = "DELETE FROM Cliente WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Erro SQL ao excluir cliente: " + e.getMessage());
            return false;
        }
    }
}
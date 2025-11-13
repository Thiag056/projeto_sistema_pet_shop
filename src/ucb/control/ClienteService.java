package ucb.control;

import ucb.model.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public Cliente criarCliente(String cpf, String nome, String sobrenome, String primeiroNome, String telefone) {

        if (buscarClientePorCpf(cpf).isPresent()) {
            return null;
        }
        Cliente novoCliente = new Cliente(cpf, nome, sobrenome, primeiroNome, telefone);
        clientes.add(novoCliente);
        return novoCliente;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public Optional<Cliente> buscarClientePorCpf(String cpf) {

        return clientes.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst();
    }

    public boolean atualizarCliente(String cpf, String nome, String sobrenome, String primeiroNome, String telefone) {
        Optional<Cliente> optionalCliente = buscarClientePorCpf(cpf);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            cliente.setNome(nome);
            cliente.setSobrenome(sobrenome);
            cliente.setPrimeiroNome(primeiroNome);
            cliente.setTelefone(telefone);
            return true;
        }
        return false;
    }

    public boolean excluirCliente(String cpf) {

        return clientes.removeIf(c -> c.getCpf().equals(cpf));
    }
}
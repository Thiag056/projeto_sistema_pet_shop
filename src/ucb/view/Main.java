package ucb.view;

import ucb.control.ClienteService;
import ucb.model.Cliente;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static ClienteService service = new ClienteService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // ... (código do menu permanece o mesmo)
        int opcao;

        do {
            exibirMenu();
            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1: criarCliente(); break;
                    case 2: listarClientes(); break;
                    case 3: atualizarCliente(); break;
                    case 4: excluirCliente(); break;
                    case 0: System.out.println("Saindo do sistema. Até mais!"); break;
                    default: System.out.println("Opção inválida. Tente novamente.");
                }
            } else {
                System.out.println("Entrada inválida. Digite um número.");
                scanner.next();
                opcao = -1;
            }
            System.out.println("----------------------------------------");
        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("========= MENU CRUD CLIENTE =========");
        System.out.println("1 - Criar Novo Cliente (CREATE)");
        System.out.println("2 - Listar Clientes (READ)");
        System.out.println("3 - Atualizar Cliente (UPDATE)");
        System.out.println("4 - Excluir Cliente (DELETE)");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarCliente() {
        System.out.println("\n--- CRIAR NOVO CLIENTE ---");
        System.out.print("Digite o CPF do cliente (ID): ");
        String cpf = scanner.nextLine();

        if (service.buscarClientePorCpf(cpf).isPresent()) {
            System.out.println("❌ Erro: Cliente com CPF " + cpf + " já existe!");
            return;
        }

        System.out.print("Digite o Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o Sobrenome: ");
        String sobrenome = scanner.nextLine();

        System.out.print("Digite o Telefone: ");
        String telefone = scanner.nextLine();

        // Chamada atualizada (SEM primeiroNome)
        Cliente novo = service.criarCliente(cpf, nome, sobrenome, telefone);

        if (novo != null) {
            System.out.println("\n✅ Cliente criado com sucesso!");
            System.out.println(novo);
        } else {
            System.out.println("❌ Falha na criação ou CPF já existe.");
        }
    }

    private static void listarClientes() {
        System.out.println("\n--- LISTA DE CLIENTES ---");
        List<Cliente> clientes = service.listarClientes();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        clientes.forEach(System.out::println);
    }

    private static void atualizarCliente() {
        System.out.println("\n--- ATUALIZAR CLIENTE ---");
        System.out.print("Digite o CPF do cliente que deseja atualizar: ");
        String cpf = scanner.nextLine();

        Optional<Cliente> clienteExistente = service.buscarClientePorCpf(cpf);

        if (clienteExistente.isEmpty()) {
            System.out.println("❌ Cliente com CPF " + cpf + " não encontrado.");
            return;
        }

        Cliente cliente = clienteExistente.get();
        System.out.println("Cliente atual: " + cliente);

        System.out.print("Digite o NOVO Nome (Atual: " + cliente.getNome() + " | Deixe em branco para manter): ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite o NOVO Sobrenome (Atual: " + cliente.getSobrenome() + " | Deixe em branco para manter): ");
        String novoSobrenome = scanner.nextLine();

        System.out.print("Digite o NOVO Telefone (Atual: " + cliente.getTelefone() + " | Deixe em branco para manter): ");
        String novoTelefone = scanner.nextLine();

        String nomeFinal = novoNome.isEmpty() ? cliente.getNome() : novoNome;
        String sobrenomeFinal = novoSobrenome.isEmpty() ? cliente.getSobrenome() : novoSobrenome;
        String telefoneFinal = novoTelefone.isEmpty() ? cliente.getTelefone() : novoTelefone;

        // Chamada atualizada (SEM primeiroNome)
        if (service.atualizarCliente(cpf, nomeFinal, sobrenomeFinal, telefoneFinal)) {
            System.out.println("\n✅ Cliente CPF " + cpf + " atualizado com sucesso!");
            service.buscarClientePorCpf(cpf).ifPresent(System.out::println);
        } else {
            System.out.println("❌ Falha ao atualizar. Tente novamente.");
        }
    }

    private static void excluirCliente() {
        System.out.println("\n--- EXCLUIR CLIENTE ---");
        System.out.print("Digite o CPF do cliente que deseja EXCLUIR: ");
        String cpf = scanner.nextLine();

        if (service.excluirCliente(cpf)) {
            System.out.println("\n🗑️ Cliente com CPF " + cpf + " excluído com sucesso.");
        } else {
            System.out.println("❌ Cliente com CPF " + cpf + " não encontrado.");
        }
    }
}
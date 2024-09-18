import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Criar nova conta");
            System.out.println("2. Fazer login");
            System.out.println("3. Sair");
            System.out.print("Opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    criarNovaConta(scanner);
                    break;
                case 2:
                    fazerLogin(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 3);

        scanner.close();
    }

    // Método para criar uma nova conta
    private static void criarNovaConta(Scanner scanner) {
        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o nome completo: ");
        String nomeCompleto = scanner.nextLine();
        System.out.print("Digite a data de nascimento (dd/mm/yyyy): ");
        String dataNascimento = scanner.nextLine();
        System.out.print("Digite o sexo (M/F): ");
        String sexo = scanner.nextLine();
        System.out.print("Digite o e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();
        System.out.print("Digite o saldo inicial: R$ ");
        double saldoInicial = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer

        Cliente novoCliente = new Cliente(cpf, nomeCompleto, dataNascimento, sexo, email, senha);
        Conta novaConta = new Conta(novoCliente, saldoInicial);
        clientes.add(novoCliente);
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso para " + nomeCompleto + ".");
    }

    // Método para fazer login
    private static void fazerLogin(Scanner scanner) {
        System.out.println("Escolha a opção de login:");
        System.out.println("1. Login com CPF");
        System.out.println("2. Login com e-mail");
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        Cliente clienteLogado = null;
        String senha;

        if (opcao == 1) {
            System.out.print("Digite o CPF: ");
            String cpf = scanner.nextLine();
            clienteLogado = encontrarClientePorCPF(cpf);

        } else if (opcao == 2) {
            System.out.print("Digite o e-mail: ");
            String email = scanner.nextLine();
            clienteLogado = encontrarClientePorEmail(email);
        }

        if (clienteLogado != null) {
            System.out.print("Digite a senha: ");
            senha = scanner.nextLine();
            if (clienteLogado.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso!");
                menuConta(scanner, encontrarContaPorCliente(clienteLogado));
            } else {
                System.out.println("Senha incorreta.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    // Método para menu da conta
    private static void menuConta(Scanner scanner, Conta conta) {
        int opcao;
        do {
            System.out.println("\nMenu da Conta:");
            System.out.println("1. Depósito");
            System.out.println("2. Saque");
            System.out.println("3. Consulta de Saldo");
            System.out.println("4. Transferência");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    realizarDeposito(scanner, conta);
                    break;
                case 2:
                    realizarSaque(scanner, conta);
                    break;
                case 3:
                    conta.consultarSaldo();
                    break;
                case 4:
                    realizarTransferencia(scanner, conta);
                    break;
                case 5:
                    System.out.println("Saindo do menu da conta.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 5);
    }

    // Método para encontrar cliente por CPF
    private static Cliente encontrarClientePorCPF(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    // Método para encontrar cliente por e-mail
    private static Cliente encontrarClientePorEmail(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }

    // Método para encontrar conta associada ao cliente
    private static Conta encontrarContaPorCliente(Cliente cliente) {
        for (Conta conta : contas) {
            if (conta.getCliente().equals(cliente)) {
                return conta;
            }
        }
        return null;
    }

    // Método para realizar um depósito
    private static void realizarDeposito(Scanner scanner, Conta conta) {
        System.out.print("Digite o valor do depósito: R$ ");
        double valor = scanner.nextDouble();
        conta.depositar(valor);
    }

    // Método para realizar um saque
    private static void realizarSaque(Scanner scanner, Conta conta) {
        System.out.print("Digite o valor do saque: R$ ");
        double valor = scanner.nextDouble();
        conta.sacar(valor);
    }

    // Método para realizar uma transferência
    private static void realizarTransferencia(Scanner scanner, Conta contaOrigem) {
        System.out.print("Digite o nome do cliente da conta de destino: ");
        String nomeClienteDestino = scanner.nextLine();
        Conta contaDestino = encontrarContaPorCliente(encontrarClientePorEmail(nomeClienteDestino));

    }
}
        

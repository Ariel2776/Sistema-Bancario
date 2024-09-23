import java.util.ArrayList;
import java.util.Scanner;

public class Banco {
    private static ArrayList<Conta> contas = new ArrayList<>();
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu Principal:");
            System.out.println("1. Criar nova conta");
            System.out.println("2. Fazer login");
            System.out.println("3. Sair");
            System.out.print("Opção: ");
            opcao = ler.nextInt();
            ler.nextLine();

            switch (opcao) {
                case 1:
                    criarNovaConta(ler);
                    break;
                case 2:
                    fazerLogin(ler);
                    break;
                case 3:
                    System.out.println("Saindo do sistema.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 3);

        ler.close();
    }

    //Criar Nova conta
    private static void criarNovaConta(Scanner ler) {
        //Validar CPF
        System.out.print("Digite o CPF: ");

        String CPF = ler.nextLine();
        if (CPF.length() == 11 && CPF.matches("\\d+")) {
            String CPFformatado = CPF.substring(0, 3) + "." 
                                + CPF.substring(3, 6) + "." 
                                + CPF.substring(6, 9) + "-" 
                                + CPF.substring(9, 11);
            System.out.println("CPF " + CPFformatado + " é válido.");
        }//fim if
         else {
            System.out.println("CPF inválido. Deve conter 11 dígitos numéricos.");
            return;  
         }//fim else
        System.out.print("Digite o nome completo: ");
        String nomeCompleto = ler.nextLine();
        System.out.print("Digite a data de nascimento (dd/mm/yyyy): ");
        String dataNascimento = ler.nextLine();
        String sexo;    
        
        do {
            System.out.print("Digite o sexo (m/f ou masculino/feminino): ");
            sexo = ler.nextLine().toLowerCase();
            if (sexo.equals("m") || sexo.equals("f") || sexo.equals("masculino") || sexo.equals("feminino")) {
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, digite M, F, masculino ou feminino.");
            }
        } while (true);

        System.out.print("Digite o e-mail: ");
        String email = ler.nextLine();
        System.out.print("Digite a senha: ");
        String senha = ler.nextLine();
        System.out.print("Digite o saldo inicial: R$ ");
        double saldoInicial = ler.nextDouble();
        ler.nextLine(); 

        Cliente novoCliente = new Cliente(CPF, nomeCompleto, dataNascimento, sexo, email, senha);
        Conta novaConta = new Conta(novoCliente, saldoInicial);
        clientes.add(novoCliente);
        contas.add(novaConta);
        System.out.println("Conta criada com sucesso para " + nomeCompleto + ".");
    }//fim Criar Conta

    //FazerLogin
    private static void fazerLogin(Scanner ler) {
        System.out.println("Escolha a opção de login:");
        System.out.println("1. Login com CPF");
        System.out.println("2. Login com e-mail");
        int opcao = ler.nextInt();
        ler.nextLine();

        Cliente clienteLogado = null;
        String senha;

        if (opcao == 1) {
            System.out.print("Digite o CPF: ");
            String CPF = ler.nextLine();
            clienteLogado = encontrarClientePorCPF(CPF);

        } else if (opcao == 2) {
            System.out.print("Digite o e-mail: ");
            String email = ler.nextLine();
            clienteLogado = encontrarClientePorEmail(email);
        }

        if (clienteLogado != null) {
            System.out.print("Digite a senha: ");
            senha = ler.nextLine();
            if (clienteLogado.getSenha().equals(senha)) {
                System.out.println("Login realizado com sucesso!");
                menuConta(ler, encontrarContaPorCliente(clienteLogado));
            } else {
                System.out.println("Senha incorreta.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    //Menu da conta
    private static void menuConta(Scanner ler, Conta conta) {
        int opcao;
        do {
            System.out.println("\nMenu da Conta:");
            System.out.println("1. Depósito");
            System.out.println("2. Saque");
            System.out.println("3. Consulta de Saldo");
            System.out.println("4. Transferência");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = ler.nextInt();
            ler.nextLine();

            switch (opcao) {
                case 1:
                    realizarDeposito(ler, conta);
                    break;
                case 2:
                    realizarSaque(ler, conta);
                    break;
                case 3:
                    conta.consultarSaldo();
                    break;
                case 4:
                    realizarTransferencia(ler, conta);
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

    //Encontrar cliente por CPF
    private static Cliente encontrarClientePorCPF(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }//fim Encontrar Cliente

    //Encontrar cliente por e-mail
    private static Cliente encontrarClientePorEmail(String email) {
        for (Cliente cliente : clientes) {
            if (cliente.getEmail().equals(email)) {
                return cliente;
            }
        }
        return null;
    }//fim ClienteEmail

    //Encontrar conta associada ao cliente
    private static Conta encontrarContaPorCliente(Cliente cliente) {
        for (Conta conta : contas) {
            if (conta.getCliente().equals(cliente)) {
                return conta;
            }
        }
        return null;
    }

    //Realizar um depósito
    private static void realizarDeposito(Scanner ler, Conta conta) {
        System.out.print("Digite o valor do depósito: R$ ");
        double valor = ler.nextDouble();
        conta.depositar(valor);
    }

    //Realizar um saque
    private static void realizarSaque(Scanner ler, Conta conta) {
        System.out.print("Digite o valor do saque: R$ ");
        double valor = ler.nextDouble();
        conta.sacar(valor);
    }

    //Realizar uma transferência
    private static void realizarTransferencia(Scanner ler, Conta contaOrigem) {
        System.out.print("Digite o nome do cliente da conta de destino: ");
        String nomeClienteDestino = ler.nextLine();
        Conta contaDestino = encontrarContaPorCliente(encontrarClientePorEmail(nomeClienteDestino));

    }
}//fim class
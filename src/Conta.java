public class Conta {
    private Cliente cliente;
    private double saldo;


    public Conta(Cliente cliente, double saldoInicial) {
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    // Depósito
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Valor do depósito deve ser positivo.");
        }
    }

    // Saque
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }
    }

    // Saldo
    public void consultarSaldo() {
        System.out.println("Saldo atual: R$ " + saldo);
    }

    // Transferência
    public void transferir(double valor, Conta contaDestino) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            contaDestino.depositar(valor);
            System.out.println("Transferência de R$ " + valor + " realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para transferência.");
        }
    }

    // Getters
    public Cliente getCliente() {
        return cliente;
    }
}

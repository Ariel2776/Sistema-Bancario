public class Conta {
    private Cliente cliente;
    private double saldo;


    public Conta(Cliente cliente, double saldoInicial) {
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }//fim conta

    //Depósito
    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            System.out.println("Depósito de R$ " + valor + " realizado com sucesso.");
        }//fim if
         else {
            System.out.println("Valor do depósito deve ser positivo.");
        }//fim else
    }//fim depositar

    //Saque
    public void sacar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            System.out.println("Saque de R$ " + valor + " realizado com sucesso.");
        }//fim if 
        else {
            System.out.println("Saldo insuficiente ou valor inválido.");
        }//fim else
    }//fim Saque

    //Saldo
    public void consultarSaldo() {
        System.out.println("Saldo atual: R$ " + saldo);
    }//fim consultar

    //Transferência
    public void transferir(double valor, Conta contaDestino) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            contaDestino.depositar(valor);
            System.out.println("Transferência de R$ " + valor + " realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para transferência.");
        }//fim else
    }//fim transferencia

    //Get
    public Cliente getCliente() {
        return cliente;
    }
}//fim class

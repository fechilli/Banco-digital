package banco;

public class Conta implements IConta {

    private static final int AGENCIA_PADRAO = 1;
    private static int SEQUENCIAL = 1;

    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = getNextSequential();
        this.cliente = cliente;
    }

    private synchronized int getNextSequential() {
        return SEQUENCIAL++;
    }

    @Override
    public void sacar(double valor) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente para saque");
            saldoAtual();
        } else {
            saldo -= valor;
        }
    }

    @Override
    public void depositar(double valor) {
        saldo += valor;
        System.out.println("O valor do depósito foi de R$: " + valor);
        saldoAtual();
    }

    @Override
    public void transferir(double valor, IConta contaDestino) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente para transferência");
            saldoAtual();
        } else {
            this.sacar(valor);
            contaDestino.depositar(valor);
        }
    }

    @Override
    public void imprimirExtrato() {
        imprimirInfosComuns();
    }

    @Override
    public void saldoAtual() {
        System.out.println("Seu Saldo atual é de R$: " + saldo);
    }

    @Override
    public void pagarBoleto(double valor) {
        if (saldo < valor) {
            System.out.println("Saldo Insuficiente...");
        } else {
            saldo -= valor;
        }
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns() {
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agencia: %d", this.agencia));
        System.out.println(String.format("Numero: %d", this.numero));
        System.out.println(String.format("Saldo: %.2f", this.saldo));
    }
}

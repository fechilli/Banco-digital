package banco;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public void imprimirExtrato() {
        System.out.println("=== Extrato Conta Corrente ===");
        super.imprimirInfosComuns();
    }

    public class Conta implements IConta {

        private static final int AGENCIA_PADRAO = 1;
        private static int SEQUENCIAL = 1;

        protected int agencia;
        protected int numero;
        protected double saldo;
        protected Cliente cliente;

        public Conta(Cliente cliente) {
            this.agencia = Conta.AGENCIA_PADRAO;
            this.numero = SEQUENCIAL++;
            this.cliente = cliente;
        }

        @Override
        public void sacar(double valor) {
            if(valor > saldo){
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
            this.sacar(valor);
            contaDestino.depositar(valor);
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

                if (saldo < valor){
                    System.out.println("Saldo Insuficiente...");
                }else{
                    saldo -= valor;
                }
            }

        }
}

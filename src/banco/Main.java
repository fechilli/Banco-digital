package banco;

public class Main {
    public static void main(String[] args) {
        Cliente felipe = new Cliente();
        felipe.setNome("Felipe");

        Conta cc = new ContaCorrente(felipe);
        Conta poupanca = new ContaPoupanca(felipe);

        cc.depositar(200.0);
        cc.sacar(110.0);
        cc.transferir(50.0, poupanca);

        cc.imprimirExtrato();
        poupanca.imprimirExtrato();
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public abstract class Conta implements ITaxas{

    private int numero;

    private Cliente dono;

    private double saldo;

    protected double limite;

    private ArrayList<Operacao> operacoes;

    private int proximaOperacao;

    private static int totalContas = 0;

    public Conta(int numero, Cliente dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;

        this.operacoes = new ArrayList<>(1000);
        this.proximaOperacao = 0;

        Conta.totalContas++;
    }

    public boolean sacar(double valor) {
        if (valor >= 0 && valor <= this.limite) {
            this.saldo -= valor;

            this.operacoes.add(proximaOperacao, new OperacaoSaque(valor));
            this.proximaOperacao++;
            return true;
        }

        return false;
    }

    public void depositar(double valor) {
        this.saldo += valor;

        this.operacoes.add(proximaOperacao, new OperacaoDeposito(valor));
        this.proximaOperacao++;
    }

    public boolean transferir(Conta destino, double valor) {
        boolean valorSacado = this.sacar(valor);
        if (valorSacado) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return dono.toString() + '\n' +
                "---\n" +
                "numero=" + numero + '\n' +
                "saldo=" + saldo + '\n' +
                "limite=" + limite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof Conta) {
            Conta conta = (Conta) o;
            return numero == conta.numero;
        }
        return false;
    }

    public void imprimirExtrato(int typExc) {

        if(typExc == 1){
            Collections.sort(operacoes); //Ordena
        }

        System.out.println("======= Extrato Conta " + this.numero + "======");

            for(Operacao atual : this.operacoes) {
            if (atual != null) {
                System.out.println(atual);
            }
        }
        System.out.println("====================");
    }

    public void imprimirExtratoTaxas() {
        System.out.println("=== Extrato de Taxas ===");
        System.out.printf("Manutenção:\t%.2f\n", this.calculaTaxas());

        double totalTaxas = this.calculaTaxas();
        for (int i = 0; i < this.proximaOperacao; i++) {
            Operacao atual = this.operacoes.get(i);

            totalTaxas += atual.calculaTaxas();
            System.out.printf("%c:\t%.2f\n", atual.getTipo(), atual.calculaTaxas());
        }

        System.out.printf("Total:\t%.2f\n", totalTaxas);
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public abstract void setLimite(double limite);
}
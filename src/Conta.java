import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Conta implements ITaxas{

    private int numero;

    private Cliente dono;

    private double saldo;

    private double limite;

    protected ArrayList<Operacao> Operacoes = new ArrayList<Operacao>();

    private int proximaOperacao;

    private static int totalContas = 0;

    public Conta(int numero, Cliente dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;

        Operacoes.add(new OperacaoDeposito(saldo));
        this.proximaOperacao = 0;

        Conta.totalContas++;
    }



    public boolean sacar(double valor) {
        if (valor >= 0 && valor <= this.limite) {
            this.saldo -= valor;
            Operacoes.add (new OperacaoSaque(valor));
            this.proximaOperacao++;
            return true;
        }

        return false;
    }

    public void depositar(double valor) {
        this.saldo += valor;
        Operacoes.add(new OperacaoDeposito(valor));
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

    public void imprimirExtrato() {
        System.out.println("======= Extrato Conta " + this.numero + "======");

        Collections.sort(Operacoes);

        for(Operacao atual : this.Operacoes) {
            if (atual != null) {
                System.out.println(atual);
            }
        }
        System.out.println("====================");
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

    public void setLimite(double limite) {
        if (limite < 0)
            limite = 0;

        this.limite = limite;
    }

    public ArrayList<Operacao> getOperacoes() {

        return Operacoes;

    }

    double Cont = 0;

    public void imprimirExtratoTaxas(){



        System.out.println("== Extrato De Taxas ==");
        System.out.println("Manutencao da conta: " + calculaTaxas());
        System.out.println("\nOperacoes: ");

        Cont += calculaTaxas();
        for (Operacao opc: Operacoes){

            if (Operacoes != null && opc.getTipo() == 's') {

                System.out.println("Saque: " + opc.calculaTaxas());
                Cont += opc.calculaTaxas();

            }
        }

        System.out.println("\nTotal obtido: " + Cont);
    }

}
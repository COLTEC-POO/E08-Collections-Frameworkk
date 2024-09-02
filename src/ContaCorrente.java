public class ContaCorrente extends Conta {

    public ContaCorrente(int numero, Cliente dono, double saldo, double limite) {
        super(numero, dono, saldo, limite);
    }


    @Override
    public double calculaTaxas() {
        return this.getDono().calculaTaxas();
    }

    @Override
    public void setLimite(double limite) {
        if (limite >= -100)
            super.limite = limite;
    }

}
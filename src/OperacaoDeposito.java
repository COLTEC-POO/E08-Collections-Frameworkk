public class OperacaoDeposito extends Operacao {

    public OperacaoDeposito(double valor) {
        super('d', valor);
    }

    @Override
    public double calculaTaxas() {
        return 0f;
    }

    public int compareTo(Operacao o) {
        if(this.getTipo() == o.getTipo()) return 0;
        if(this.getTipo() == 'd') return -1;
        if(this.getTipo() == 's') return 1;

        return 0;
    }
}
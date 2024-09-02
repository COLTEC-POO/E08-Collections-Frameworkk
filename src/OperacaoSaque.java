public class OperacaoSaque extends Operacao {

    public OperacaoSaque(double valor) {
        super('s', valor);
    }

    @Override
    public double calculaTaxas() {
        return 0.05;
    }

    @Override
    public int compareTo(Operacao o) {
        return this.getData().compareTo(o.getData());
    }
}
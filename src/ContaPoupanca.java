public class ContaPoupanca extends Conta{

    public ContaPoupanca (int Num, Cliente Dono, double Saldo, double limite, double saldo){

        super (Num, Dono, saldo, limite);
        setLimite(limite, saldo);

    }

    void setLimite(double limite, double saldo) {

        if ((limite > 1000) && (saldo < 100)){

            saldo = 100;
            limite = 1000;

            saldo = super.getSaldo();
            limite = super.getLimite();

        }

    }

    public double calculaTaxas() {

        return 0;

    }

}
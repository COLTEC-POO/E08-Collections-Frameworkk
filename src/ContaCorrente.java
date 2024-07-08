public class ContaCorrente extends Conta{

    public ContaCorrente(int Num, Cliente Dono, double saldo, double limite){

        super(Num, Dono, saldo, limite);
        setLimite(limite, saldo);

    }



    void setLimite(double limite, double saldo) {

        if (saldo < -100){

            saldo = -100;

            saldo = super.getSaldo();
            limite = super.getLimite();

        }

    }

    public double calculaTaxas() {

        if (getDono().getClass() == PessoaFisica.class){
            return 10;
        }else{
            return 20;
        }

    }

}
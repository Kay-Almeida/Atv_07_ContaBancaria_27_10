package kailaine.mobile.atv_07_contabancaria_27_10.model;
/*
 *@author:<Kailaine Almeida de Souza RA: 1110482313026>
 */
public class ContaEspecial extends ContaBancaria {
    private float limite;

    public ContaEspecial(String cliente, int num_conta, float saldoInicial, float limite) {
        super(cliente, num_conta, saldoInicial);
        this.limite = limite;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public boolean sacar(float valor) {
        if (valor > 0 && valor <= (getSaldo() + limite)) {
            setSaldo(getSaldo() - valor);
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para saque.");
            return false;
        }
    }
}

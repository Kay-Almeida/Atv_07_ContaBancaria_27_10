package kailaine.mobile.atv_07_contabancaria_27_10.model;
/*
 *@author:<Kailaine Almeida de Souza RA: 1110482313026>
 */
public class ContaPoupanca extends ContaBancaria {
    private int diaRendimento;

    public ContaPoupanca(String cliente, int num_conta, float saldoInicial, int diaRendimento) {
        super(cliente, num_conta, saldoInicial);
        this.diaRendimento = diaRendimento;
    }

    public int getDiaRendimento() {
        return diaRendimento;
    }

    public void setDiaRendimento(int diaRendimento) {
        this.diaRendimento = diaRendimento;
    }

    public void calcularNovoSaldo(float taxaRendimento) {
        if (taxaRendimento > 0) {
            float novoSaldo = getSaldo() + (getSaldo() * taxaRendimento / 100);
            setSaldo(novoSaldo);
        } else {
            System.out.println("Taxa de rendimento inválida.");
        }
    }
}

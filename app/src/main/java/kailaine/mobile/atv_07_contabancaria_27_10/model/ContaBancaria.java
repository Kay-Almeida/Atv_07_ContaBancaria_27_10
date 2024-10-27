package kailaine.mobile.atv_07_contabancaria_27_10.model;
/*
 *@author:<Kailaine Almeida de Souza RA: 1110482313026>
 */
public abstract class ContaBancaria {
    private String cliente;
    private int num_conta;
    private float saldo;

    public ContaBancaria(String cliente, int num_conta, float saldoInicial) {
        this.cliente = cliente;
        this.num_conta = num_conta;
        this.saldo = saldoInicial;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getNumConta() {
        return num_conta;
    }

    public void setNumConta(int num_conta) {
        this.num_conta = num_conta;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo){
        this.saldo = saldo;
    }

    public boolean sacar(float valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
            return true;
        } else {
            System.out.println("Saldo insuficiente ou valor inválido para saque.");
            return false;
        }
    }

    public void depositar(float valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("Valor inválido para depósito.");
        }
    }
}


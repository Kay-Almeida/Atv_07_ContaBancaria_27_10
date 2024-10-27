package kailaine.mobile.atv_07_contabancaria_27_10;
/*
 *@author:<Kailaine Almeida de Souza RA: 1110482313026>
 */
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import kailaine.mobile.atv_07_contabancaria_27_10.model.ContaEspecial;
import kailaine.mobile.atv_07_contabancaria_27_10.model.ContaPoupanca;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCliente, editTextNumeroConta, editTextSaldoInicial, editTextValor;
    private RadioGroup radioGroupTipoConta;
    private RadioButton radioButtonPoupanca, radioButtonEspecial;
    private Button buttonDepositar, buttonSacar, buttonCalcularRendimento, buttonMostrarDados;
    private TextView textViewResultado;

    private ContaPoupanca contaPoupanca;
    private ContaEspecial contaEspecial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextCliente = findViewById(R.id.editTextCliente);
        editTextNumeroConta = findViewById(R.id.editTextNumeroConta);
        editTextSaldoInicial = findViewById(R.id.editTextSaldoInicial);
        editTextValor = findViewById(R.id.editTextValor);
        radioGroupTipoConta = findViewById(R.id.radioGroupTipoConta);
        radioButtonPoupanca = findViewById(R.id.radioButtonPoupanca);
        radioButtonPoupanca.setChecked(true);
        radioButtonEspecial = findViewById(R.id.radioButtonEspecial);
        buttonDepositar = findViewById(R.id.buttonDepositar);
        buttonSacar = findViewById(R.id.buttonSacar);
        buttonCalcularRendimento = findViewById(R.id.buttonCalcularRendimento);
        buttonMostrarDados = findViewById(R.id.buttonMostrarDados);
        textViewResultado = findViewById(R.id.textViewResultado);

        buttonDepositar.setOnClickListener(op -> depositar());
        buttonSacar.setOnClickListener(op -> sacar());
        buttonCalcularRendimento.setOnClickListener(op -> calcularRendimento());
        buttonMostrarDados.setOnClickListener(op -> mostrarDados());
    }

    private void inicializarConta() {
        String cliente = editTextCliente.getText().toString();
        int numeroConta = Integer.parseInt(editTextNumeroConta.getText().toString());
        float saldoInicial = Float.parseFloat(editTextSaldoInicial.getText().toString());

        if (radioButtonPoupanca.isChecked()) {
            contaPoupanca = new ContaPoupanca(cliente, numeroConta, saldoInicial, 1);
            contaEspecial = null;
        } else if (radioButtonEspecial.isChecked()) {
            float limite = 500.0f;
            contaEspecial = new ContaEspecial(cliente, numeroConta, saldoInicial, limite);
            contaPoupanca = null;
        }
    }

    private void depositar() {
        inicializarConta();
        float valor = Float.parseFloat(editTextValor.getText().toString());

        if (contaPoupanca != null) {
            contaPoupanca.depositar(valor);
            textViewResultado.setText("Depósito realizado. Novo saldo: " + contaPoupanca.getSaldo());
        } else if (contaEspecial != null) {
            contaEspecial.depositar(valor);
            textViewResultado.setText("Depósito realizado. Novo saldo: " + contaEspecial.getSaldo());
        }
    }

    private void sacar() {
        inicializarConta();
        float valor = Float.parseFloat(editTextValor.getText().toString());

        if (contaPoupanca != null) {
            boolean sucesso = contaPoupanca.sacar(valor);
            if (sucesso) {
                textViewResultado.setText("Saque realizado. Novo saldo: " + contaPoupanca.getSaldo());
            } else {
                textViewResultado.setText("Saque não permitido.");
            }
        } else if (contaEspecial != null) {
            boolean sucesso = contaEspecial.sacar(valor);
            if (sucesso) {
                textViewResultado.setText("Saque realizado. Novo saldo: " + contaEspecial.getSaldo());
            } else {
                textViewResultado.setText("Saque não permitido.");
            }
        }
    }

    private void calcularRendimento() {
        if (contaPoupanca != null) {
            float taxaRendimento = 2.0f;
            contaPoupanca.calcularNovoSaldo(taxaRendimento);
            textViewResultado.setText("Novo saldo com rendimento: " + contaPoupanca.getSaldo());
        } else {
            textViewResultado.setText("Conta Poupança não selecionada.");
        }
    }

    private void mostrarDados() {
        if (contaPoupanca != null) {
            textViewResultado.setText("Conta Poupança - Cliente: " + contaPoupanca.getCliente() + ", Número: " + contaPoupanca.getNumConta() + ", Saldo: " + contaPoupanca.getSaldo());
        } else if (contaEspecial != null) {
            textViewResultado.setText("Conta Especial - Cliente: " + contaEspecial.getCliente() + ", Número: " + contaEspecial.getNumConta() + ", Saldo: " + contaEspecial.getSaldo() + ", Limite: " + contaEspecial.getLimite());
        }
    }
}
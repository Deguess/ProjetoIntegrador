package guedes.gabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Relatorio extends AppCompatActivity {
    private ContaData cData;
    private EditText edtEscolha;
    private EditText edtEscolha2;
    private InformacoesConta infconta;
    private TextView txtValor2;
    private Button btnGerar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);



        txtValor2 = (TextView) findViewById(R.id.txtValor2);
        //abrir banco de dados
        cData = new ContaData(this);
        cData.abrirBanco();
        infconta = new InformacoesConta();


                txtValor2.setText(cData.somar(infconta));






    }
}
package guedes.gabriel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CadastroConta_Activity extends AppCompatActivity {

    private EditText edtID;
    private EditText edtTipo;
    private EditText edtNome;
    private EditText edtValor;
    private EditText edtData;
    private Button btnCadastrar;
    private Button btnAlterar;
    private InformacoesConta infconta;

    private ContaData cData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_conta_);

        edtID = (EditText) findViewById(R.id.edtID);
        edtTipo = (EditText) findViewById(R.id.edtTipo);
        edtNome = (EditText) findViewById(R.id.edtNome);
        edtValor = (EditText) findViewById(R.id.edtValor);
        edtData = (EditText) findViewById(R.id.edtData);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnAlterar = (Button) findViewById(R.id.btnAlterar);

        //Abrir banco
        cData = new ContaData(this);
        cData.abrirBanco();

        //Botão alterar invisível para cadastro
        btnAlterar.setVisibility(View.INVISIBLE);


        //Adicionar
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infconta = new InformacoesConta();
                infconta.setIdConta(Integer.parseInt(edtID.getText().toString()));
                infconta.setTipo(edtTipo.getText().toString());
                infconta.setNome(edtNome.getText().toString());
                infconta.setData(edtData.getText().toString());
                infconta.setValor(Double.parseDouble(edtValor.getText().toString()));

                Toast.makeText(
                        getBaseContext(),
                        "Conta cadastrada: " + infconta.toString(),
                        Toast.LENGTH_LONG).show();

                cData.inserir(infconta);
                Intent it = new Intent(CadastroConta_Activity.this, MainActivity.class);
                startActivity(it);
            }
        });

        String acao = getIntent().getStringExtra("acao");

        if(acao != null) {
            btnCadastrar.setVisibility(View.INVISIBLE);
            btnAlterar.setVisibility(View.VISIBLE);
            edtID.setEnabled(false);

            InformacoesConta infconta = (InformacoesConta) getIntent().getSerializableExtra("infconta");
            edtID.setText(String.valueOf(infconta.getIdConta()));
            edtTipo.setText(String.valueOf(infconta.getTipo()));
            edtNome.setText(String.valueOf(infconta.getNome()));
            edtData.setText(String.valueOf(infconta.getData()));
            edtValor.setText(String.valueOf(infconta.getValor()));
        }

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infconta = new InformacoesConta();
                infconta.setIdConta(Integer.parseInt(edtID.getText().toString()));
                infconta.setTipo(edtTipo.getText().toString());
                infconta.setNome(edtNome.getText().toString());
                infconta.setData(edtData.getText().toString());
                infconta.setValor(Double.parseDouble(edtValor.getText().toString()));

                Toast.makeText(
                        getBaseContext(),
                        "Conta alterada com sucesso!",
                        Toast.LENGTH_LONG).show();
                cData.alterar(infconta);

                Intent it = new Intent(CadastroConta_Activity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cData.abrirBanco();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cData.fecharBanco();
    }

    private void limpar() {
        edtID.setText(null);
        edtTipo.setText(null);
        edtNome.setText(null);
        edtData.setText(null);
        edtValor.setText(null);
    }
}
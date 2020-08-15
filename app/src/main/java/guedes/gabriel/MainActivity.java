package guedes.gabriel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    //Botão de cadastro e ListView
    private Button btnCadastro;
    private ListView lvContas;
    private Button btnGerar;

    //Banco
    private ContaData cData;

    private ArrayList<InformacoesConta> contasAux;
    private ContaAdapter adapter;

    //Guardar posição
    private int posSelec = -1;

    //Menu de contexto
    private static final int ALTERAR = 0;
    private static final int DELETAR = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        
        lvContas = (ListView) findViewById(R.id.lvContas);

        cData = new ContaData(this);
        cData.abrirBanco();
        contasAux = cData.consultar();

        adapter = new ContaAdapter(this, contasAux);

        lvContas.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        lvContas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                posSelec = position;
                return false;
            }
        });

        registerForContextMenu(lvContas);

        //Configuração do botão de cadastro
        btnCadastro = (Button) findViewById(R.id.btnCadastro);
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroConta_Activity.class);
                startActivity(it);
            }
        });
        //Fim da configuração de botão de cadastro

        //Configurar botão de Gerar Relatório
        btnGerar = (Button) findViewById(R.id.btnGerar);
        btnGerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Relatorio.class);
                startActivity(i);
            }
        });
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuinfo) {
        super.onCreateContextMenu(menu, v , menuinfo);

        menu.setHeaderTitle("Menu");
        menu.addSubMenu(DELETAR, DELETAR, 100, "Deletar");
        menu.addSubMenu(ALTERAR, ALTERAR, 200, "Alterar");
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case DELETAR:
                AlertDialog.Builder msg = new AlertDialog.Builder(MainActivity.this);
                msg.setTitle("Alerta");
                msg.setMessage("Você tem certeza que deseja excluir?");
                msg.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        InformacoesConta infconta = contasAux.get(posSelec);
                        cData.excluir(infconta);
                        contasAux.remove(infconta);

                        Toast.makeText(
                                getBaseContext(),
                                "Conta excluída com sucesso!",
                                Toast.LENGTH_LONG).show();;
                        adapter.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                    }
                });

                msg.setNeutralButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(
                                getBaseContext(),
                                "Conta mantida",
                                Toast.LENGTH_LONG).show();
                    }
                });
                msg.show();
                break;

            case ALTERAR:
                InformacoesConta infconta = contasAux.get(posSelec);
                Intent it = new Intent(MainActivity.this, CadastroConta_Activity.class);
                it.putExtra("infconta", infconta);
                it.putExtra("acao", "alterar");
                startActivity(it);
                finish();
                break;
        }
        return super.onContextItemSelected(item);
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



}

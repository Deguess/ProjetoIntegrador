package guedes.gabriel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContaAdapter extends BaseAdapter {
    private Context context;
    
    private ArrayList<InformacoesConta> listaContas;
    private LayoutInflater inflater;

    public TextView txtID;
    public TextView txtTipo;
    public TextView txtNome1;
    public TextView txtData1;
    public TextView txtValor1;
    
    public ContaAdapter(Context context, ArrayList<InformacoesConta> listaContas) {
        this.context = context;
        this.listaContas = listaContas;
        inflater = LayoutInflater.from(context);
    }
    //Fim do Construtor
    
    @Override
    public void notifyDataSetChanged() {
        try {
            super.notifyDataSetChanged();
        } catch (Exception e) {
            trace("Erro: " + e.getMessage());
        }
    } 
    //Fim do Notify
    
    //Envios de mensagem
    public void toast (String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    private void trace (String msg) {
        toast(msg);
    }
    public void add(InformacoesConta infconta) {
        listaContas.add(infconta);
    }
    public void remove(InformacoesConta infconta) {
        listaContas.remove(infconta);
    }
    
    @Override
    public int getCount() {
        return listaContas.size();
    }
    
    @Override
    public InformacoesConta getItem(int position) {
        return listaContas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            InformacoesConta infconta = listaContas.get(position);
            
            if(convertView==null) {
                convertView = inflater.inflate(R.layout.activity_conta_adapter, null);
            }
            //Instancia de objetos
            txtID = (TextView) convertView.findViewById(R.id.txtID1);
            txtTipo = (TextView) convertView.findViewById(R.id.txtTipo);
            txtNome1 = (TextView) convertView.findViewById(R.id.txtNome1);
            txtData1 = (TextView) convertView.findViewById(R.id.txtData1);
            txtValor1 = (TextView) convertView.findViewById(R.id.txtValor1);
            
            //Set de linha
            txtID.setText("Identificação de conta: " + infconta.getIdConta());
            txtTipo.setText("Tipo de conta: " + infconta.getTipo());
            txtNome1.setText("Nome: " + infconta.getNome());
            txtData1.setText("Data: " + infconta.getData());
            txtValor1.setText("Valor: " + infconta.getValor());
            
            return convertView;
        } catch (Exception e) {
            trace("Erro: " + e.getMessage());
        }
        return convertView;
    }
}
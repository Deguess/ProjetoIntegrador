package guedes.gabriel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ContaData {
    private SQLiteDatabase database;
    private BaseData dbHelper;
    private InformacoesConta infconta;

    public ContaData(Context context) {
        dbHelper = new BaseData(context);
    }

    public void abrirBanco() {
        database = dbHelper.getWritableDatabase();
    }

    public void fecharBanco() {
        dbHelper.close();
    }

    public long inserir(InformacoesConta infconta) {
        ContentValues cv = new ContentValues();

        cv.put(BaseData.ID_CONTA, infconta.getIdConta());
        cv.put(BaseData.TIPO_CONTA, infconta.getTipo());
        cv.put(BaseData.NOME_CONTA, infconta.getNome());
        cv.put(BaseData.DATA_CONTA, infconta.getData());
        cv.put(BaseData.VALOR_CONTA, infconta.getValor());

        return database.insert(BaseData.TBL_CONTA, null, cv);
    }

    //Alterar
    public int alterar(InformacoesConta infconta) {
        int id = infconta.getIdConta();

        ContentValues cv = new ContentValues();
        cv.put(BaseData.ID_CONTA, infconta.getIdConta());
        cv.put(BaseData.TIPO_CONTA, infconta.getTipo());
        cv.put(BaseData.NOME_CONTA, infconta.getNome());
        cv.put(BaseData.DATA_CONTA, infconta.getData());
        cv.put(BaseData.VALOR_CONTA, infconta.getValor());

        return database.update(
                BaseData.TBL_CONTA,
                cv,
                BaseData.ID_CONTA + "=?",
                new String[]{String.valueOf(id)});
    }

    public String somar(InformacoesConta infconta) {
        infconta = new InformacoesConta();
        double total = infconta.getValor();
        ArrayList<InformacoesConta> contasSoma = new ArrayList<>();

        Cursor cur = database.rawQuery("SELECT sum(valor) FROM conta ", null);
        if (cur.moveToFirst())
            total = cur.getDouble(0);
        cur.close();
        return String.valueOf(total);
    }

    //Excluir
    public int excluir(InformacoesConta infconta) {
        int id = infconta.getIdConta();

        return database.delete(
                BaseData.TBL_CONTA,
                BaseData.ID_CONTA + "=?",
                new String[]{String.valueOf(id)});
    }

    public ArrayList<InformacoesConta> consultar() {
        ArrayList<InformacoesConta> contasAux = new ArrayList<>();

        Cursor cursor = database.query(
                BaseData.TBL_CONTA,
                BaseData.TBL_CONTA_COLUNAS,
                null,
                null, null, null,
                BaseData.NOME_CONTA);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            InformacoesConta infconta = new InformacoesConta();
            infconta.setIdConta(cursor.getInt(0));
            infconta.setTipo(cursor.getString(1));
            infconta.setNome(cursor.getString(2));
            infconta.setData(cursor.getString(3));
            infconta.setValor(cursor.getDouble(4));
            cursor.moveToNext();
            contasAux.add(infconta);
        }

        cursor.close();
        return contasAux;
    }

}



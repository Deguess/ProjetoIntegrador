package guedes.gabriel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseData extends SQLiteOpenHelper {
    //Tabela
    public static final String TBL_CONTA = "Conta";
    public static final String ID_CONTA = "idConta";
    public static final String TIPO_CONTA = "Tipo";
    public static final String NOME_CONTA = "Nome";
    public static final String DATA_CONTA = "Data";
    public static final String VALOR_CONTA = "Valor";

    //Colunas da tabela
    public static final String[] TBL_CONTA_COLUNAS = {
    BaseData.ID_CONTA,
    BaseData.TIPO_CONTA,
    BaseData.NOME_CONTA,
    BaseData.DATA_CONTA,
    BaseData.VALOR_CONTA};

    //Nome e Versão
    public static final String DATABASE_NAME = "contas.sqlite";
    public static final int DATABASE_VERSION = 1;

    //DDL - Criação de tabelas
    public static final String CREATE_CONTA =
            " create table " + TBL_CONTA + "(" +
                    ID_CONTA + " integer primary key," +
                    TIPO_CONTA + " text not null," +
                    NOME_CONTA + " text not null," +
                    DATA_CONTA + " date not null," +
                    VALOR_CONTA + " double not null);";

    //DDL - Exclusão da tabela
    public static final String DROP_CONTAS =
            " drop table if exists " + TBL_CONTA;

    public BaseData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CONTAS);
        onCreate(db);
    }
}

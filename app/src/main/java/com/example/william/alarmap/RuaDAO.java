package com.example.william.alarmap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class RuaDAO extends SQLiteOpenHelper {

    public RuaDAO(Context context){
        super(context, "Rua", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE Ruas (id INTEGER PRIMARY KEY, endereco TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Rua";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(Rua rua){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDaRua(rua);
        db.insert("Ruas", null, dados);
    }

    private ContentValues pegaDadosDaRua(Rua rua) {

        ContentValues dados = new ContentValues();
        dados.put("endereco", rua.getEndereco());
        return dados;
    }

    public List<Rua> buscaRuas() {
        String sql = "SELECT * FROM Ruas";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Rua> ruas = new ArrayList<Rua>();
        while(c.moveToNext()){
            Rua rua = new Rua();
            rua.setId(c.getLong(c.getColumnIndex("id")));
            rua.setEndereco(c.getString(c.getColumnIndex("endereco")));

            ruas.add(rua);
        }
        c.close();
        return ruas;
    }

    public void deleta(Rua rua){
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {rua.getId().toString()};
        db.delete("Ruas", "id = ?", params);
    }

    public void limpar(){
        List<Rua> ruas = buscaRuas();
        for(Rua rua : ruas){
            deleta(rua);
        }

    }


}

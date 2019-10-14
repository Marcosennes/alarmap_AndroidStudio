package com.example.william.alarmap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


public class MapaActivity extends AppCompatActivity  {
    private ListView listaRuas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        listaRuas = findViewById(R.id.lista_ruas);
    }

    private void carregaLista(){
        RuaDAO dao = new RuaDAO(this);
        List<Rua>  ruas = dao.buscaRuas();
        dao.close();

        ArrayAdapter<Rua> adapter = new ArrayAdapter<Rua>(this, android.R.layout.simple_list_item_1, ruas);
        listaRuas.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.apagar_lista:
                RuaDAO dao = new RuaDAO(this);
                dao.limpar();
                carregaLista();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

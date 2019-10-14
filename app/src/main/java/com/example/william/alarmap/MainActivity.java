package com.example.william.alarmap;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST= 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isServiceOK()){
            init();
        }
    }
    private void init(){
        Button btnmapa = findViewById(R.id.BtnMapa);
        Button btncalendario = findViewById(R.id.BtnCalendario);
        btnmapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        btncalendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, calendar.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_maps,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_maps:
                Intent volta = new Intent(MainActivity.this, MapaActivity.class);
                startActivity(volta);
                break;

            case R.id.menu_comp:
                Intent compartilhe= new Intent(Intent.ACTION_SEND);
                compartilhe.setType("text/plain");
                compartilhe.putExtra(Intent.EXTRA_SUBJECT,"My_App");
                compartilhe.putExtra(Intent.EXTRA_TEXT,"Cansado de acordar somente no ponto final? Resolvemos seu problema!");
                startActivity(Intent.createChooser(compartilhe,"Compartilhar"));
                break;

            case R.id.menu_vibrate:
                Intent vibrar = new Intent(MainActivity.this, Vibrate.class );
                startActivity(vibrar);
                break;

            case R.id.menu_toque:
                Intent escolhaToque = new Intent(MainActivity.this, EscolhaToque.class);
                startActivity(escolhaToque);
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isServiceOK(){
        Log.d(TAG, "isServiceOK: checking google service version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            Log.d(TAG, "isServiceOk: google play service esta funcionando");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG," isServiceOk: erro ocorrido");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else{
            Toast.makeText(this, "Você nao pode fazer requisicao de mapas ", Toast.LENGTH_SHORT).show();
        }
        return false;

    }
}

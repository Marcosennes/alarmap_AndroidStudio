package com.example.william.alarmap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import static com.example.william.alarmap.EscolhaToque.inicializouToqueId;
import static com.example.william.alarmap.EscolhaToque.toqueId;
import static com.example.william.alarmap.Vibrate.checked;

public class TocadorAlarme extends AppCompatActivity {

    private MediaPlayer toqueEscolhido;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checked) {
            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(5000);
        }

        //se o usuário não escolheu explícitamente o toque na radio group, a variável inicializouToqueId é false,
        //e o toque 1 é definido por padrão. Isso é necessário, pois o app crasha quando o usuário fecha o programa à força
        //durante o toque do alarme e depois o inicia de novo sem escolher um toque
        if(!inicializouToqueId){
            toqueId = R.raw.toque1;
        }

        toqueEscolhido = MediaPlayer.create(TocadorAlarme.this, toqueId);
        toqueEscolhido.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                toqueEscolhido.start();
            }
        });
        toqueEscolhido.start();

        AlertDialog.Builder mensagemDespertador = new AlertDialog.Builder(TocadorAlarme.this);
        mensagemDespertador.setCancelable(false);
        mensagemDespertador.setMessage(getString(R.string.mensagem_despertador));
        mensagemDespertador.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                toqueEscolhido.stop();
                toqueEscolhido.release();
                toqueEscolhido = null;
                finish();
            }
        });
        mensagemDespertador.show();
    }

}
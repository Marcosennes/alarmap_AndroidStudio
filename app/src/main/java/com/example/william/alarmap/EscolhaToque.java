package com.example.william.alarmap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EscolhaToque extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton toque1;
    private RadioButton toque2;
    private RadioButton toque3;
    private Button botaoEscolha;
    private String text;
    public static int toqueId;
    public static boolean inicializouToqueId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha_toque);

        radioGroup = (RadioGroup) findViewById(R.id.id_toque_radio);
        toque1 = (RadioButton) findViewById(R.id.toque_1);
        toque2 = (RadioButton) findViewById(R.id.toque_2);
        toque3 = (RadioButton) findViewById(R.id.toque_3);
        botaoEscolha = (Button) findViewById(R.id.botao_escolha);

        toqueId = R.raw.toque1;
        text = toque1.getText().toString();
        inicializouToqueId = true;

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.toque_1) {
                    text = toque1.getText().toString();
                    toqueId = R.raw.toque1;
                } else if (checkedId == R.id.toque_2) {
                    text = toque2.getText().toString();
                    toqueId = R.raw.toque2;
                } else if (checkedId == R.id.toque_3) {
                    text = toque3.getText().toString();
                    toqueId = R.raw.toque3;                }
            };
        });

        botaoEscolha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EscolhaToque.this, text + " escolhido", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
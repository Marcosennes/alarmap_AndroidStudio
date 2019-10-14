package com.example.william.alarmap;

import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Vibrate extends AppCompatActivity {
    Button b_vibrate;
    Vibrator vibrator;
    public static boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrate);

        b_vibrate = (Button) findViewById(R.id.b_vibrate);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        b_vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checked = ((CheckBox) v).isChecked();

                if(checked) {
                    vibrator.vibrate(150);
                }
            }
        });


    }
}

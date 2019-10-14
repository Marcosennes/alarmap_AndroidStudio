package com.example.william.alarmap;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class calendar extends AppCompatActivity {
    CalendarView calendarView;
    TextView dataSelecionada;
    private int i2b, i1b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        Button btnagendar = findViewById(R.id.btnAgendar);
        btnagendar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                notificationCall();
            }
        });

        calendarView= (CalendarView) findViewById(R.id.calendarView);
        dataSelecionada= (TextView) findViewById(R.id.dataSelecionada);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                i2b=i2;
                i1b=i1;
                String date= i2 + "/" + (i1+1) + "/" + i;
                dataSelecionada.setText(date);
            }
        });
    }

    public void notificationCall(){
        NotificationCompat.Builder notificationBuilder =  (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.icone_search)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icone_search))
                .setContentTitle("Alarmap")
                .setContentText("Alarme agendado: "+ i2b +"/" + (i1b+1));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notificationBuilder.build());
    }
}




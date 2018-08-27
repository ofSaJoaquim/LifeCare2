package com.example.casa.lifecare;

import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CadastroRemedio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_remedio);
        Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.lifecare)
                        .setContentTitle("Hora de tomar rémedio")
                        .setContentText("Gardenau")
                        .setAutoCancel(true)
                       .setSound(som)
                        .setVibrate(new long[]{ 100, 250, 100, 500 })
                        .setLights(100, 500, 100);;


        final int mNotificationId = 001;

        final NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        final Button proximo = (Button) findViewById(R.id.button4);


        proximo.setOnClickListener(new  View.OnClickListener(){
            @Override
            public  void  onClick(View v){
               /* Toast.makeText(Formulario_Primeiro.this, "Hello World",
                        Toast.LENGTH_LONG).show();*/
                mostrar(mNotifyMgr,mNotificationId,mBuilder);

            }
        } );


    }
    public void mostrar(NotificationManager n, int id,  NotificationCompat.Builder builder){
        n.notify(id, builder.build());
    }
}

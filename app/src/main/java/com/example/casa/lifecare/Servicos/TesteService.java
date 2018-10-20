package com.example.casa.lifecare.Servicos;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import com.example.casa.lifecare.Formulario_segundo;
import com.example.casa.lifecare.R;
import com.example.casa.lifecare.Tela_login;
import com.example.casa.lifecare.TesteNofiticacao;
import com.example.casa.lifecare.entidades.Paciente;
import com.example.casa.lifecare.utils.TesteMeusRemedios;
import com.example.casa.lifecare.utils.TesteRemedios;

public class TesteService extends Service {
 static   public Context context;
    NotificationManager notify;
    public TesteService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Script", "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Script", "onStarCommand");
        TesteMeusRemedios testeMeusRemedios = new TesteMeusRemedios();
        testeMeusRemedios.AdicionarRemedioparaTeste();

       Worker w = new Worker(startId,testeMeusRemedios);
        w.start();

        return super.onStartCommand(intent, flags, startId);
    }
    public void criarNotificacaoSimples(int id){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.lifecare)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, Tela_login.class);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(Tela_login.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
        mNotificationManager.notify(id, mBuilder.build());
    }


     class Worker extends Thread{

        public int count =0;
        public int startId;
        public boolean ativo =true;
        public TesteMeusRemedios testeMeusRemedios;
        public Worker(int StartId, TesteMeusRemedios testeMeusRemedios){
            this.startId=startId;
            this.testeMeusRemedios=testeMeusRemedios;
        }
        public void run(){
            while(ativo==true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int count=0;count<testeMeusRemedios.testeMeusremedios.size();count++) {
                    TesteRemedios remedio = testeMeusRemedios.testeMeusremedios.get(count);
                    if((remedio.getUltimaAplicacao()+remedio.getIntervalo())<=System.currentTimeMillis()) {
                        remedio.setUltimaAplicacao(System.currentTimeMillis()*200);
                        criarNotificacaoSimples(count);
                        //TesteNofiticacao.notify(context, remedio.getDescricao(), count);


                        Log.i("Script", "COUNT: " + count);
                    }
                }
            }
            stopSelf(startId);
        }


    }
}
package com.example.casa.lifecare.Servicos;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.widget.Toast;

import com.example.casa.lifecare.Formulario_segundo;
import com.example.casa.lifecare.ListaMeusRemedios;
import com.example.casa.lifecare.Principal;
import com.example.casa.lifecare.R;
import com.example.casa.lifecare.TelaChat;
import com.example.casa.lifecare.Tela_login;
import com.example.casa.lifecare.TesteNofiticacao;
import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Medicamento;
import com.example.casa.lifecare.entidades.Paciente;
import com.example.casa.lifecare.entidades.Risco;
import com.example.casa.lifecare.utils.TesteMeusRemedios;
import com.example.casa.lifecare.utils.TesteRemedios;

import java.util.ArrayList;
import java.util.List;

public class TesteService extends Service {
 static   public Context context;
 static public boolean logado=false;
 static public boolean comoEsta=true;
 static public boolean comoEstaRestart=false;
    static public boolean ativo =false;
    static int ultimoCheckMenssagem=0;
 static public Paciente paciente;
  public static List<Medicamento> medicamentos=new ArrayList<Medicamento>();
    public int ultimaHoraFormD=0;

    NotificationManager notify;
     Uri alarmSound =  RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
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
    public void criarNotificacaoSimples(int id,String titulo,String corpo){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.lifecare)
                        .setContentTitle(titulo)
                        .setContentText(corpo)
        .setVibrate(new long[]{ 100, 250, 100, 500, 800})
        .setSound(alarmSound);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent;
        if(titulo.equalsIgnoreCase("Nova menssagem?")){
            resultIntent = new Intent(this, TelaChat.class);
        }
        else if(id>0){
             resultIntent = new Intent(this, ListaMeusRemedios.class);
        }

        else {
             resultIntent = new Intent(this, Principal.class);
        }

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
        public int minutos=0;
        public int segundos=0;
        public int horas=0;


        public TesteMeusRemedios testeMeusRemedios;
        public Worker(int StartId, TesteMeusRemedios testeMeusRemedios){
            this.startId=startId;
            this.testeMeusRemedios=testeMeusRemedios;
        }
        public void run(){
            logado=true;
            while(ativo==true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int count = 1;
                if (segundos < 59) segundos++;
                else {
                    segundos = 0;
                    if (minutos < 59) minutos++;
                    else {
                        minutos = 0;
                        if (horas < 23) horas++;
                        else horas = 0;
                    }
                }
                if (ativo == false) break;
                paciente = Auxiliar.prontuario.getPaciente();
                logado = true;
                if (segundos == 59) {
                    for (Medicamento medicamento : medicamentos) {
                        if (!medicamento.getAguardaUso()) {
                            if (medicamento.proximaDose() <= 0) {
                                count++;
                                medicamento.setAguardaUso(true);
                                if (ativo == false) break;
                                criarNotificacaoSimples(count, medicamento.getNome(), " Hora: " + medicamento.horaDose());

                                //TesteNofiticacao.notify(context, remedio.getDescricao(), count);


                                Log.i("Script", "COUNT: " + count);
                            }
                        }
                        if (ativo == false) break;
                        else if (medicamento.proximaDose() <= 0) {
                            if (medicamento.getReavisos() < medicamento.getMaxReaviso()) {
                                if (medicamento.getReaviso() > medicamento.getMuliplica()) {
                                    count++;
                                    medicamento.setReavisos(medicamento.getReavisos() + 1);
                                    medicamento.setReaviso(0);
                                    criarNotificacaoSimples(count, medicamento.getNome(), "Não esqueça seu remédio é muinto importante!!!  Hora: " + medicamento.horaDose());
                                    Auxiliar.adicionarRiscos(new Risco(medicamento.getNome(), count, "Remedio_Atrasado"));
                                } else medicamento.setReaviso(medicamento.getReaviso() + 1);
                                if (ativo == false) break;
                            } else if (medicamento.getReavisos() == medicamento.getMaxReaviso()) {
                                count++;
                                criarNotificacaoSimples(count, medicamento.getNome(), "Que pena esqueceu seu remédio!!!  Hora: " + medicamento.horaDose());
                                medicamento.setReavisos(medicamento.getReavisos() + 1);
                                Auxiliar.adicionarRiscos(new Risco(medicamento.getNome(), count, "Remedio_Nao_Aplicado"));

                            }
                        }
                        //  Log.i("Script", medicamento.getNome()+"   Reavisos: " + medicamento.getReavisos());
                        //  Log.i("Script", medicamento.getNome()+"   Reaviso: " + medicamento.getReaviso());

                    }
                }
                if (ativo == false) break;
                if (!comoEstaRestart) {
                    if (horas == ultimaHoraFormD) {
                        comoEsta = false;
                        comoEstaRestart = true;
                        criarNotificacaoSimples(0, "Como você está agora?", "Clique aqui vamos falar sobre um pouco de você!");


                    }
                } else if (horas != ultimaHoraFormD) comoEstaRestart = false;

                if (minutos == 2) {
                    if (segundos == 0) {
                        int teste = Auxiliar.mensagems.size();
                        Auxiliar.carregarMenssagens();
                        int teste2 = Auxiliar.mensagems.size();
                        if (teste < teste2) {
                            if (Auxiliar.mensagems.get(teste2 - 1).getMedicoId() != null) {

                                criarNotificacaoSimples(count, "Nova menssagem?", Auxiliar.mensagems.get(teste2 - 1).getTexto());
                                count++;
                            }
                        }
                    }
                }
            }
            stopSelf(startId);
        }


    }

}

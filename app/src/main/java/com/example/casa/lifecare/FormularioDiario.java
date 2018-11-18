package com.example.casa.lifecare;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.casa.lifecare.Servicos.WebService;
import com.example.casa.lifecare.adptador.AdaptadorRiscos;
import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Risco;

import java.util.ArrayList;
import java.util.List;

public class FormularioDiario extends AppCompatActivity {
private int chancesInfarto;
private int chancesAvc;
private List<Risco> riscos;
    ProgressDialog load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_diario);
        chancesAvc=0;
        chancesInfarto=0;
        riscos=new ArrayList<Risco>();


       findViewById(R.id.botaoFormularioDiario).setOnClickListener(new  View.OnClickListener(){
            @Override
            public  void  onClick(View v){
                if(((CheckBox)findViewById(R.id.checkDDorNoPeito)).isChecked()){
                    Log.i("Check1","true");
                    chancesInfarto++;
                    riscos.add(new Risco("Dor no Peito",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDCansaco)).isChecked()){
                    chancesInfarto++;
                    riscos.add(new Risco("Cansaço",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDEnjoos)).isChecked()){
                    chancesInfarto++;
                    riscos.add(new Risco("Enjoos",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDAr)).isChecked()){
                    chancesInfarto++;
                    riscos.add(new Risco("Falta de Ar",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDTontutras)).isChecked()){
                    chancesInfarto++;
                    chancesAvc++;
                    riscos.add(new Risco("Tonturas",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDSuorFrio)).isChecked()){
                    chancesInfarto++;
                    riscos.add(new Risco("Suor frio",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDParelisia)).isChecked()){
                    chancesAvc++;
                    riscos.add(new Risco("Paralisia",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDVisaoDupla)).isChecked()){
                    chancesAvc++;
                    riscos.add(new Risco("Visão embassada ou dupla",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDPerdaVisao)).isChecked()){
                    chancesAvc++;
                    riscos.add(new Risco("Perda de visão momentânea",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDFala)).isChecked()){
                    chancesAvc++;
                    riscos.add(new Risco("Dificuldade na fala",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDConfusao)).isChecked()){
                    chancesAvc++;
                    riscos.add(new Risco("Confusão Mental",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDDoresCabeça)).isChecked()){
                    chancesAvc++;
                    riscos.add(new Risco("Dores de cabeça constantes",1,"Diario"));
                }

                if(((CheckBox)findViewById(R.id.checkDFaltaApetite)).isChecked()){

                    riscos.add(new Risco("Falta de apetite",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDDesanimo)).isChecked()){

                    riscos.add(new Risco("Desânimo",1,"Diario"));
                }
                if(((CheckBox)findViewById(R.id.checkDInsonia)).isChecked()){

                    riscos.add(new Risco("Insônia",1,"Diario"));
                }
                if(chancesInfarto>=3)riscos.add(new Risco("Possivel Infarto",1000,"Diario"));
                if(chancesAvc>=3)riscos.add(new Risco("Possivel AVC",1000,"Diario"));
              AdicionarRiscosDiarios ar = new AdicionarRiscosDiarios();
              ar.execute();


            }
        } );
    }

    private void infarto(){
        new AlertDialog.Builder(this).setTitle("Fique calmo!").
                setMessage("Você pode esta num inicio de um infarto, procure um médico imediatamente ou ligue 192 ou 193")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(FormularioDiario.this, Principal.class);
                        startActivity(intent);
                    }
                })


                .show();

    }

    private void AVC(){
        new AlertDialog.Builder(this).setTitle("Fique calmo!").
                setMessage("Você pode esta num inicio de um AVC, procure um médico imediatamente ou ligue 192 ou 193")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(FormularioDiario.this, Principal.class);
                        startActivity(intent);
                    }
                })


                .show();

    }

    private  class AdicionarRiscosDiarios extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(FormularioDiario.this, "Por favor Aguarde ...",
                    "Salvando dados no servidor ...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Integer doInBackground(String... params) {
            int retorno=0;
            try {
                Auxiliar.adicionarRiscos(riscos);
                Auxiliar.carregarProntuario();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return 201;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {

            load.dismiss();
            if(retornoHTTP==201){
                Toast toast = Toast.makeText(FormularioDiario.this, "Dados salvos com sucesso!!",Toast.LENGTH_LONG);
                toast.show();
                if(chancesInfarto>=3)infarto();
                else if(chancesAvc>=3)AVC();

                else{
                    Intent intent = new Intent(FormularioDiario.this, Principal.class);
                    startActivity(intent);
                }


            }
            else{
                Toast toast = Toast.makeText(FormularioDiario.this, "Não foi possivel salvar os dados, tente mais tarde. \n Erro: "+retornoHTTP,Toast.LENGTH_LONG);
                toast.show();
            }

        }
    }


}

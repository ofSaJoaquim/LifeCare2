package com.example.casa.lifecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.casa.lifecare.Servicos.TesteService;
import com.example.casa.lifecare.adptador.AdaptadorLinhaCuidados;
import com.example.casa.lifecare.adptador.AdaptadorRiscos;
import com.example.casa.lifecare.adptador.AdptadorMeusRemedios;
import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Risco;

import java.util.ArrayList;
import java.util.List;

public class TelaProntuario extends AppCompatActivity {
    AdptadorMeusRemedios adptadorMeusRemedios;
    AdaptadorLinhaCuidados adaptadorLinhaCuidados;
    AdaptadorRiscos adaptadorRiscos;
    RecyclerView recyclerView;
    RecyclerView reciclerLinha;
    RecyclerView reciclerRiscos;
    ProgressDialog load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_prontuario);
        CarregaProntiario cp = new CarregaProntiario();
        cp.execute();
    }


    private class CarregaProntiario extends AsyncTask<String, Void, Integer>{
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(TelaProntuario.this, "Por favor Aguarde ...",
                    "Conectando no servidor ...");
        }
        @Override
        protected Integer doInBackground(String... strings) {
           if(Auxiliar.carregarProntuario()){
Auxiliar.prontuario.setScore(Principal.calcularRisco());
               return 1;

           }
           else return 0;

        }
        protected void onPostExecute(Integer retorno) {

            if(Auxiliar.prontuario.getMedicamentos().size()>=0){
             if(TesteService.medicamentos.size()!=Auxiliar.prontuario.getMedicamentos().size())  TesteService.medicamentos=Auxiliar.prontuario.getMedicamentos();
            recyclerView = (RecyclerView) findViewById(R.id.recicleRemediosProntuario);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(TelaProntuario.this, LinearLayout.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            adptadorMeusRemedios = new AdptadorMeusRemedios(TesteService.medicamentos, TelaProntuario.this);
            recyclerView.setAdapter(adptadorMeusRemedios);}

            if(Auxiliar.prontuario.getLinhasDeCuidado().size()>0){
            reciclerLinha = (RecyclerView) findViewById(R.id.recicleLinhaProntuario);
           LinearLayoutManager mLayoutManager2 = new LinearLayoutManager(TelaProntuario.this, LinearLayout.VERTICAL, false);
            reciclerLinha.setLayoutManager(mLayoutManager2);
             adaptadorLinhaCuidados= new AdaptadorLinhaCuidados(Auxiliar.prontuario.getLinhasDeCuidado(), TelaProntuario.this);
            reciclerLinha.setAdapter(adaptadorLinhaCuidados);}
            List<Risco> riscos = new ArrayList<Risco>();
            for(Risco risco: Auxiliar.prontuario.getRiscos()){
                if(risco.getIntensidade()>0)
               riscos.add(risco);
            }
            if(Auxiliar.prontuario.getRiscos().size()>0){
            reciclerRiscos = (RecyclerView) findViewById(R.id.reciclerRiscosProntuario);
            LinearLayoutManager mLayoutManager3 = new LinearLayoutManager(TelaProntuario.this, LinearLayout.VERTICAL, false);
            reciclerRiscos.setLayoutManager(mLayoutManager3);
            adaptadorRiscos= new AdaptadorRiscos(riscos, TelaProntuario.this);
            reciclerRiscos.setAdapter(adaptadorRiscos);}


            if(Auxiliar.prontuario.getPaciente()==null)voltarForm();
            else ((TextView)findViewById(R.id.textPaciente)).setText(Auxiliar.prontuario.getPaciente().getNome());
            if(Auxiliar.prontuario.getScore()==null) ((TextView)findViewById(R.id.textRisco)).setText("Não Calculado");
            else ((TextView)findViewById(R.id.textRisco)).setText(Auxiliar.prontuario.getScore().toString());
            if(Auxiliar.prontuario.getPaciente().getIdade()==null)((TextView)findViewById(R.id.textIdade)).setText("Idade?");
            else ((TextView)findViewById(R.id.textIdade)).setText(Auxiliar.prontuario.getPaciente().getIdade().toString());
            if(Auxiliar.prontuario.getPaciente().getMedico()==null)voltarForm();
            else ((TextView)findViewById(R.id.textMedico)).setText(Auxiliar.prontuario.getPaciente().getMedico().getNome());
           if(Auxiliar.prontuario.getPaciente().getMedico().getEspecialidades()==null)((TextView)findViewById(R.id.textEspecialidade)).setText("Clínico");
           else if(Auxiliar.prontuario.getPaciente().getMedico().getEspecialidades().size()<=0)((TextView)findViewById(R.id.textEspecialidade)).setText("Clínico");
               else((TextView)findViewById(R.id.textEspecialidade)).setText(Auxiliar.prontuario.getPaciente().getMedico().getEspecialidades().get(0).toString());
            load.dismiss();
        }
    }
    public void voltarForm(){
        Intent intent = new Intent(TelaProntuario.this, Principal.class);
        startActivity(intent);
    }
}

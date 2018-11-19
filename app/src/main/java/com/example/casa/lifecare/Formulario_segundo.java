package com.example.casa.lifecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Cidade;
import com.example.casa.lifecare.entidades.Risco;

import java.util.ArrayList;
import java.util.List;

public class Formulario_segundo extends AppCompatActivity {
    ProgressDialog load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_segundo);
        final Button proximo =  findViewById(R.id.botaoFormulario);
        proximo.setOnClickListener(new  View.OnClickListener(){
            @Override
            public  void  onClick(View v){
                Toast.makeText(Formulario_segundo.this, "Parabéns Formulário Cadastrado com sucesso",
                        Toast.LENGTH_LONG).show();
                proximoFormulario();


            }
        } );
    }
    public void proximoFormulario() {
        List<Risco> riscos = Auxiliar.prontuario.getRiscos();

        if (((CheckBox) findViewById(R.id.radioDiabetesAvo)).isChecked())
            riscos.add(new Risco("DIABETES_AVO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DIABETES_AVO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDiabetesVo)).isChecked())
            riscos.add(new Risco("DIABETES_VO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DIABETES_VO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDiabetesMae)).isChecked())
            riscos.add(new Risco("DIABETES_MAE", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DIABETES_MAE", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDiabetesPai)).isChecked())
            riscos.add(new Risco("DIABETES_PAI", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DIABETES_PAI", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDiabetesIrma)).isChecked())
            riscos.add(new Risco("DIABETES_IRMA", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DIABETES_IRMA", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDiabetesIrmao)).isChecked())
            riscos.add(new Risco("DIABETES_IRMAO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DIABETES_IRMAO", 0, "NAO_MODIFICAVEL"));


        //HIPERTENSAO
        if (((CheckBox) findViewById(R.id.radioHipertencaoAvo)).isChecked())
            riscos.add(new Risco("HIPERTENSAO_AVO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("HIPERTENSAO_AVO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioHipertencaoVo)).isChecked())
            riscos.add(new Risco("HIPERTENSAO_VO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("HIPERTENSAO_VO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioHipertencaoMae)).isChecked())
            riscos.add(new Risco("HIPERTENSAO_MAE", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("HIPERTENSAO_MAE", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioHipertencaoPai)).isChecked())
            riscos.add(new Risco("HIPERTENSAO_MAE", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("HIPERTENSAO_MAE", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioHipertencaoIrma)).isChecked())
            riscos.add(new Risco("HIPERTENSAO_IRMA", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("HIPERTENSAO_IRMA", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioHipertencaoIrmao)).isChecked())
            riscos.add(new Risco("HIPERTENSAO_IRMAO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("HIPERTENSAO_IRMAO", 0, "NAO_MODIFICAVEL"));


        //cardiacas
        if (((CheckBox) findViewById(R.id.radioCardiacoAvo)).isChecked())
            riscos.add(new Risco("CARDIACO_AVO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("CARDIACO_AVO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioCardiacoVo)).isChecked())
            riscos.add(new Risco("CARDIACO_VO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("CARDIACO_VO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioCardiacoMae)).isChecked())
            riscos.add(new Risco("CARDIACO_MAE", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("CARDIACO_MAE", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioCardiacoPai)).isChecked())
            riscos.add(new Risco("CARDIACO_PAI", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("CARDIACO_PAI", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioCardiacoIrma)).isChecked())
            riscos.add(new Risco("CARDIACO_IRMA", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("CARDIACO_IRMA", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioCardiacoIrmao)).isChecked())
            riscos.add(new Risco("CARDIACO_IRMAO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("CARDIACO_IRMAO", 0, "NAO_MODIFICAVEL"));


        //neuro

        if (((CheckBox) findViewById(R.id.radioNeuroAvo)).isChecked())
            riscos.add(new Risco("NEUROLOGIA_AVO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("NEUROLOGIA_AVO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioNeuroVo)).isChecked())
            riscos.add(new Risco("NEUROLOGIA_VO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("NEUROLOGIA_VO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioNeuroMae)).isChecked())
            riscos.add(new Risco("NEUROLOGIA_MAE", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("NEUROLOGIA_MAE", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioNeuroPai)).isChecked())
            riscos.add(new Risco("NEUROLOGIA_PAI", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("NEUROLOGIA_PAI", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioNeuroIrma)).isChecked())
            riscos.add(new Risco("NEUROLOGIA_IRMA", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("NEUROLOGIA_IRMA", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioNeuroIrmao)).isChecked())
            riscos.add(new Risco("NEUROLOGIA_IRMAO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("NEUROLOGIA_IRMAO", 0, "NAO_MODIFICAVEL"));


        //renal

        if (((CheckBox) findViewById(R.id.radioRenalAvo)).isChecked())
            riscos.add(new Risco("RENAL_AVO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("RENAL_AVO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioRenalVo)).isChecked())
            riscos.add(new Risco("RENAL_VO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("RENAL_VO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioRenalMae)).isChecked())
            riscos.add(new Risco("RENAL_MAE", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("RENAL_MAE", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioRenalPai)).isChecked())
            riscos.add(new Risco("RENAL_PAI", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("RENAL_PAI", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioNRenalIrma)).isChecked())
            riscos.add(new Risco("RENAL_IRMA", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("RENAL_IRMA", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioRenalrmao)).isChecked())
            riscos.add(new Risco("RENAL_IRMAO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("RENAL_IRMAO", 0, "NAO_MODIFICAVEL"));


        //dst

        if (((CheckBox) findViewById(R.id.radioDSTAvo)).isChecked())
            riscos.add(new Risco("DST_AVO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DST_AVO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDSTVo)).isChecked())
            riscos.add(new Risco("DST_VO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DST_VO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDSTMae)).isChecked())
            riscos.add(new Risco("DST_MAE", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DST_MAE", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDSTPai)).isChecked())
            riscos.add(new Risco("DST_PAI", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DST_PAI", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDSTIrma)).isChecked())
            riscos.add(new Risco("DST_IRMA", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DST_IRMA", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioDSTrmao)).isChecked())
            riscos.add(new Risco("DST_IRMAO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("DST_IRMAO", 0, "NAO_MODIFICAVEL"));


        //obesidade

        if (((CheckBox) findViewById(R.id.radioObesidadeAvo)).isChecked())
            riscos.add(new Risco("OBESIDADE_AVO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("OBESIDADE_AVO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioObesidadeVo)).isChecked())
            riscos.add(new Risco("OBESIDADE_VO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("OBESIDADE_VO", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioObesidadeMae)).isChecked())
            riscos.add(new Risco("OBESIDADE_MAE", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("OBESIDADE_MAE", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioObesidadePai)).isChecked())
            riscos.add(new Risco("OBESIDADE_PAI", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("OBESIDADE_PAI", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioObesidadeIrma)).isChecked())
            riscos.add(new Risco("OBESIDADE_IRMA", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("OBESIDADE_IRMA", 0, "NAO_MODIFICAVEL"));

        if (((CheckBox) findViewById(R.id.radioObesidadermao)).isChecked())
            riscos.add(new Risco("OBESIDADE_IRMAO", 10, "NAO_MODIFICAVEL"));
        else riscos.add(new Risco("OBESIDADE_IRMAO", 0, "NAO_MODIFICAVEL"));
        AdicionarRiscos ar = new AdicionarRiscos();
        ar.execute();
    }

        private  class AdicionarRiscos extends AsyncTask<String, Void, Integer> {
            @Override
            protected void onPreExecute() {
                load = ProgressDialog.show(Formulario_segundo.this, "Por favor Aguarde ...",
                        "Salvando dados no servidor ...");
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            protected Integer doInBackground(String... params) {
                int retorno=0;
                try {
                 retorno =Auxiliar.adicionarRiscos();


                } catch (Exception e) {
                    e.printStackTrace();
                }

                return retorno;
            }

            @Override
            protected void onPostExecute(Integer retornoHTTP) {

                load.dismiss();
                if(retornoHTTP==201){
                    Toast toast = Toast.makeText(Formulario_segundo.this, "Dados salvos com sucesso!!",Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent(Formulario_segundo.this, Principal.class);
                    startActivity(intent);
                }
                else{
                    Toast toast = Toast.makeText(Formulario_segundo.this, "Não foi possivel salvar os dados, tente mais tarde. \n Erro: "+retornoHTTP,Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        }


    }


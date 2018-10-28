package com.example.casa.lifecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Risco;

import java.util.List;

public class Formulario_segundo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_segundo);
        final Button proximo = (Button) findViewById(R.id.botaoFormulario);
        proximo.setOnClickListener(new  View.OnClickListener(){
            @Override
            public  void  onClick(View v){
                Toast.makeText(Formulario_segundo.this, "Congratulations your form is finish",
                        Toast.LENGTH_LONG).show();
                proximoFormulario();


            }
        } );
    }
    public void proximoFormulario(){
      List<Risco> riscos = Auxiliar.prontuario.getRiscos();
      int contar = riscos.size();
        if (((CheckBox) findViewById(R.id.radioDiabetesAvo)).isChecked())
            riscos.add(new Risco(contar, "DIABETES,AVO,1"));
        else riscos.add(new Risco(contar, "DIABETES,AVO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDiabetesVo)).isChecked())
            riscos.add(new Risco(contar, "DIABETES,VO,1"));
        else riscos.add(new Risco(contar, "DIABETES,VO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDiabetesMae)).isChecked())
            riscos.add(new Risco(contar, "DIABETES,MAE,1"));
        else riscos.add(new Risco(contar, "DIABETES,MAE,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDiabetesPai)).isChecked())
            riscos.add(new Risco(contar, "DIABETES,PAI,1"));
        else riscos.add(new Risco(contar, "DIABETES,PAI,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDiabetesIrma)).isChecked())
            riscos.add(new Risco(contar, "DIABETES,IRMA,1"));
        else riscos.add(new Risco(contar, "DIABETES,IRMA,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDiabetesIrmao)).isChecked())
            riscos.add(new Risco(contar, "DIABETES,IRMAO,1"));
        else riscos.add(new Risco(contar, "DIABETES,IRMAO,0"));
        contar++;

        //HIPERTENSAO
        if (((CheckBox) findViewById(R.id.radioHipertencaoAvo)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,AVO,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,AVO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioHipertencaoVo)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,VO,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,VO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioHipertencaoMae)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,MAE,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,MAE,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioHipertencaoPai)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,PAI,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,PAI,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioHipertencaoIrma)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,IRMA,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,IRMA,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioHipertencaoIrmao)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,IRMAO,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,IRMAO,0"));
        contar++;

        //cardiacas
        if (((CheckBox) findViewById(R.id.radioCardiacoAvo)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,AVO,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,AVO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioCardiacoVo)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,VO,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,VO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioCardiacoMae)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,MAE,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,MAE,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioCardiacoPai)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,PAI,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,PAI,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioCardiacoIrma)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,IRMA,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,IRMA,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioCardiacoIrmao)).isChecked())
            riscos.add(new Risco(contar, "HIPERTENSAO,IRMAO,1"));
        else riscos.add(new Risco(contar, "HIPERTENSAO,IRMAO,0"));
        contar++;

        //neuro

        if (((CheckBox) findViewById(R.id.radioNeuroAvo)).isChecked())
            riscos.add(new Risco(contar, "NEUROLOGICAS,AVO,1"));
        else riscos.add(new Risco(contar, "NEUROLOGICAS,AVO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioNeuroVo)).isChecked())
            riscos.add(new Risco(contar, "NEUROLOGICAS,VO,1"));
        else riscos.add(new Risco(contar, "NEUROLOGICAS,VO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioNeuroMae)).isChecked())
            riscos.add(new Risco(contar, "NEUROLOGICAS,MAE,1"));
        else riscos.add(new Risco(contar, "NEUROLOGICAS,MAE,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioNeuroPai)).isChecked())
            riscos.add(new Risco(contar, "NEUROLOGICAS,PAI,1"));
        else riscos.add(new Risco(contar, "NEUROLOGICAS,PAI,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioNeuroIrma)).isChecked())
            riscos.add(new Risco(contar, "NEUROLOGICAS,IRMA,1"));
        else riscos.add(new Risco(contar, "NEUROLOGICAS,IRMA,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioNeuroIrmao)).isChecked())
            riscos.add(new Risco(contar, "NEUROLOGICAS,IRMAO,1"));
        else riscos.add(new Risco(contar, "NEUROLOGICAS,IRMAO,0"));
        contar++;

        //renal

        if (((CheckBox) findViewById(R.id.radioRenalAvo)).isChecked())
            riscos.add(new Risco(contar, "RENAL,AVO,1"));
        else riscos.add(new Risco(contar, "RENAL,AVO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioRenalVo)).isChecked())
            riscos.add(new Risco(contar, "RENAL,VO,1"));
        else riscos.add(new Risco(contar, "RENAL,VO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioRenalMae)).isChecked())
            riscos.add(new Risco(contar, "RENAL,MAE,1"));
        else riscos.add(new Risco(contar, "RENAL,MAE,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioRenalPai)).isChecked())
            riscos.add(new Risco(contar, "RENAL,PAI,1"));
        else riscos.add(new Risco(contar, "RENAL,PAI,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioNRenalIrma)).isChecked())
            riscos.add(new Risco(contar, "RENAL,IRMA,1"));
        else riscos.add(new Risco(contar, "RENAL,IRMA,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioRenalrmao)).isChecked())
            riscos.add(new Risco(contar, "RENAL,IRMAO,1"));
        else riscos.add(new Risco(contar, "RENAL,IRMAO,0"));
        contar++;


        //dst

        if (((CheckBox) findViewById(R.id.radioDSTAvo)).isChecked())
            riscos.add(new Risco(contar, "DST,AVO,1"));
        else riscos.add(new Risco(contar, "DST,AVO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDSTVo)).isChecked())
            riscos.add(new Risco(contar, "DST,VO,1"));
        else riscos.add(new Risco(contar, "DST,VO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDSTMae)).isChecked())
            riscos.add(new Risco(contar, "DST,MAE,1"));
        else riscos.add(new Risco(contar, "DST,MAE,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDSTPai)).isChecked())
            riscos.add(new Risco(contar, "DST,PAI,1"));
        else riscos.add(new Risco(contar, "DST,PAI,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDSTIrma)).isChecked())
            riscos.add(new Risco(contar, "DST,IRMA,1"));
        else riscos.add(new Risco(contar, "DST,IRMA,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioDSTrmao)).isChecked())
            riscos.add(new Risco(contar, "DST,IRMAO,1"));
        else riscos.add(new Risco(contar, "DST,IRMAO,0"));
        contar++;

        //obesidade

        if (((CheckBox) findViewById(R.id.radioObesidadeAvo)).isChecked())
            riscos.add(new Risco(contar, "OBESIDADE,AVO,1"));
        else riscos.add(new Risco(contar, "OBESIDADE,AVO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioObesidadeVo)).isChecked())
            riscos.add(new Risco(contar, "OBESIDADE,VO,1"));
        else riscos.add(new Risco(contar, "OBESIDADE,VO,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioObesidadeMae)).isChecked())
            riscos.add(new Risco(contar, "OBESIDADE,MAE,1"));
        else riscos.add(new Risco(contar, "OBESIDADE,MAE,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioObesidadePai)).isChecked())
            riscos.add(new Risco(contar, "OBESIDADE,PAI,1"));
        else riscos.add(new Risco(contar, "OBESIDADE,PAI,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioObesidadeIrma)).isChecked())
            riscos.add(new Risco(contar, "OBESIDADE,IRMA,1"));
        else riscos.add(new Risco(contar, "OBESIDADE,IRMA,0"));
        contar++;
        if (((CheckBox) findViewById(R.id.radioObesidadermao)).isChecked())
            riscos.add(new Risco(contar, "OBESIDADE,IRMAO,1"));
        else riscos.add(new Risco(contar, "OBESIDADE,IRMAO,0"));
        contar++;
        Intent intent = new Intent(this, Principal.class);
        startActivity(intent);

    }
}

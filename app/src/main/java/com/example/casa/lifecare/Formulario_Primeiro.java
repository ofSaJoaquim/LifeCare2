package com.example.casa.lifecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Prontuario;
import com.example.casa.lifecare.entidades.Risco;

import java.util.ArrayList;
import java.util.List;

public class Formulario_Primeiro extends AppCompatActivity {


    private boolean abreFuma = false;
    private boolean abreOutras = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__primeiro);
       // Toolbar toolbar = findViewById(R.id.my_toolbar); // get the reference of Toolbar
       // setSupportActionBar(toolbar); // Setting/replace toolbar as the ActionBar
        //toolbar.setTitle("");


        final RadioGroup internado = findViewById(R.id.radioGroupInternado);
        final RadioGroup diabete = findViewById(R.id.radioGroupDiabete);
        final RadioGroup acamado = findViewById(R.id.radioGroupAcamado);
        final RadioGroup consulta = findViewById(R.id.radioGroupoConsulta);
        final RadioGroup cirugia = findViewById(R.id.radioGroupoCirugia);
        final RadioGroup depressao = findViewById(R.id.radioGroupoDepressao);
        final RadioGroup atividade = findViewById(R.id.radioGroupoAtividadeFisica);
        final EditText atividadeVezes = findViewById(R.id.editVezesSemanaAt);
        final EditText atividadeDuracao = findViewById(R.id.editMinutosAtDia);
        final RadioGroup bebe = findViewById(R.id.radioGroupoBebe);
        final EditText bebeVezes = findViewById(R.id.editBebeVezes);
        final RadioGroup fuma = findViewById(R.id.radioGroupFuma);
        final EditText fumaVezes = findViewById(R.id.editFumaVezes);
        final RadioGroup fumarParar = findViewById(R.id.radioGroupPararFumar);
        final RadioGroup avc = findViewById(R.id.radioGroupAvc);
        final RadioGroup infarto = findViewById(R.id.radioGroupoInfarto);


        final LinearLayout lnAtividade = findViewById(R.id.lnAtividade);
        final LinearLayout lnBebe = findViewById(R.id.lnBebe);
        final LinearLayout lnFuma = findViewById(R.id.lnFuma);
        final CheckBox checkOutrasDoencas = findViewById(R.id.radioOutras);

        lnAtividade.setVisibility(View.INVISIBLE);
        lnBebe.setVisibility(View.INVISIBLE);
        lnFuma.setVisibility(View.INVISIBLE);
        fumarParar.setVisibility(View.INVISIBLE);
        findViewById(R.id.textoPararFumar).setVisibility(View.INVISIBLE);
        lnAtividade.setVisibility(View.INVISIBLE);
        lnBebe.setVisibility(View.INVISIBLE);
        findViewById(R.id.editOutrasDoencas).setVisibility(View.INVISIBLE);

        fuma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (fuma.getCheckedRadioButtonId() == R.id.radioFumaSim) {
                    abreFuma = true;
                    findViewById(R.id.textoPararFumar).setVisibility(View.VISIBLE);
                    lnFuma.setVisibility(View.VISIBLE);
                    fumarParar.setVisibility(View.VISIBLE);


                } else {
                    lnFuma.setVisibility(View.INVISIBLE);
                    fumarParar.setVisibility(View.INVISIBLE);
                    findViewById(R.id.textoPararFumar).setVisibility(View.INVISIBLE);
                    abreFuma = false;

                }
            }
        });

        checkOutrasDoencas.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkOutrasDoencas.isChecked()) {
                    findViewById(R.id.editOutrasDoencas).setVisibility(View.VISIBLE);
                    abreOutras = true;
                } else {
                    findViewById(R.id.editOutrasDoencas).setVisibility(View.INVISIBLE);
                    abreOutras = false;
                }
            }
        });


        atividade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (atividade.getCheckedRadioButtonId() == R.id.radioAtividadesFSim) {
                    lnAtividade.setVisibility(View.VISIBLE);


                } else {
                    lnAtividade.setVisibility(View.INVISIBLE);

                }
            }
        });

        bebe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (bebe.getCheckedRadioButtonId() == R.id.radioBebidaAcolicaSim) {
                    lnBebe.setVisibility(View.VISIBLE);


                } else {
                    lnBebe.setVisibility(View.INVISIBLE);

                }
            }
        });


        final Button proximo = findViewById(R.id.confirma);
        proximo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Risco> riscos = new ArrayList<>();
                boolean valida = true;


                if (validar(internado, (TextView) findViewById(R.id.textoInternado))) {
                    if (internado.getCheckedRadioButtonId() == R.id.radioInternadoSim)
                        riscos.add(new Risco( "INTERNADO",1,"NAO_MODIFICAVEL"));
                    else riscos.add(new Risco( "INTERNADO",0,"NAO_MODIFICAVEL"));

                } else valida = false;


                if (validar(diabete, (TextView) findViewById(R.id.textoDiabete))) {
                    if (diabete.getCheckedRadioButtonId() == R.id.radioDiabetesSim)
                        riscos.add(new Risco("DIABETES",1,"NAO_MODIFICAVEL"));
                    else riscos.add(new Risco( "DIABETES",0,"NAO_MODIFICAVEL"));

                } else valida = false;

                if (validar(acamado, (TextView) findViewById(R.id.textoAcamado))) {
                    if (acamado.getCheckedRadioButtonId() == R.id.radioAcamadoSim)
                        riscos.add(new Risco("ACAMADO",1,"MODIFICAVEL"));
                    else riscos.add(new Risco("ACAMADO",0,"MODIFICAVEL"));

                } else valida = false;
                if (validar(consulta, (TextView) findViewById(R.id.textoConsulta))) {
                    if (consulta.getCheckedRadioButtonId() == R.id.radioConsultasSim)
                        riscos.add(new Risco("CONSULTA",1,"MODIFICAVEL"));
                    else riscos.add(new Risco("CONSULTA",0,"MODIFICAVEL"));

                } else valida = false;

                if (validar(cirugia, (TextView) findViewById(R.id.textoCirugia))) {
                    if (consulta.getCheckedRadioButtonId() == R.id.radioCirugiaSim)
                        riscos.add(new Risco("CIRUGIA",1,"NAO_MODIFICAVEL"));
                    else riscos.add(new Risco("CIRUGIA",0,"NAO_MODIFICAVEL"));

                } else valida = false;

                if (validar(depressao, (TextView) findViewById(R.id.textoDepressao))) {
                    if (depressao.getCheckedRadioButtonId() == R.id.radioDepressaoSim)
                        riscos.add(new Risco("DEPRESSAO",1,"MODIFICAVEL"));
                    else riscos.add(new Risco("DEPRESSAO",0,"MODIFICAVEL"));

                } else valida = false;

                if (validar(atividade, (TextView) findViewById(R.id.textoAtividadeFisica))) {
                    if (atividade.getCheckedRadioButtonId() == R.id.radioAtividadesFSim) {
                        if (validarNumero(atividadeVezes) && validarNumero(atividadeDuracao)) {
                            riscos.add(new Risco("ATIVIDADE",Integer.parseInt(atividadeVezes.getText().toString())*Integer.parseInt(atividadeDuracao.getText().toString()),"MODIFICAVEL"));
                        } else valida = false;
                    } else riscos.add(new Risco("ATIVIDADE",0,"MODIFICAVEL"));

                } else valida = false;

                if (validar(bebe, (TextView) findViewById(R.id.textoBebe))) {
                    if (bebe.getCheckedRadioButtonId() == R.id.radioBebidaAcolicaSim) {
                        if (validarNumero(bebeVezes)) {
                            riscos.add(new Risco("BEBE",0,"MODIFICAVEL"));
                        } else valida = false;
                    } else riscos.add(new Risco("BEBE",0,"MODIFICAVEL"));

                } else valida = false;


                if (validar(fuma, (TextView) findViewById(R.id.textoFuma))) {
                    if (fuma.getCheckedRadioButtonId() == R.id.radioFumaSim) {
                        if (validarNumero(fumaVezes))
                            riscos.add(new Risco( "FUMA",Integer.parseInt(fumaVezes.getText().toString()),"MODIFICAVEL"));
                        else valida = false;
                    } else riscos.add(new Risco("FUMA",0,"MODIFICAVEL"));

                }

                if (abreFuma) {
                    if (validar(fumarParar, (TextView) findViewById(R.id.textoPararFumar))) {
                        if (fumarParar.getCheckedRadioButtonId() == R.id.radioQrPararFumarSim)
                            riscos.add(new Risco("PARAR-FUMAR",1,"MODIFICAVEL"));
                        else riscos.add(new Risco("PARAR-FUMAR",0,"MODIFICAVEL"));
                    } else valida = false;


                }


                if (validar(avc, (TextView) findViewById(R.id.textoAvc))) {
                    if (avc.getCheckedRadioButtonId() == R.id.radioAvcMenosAno)
                        riscos.add(new Risco("AVC",2,"NAO_MODIFICAVEL"));
                    else if (avc.getCheckedRadioButtonId() == R.id.radioAvcMaisAno)
                        riscos.add(new Risco("AVC",1,"NAO_MODIFICAVEL"));
                    else
                        riscos.add(new Risco("AVC",0,"NAO_MODIFICAVEL"));

                } else valida = false;

                if (validar(infarto, (TextView) findViewById(R.id.textoInfarto))) {
                    if (avc.getCheckedRadioButtonId() == R.id.radioInfartoMenos)
                        riscos.add(new Risco("INFARTO",2,"NAO_MODIFICAVEL"));
                    else if (avc.getCheckedRadioButtonId() == R.id.radioInfartoMais)
                        riscos.add(new Risco("INFARTO",1,"NAO_MODIFICAVEL"));
                    else
                        riscos.add(new Risco("INFARTO",0,"NAO_MODIFICAVEL"));

                } else valida = false;
                Log.i("2teste", valida + "");
                if (((CheckBox) findViewById(R.id.arroz)).isChecked())
                    riscos.add(new Risco("ARROZ",1,"ALIMENTACAO"));
                else riscos.add(new Risco("ARROZ",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.feijao)).isChecked())
                    riscos.add(new Risco("FEIJAO",1,"ALIMENTACAO"));
                else riscos.add(new Risco("FEIJAO",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.massa)).isChecked())
                    riscos.add(new Risco("MASSA",1,"ALIMENTACAO"));
                else riscos.add(new Risco("MASSA",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.batata)).isChecked())
                    riscos.add(new Risco("BATATA",1,"ALIMENTACAO"));
                else riscos.add(new Risco("BATATA",1,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.acucar)).isChecked())
                    riscos.add(new Risco("ACUCAR",1,"ALIMENTACAO"));
                else riscos.add(new Risco("ACUCAR",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.carne)).isChecked())
                    riscos.add(new Risco("CARNE",1,"ALIMENTACAO"));
                else riscos.add(new Risco("CARNE",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.ovos)).isChecked())
                    riscos.add(new Risco("OVOS",1,"ALIMENTACAO"));
                else riscos.add(new Risco("OVOS",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.leite)).isChecked())
                    riscos.add(new Risco("LEITE",1,"ALIMENTACAO"));
                else riscos.add(new Risco("LEITE",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.queijo)).isChecked())
                    riscos.add(new Risco("QUEIJO",1,"ALIMENTACAO"));
                else riscos.add(new Risco("QUEIJO",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.vegetais)).isChecked())
                    riscos.add(new Risco("VEGETAIS",1,"ALIMENTACAO"));
                else riscos.add(new Risco("VEGETAIS",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.sal)).isChecked())
                    riscos.add(new Risco("SAL",1,"ALIMENTACAO"));
                else riscos.add(new Risco("SAL",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.radioObstrutiva)).isChecked())
                    riscos.add(new Risco("PULMONAR_OBSTRUTIVA",1,"NAO_MODIFICAVEL"));
                else riscos.add(new Risco("PULMONAR_OBSTRUTIVA",0,"NAO_MODIFICAVEL"));

                if (((CheckBox) findViewById(R.id.radioBronquite)).isChecked())
                    riscos.add(new Risco("BRONQUITE",1,"NAO_MODIFICAVEL"));
                else riscos.add(new Risco("BRONQUITE",0,"NAO_MODIFICAVEL"));

                if (((CheckBox) findViewById(R.id.radioBronquite)).isChecked())
                    riscos.add(new Risco("ASMA",1,"NAO_MODIFICAVEL"));
                else riscos.add(new Risco("ASMA",0,"NAO_MODIFICAVEL"));

                if (abreOutras) {
                    EditText outrasE = findViewById(R.id.editOutrasDoencas);
                    String outras = outrasE.getText().toString();
                    String expressao = "[a-zA-Z]+[[ ]a-zA-Z0-9]";
                    if (outras.length() < 1) {
                        outrasE.setError("Campo Obrigatório");
                        valida = false;
                    } else if (!outras.matches(expressao)) {
                        outrasE.setError("Valor inválido");
                        valida = false;

                    } else {
                        riscos.add(new Risco(outras,1,"OUTRAS"));

                    }

                }


                for (Risco risco : riscos) {
                    Log.i("Risco", risco.getId() + "-" + risco.getTipo());
                }
                if (valida) {
                    Toast.makeText(Formulario_Primeiro.this, "Formulário preenchido corretamente",
                            Toast.LENGTH_SHORT).show();
                Auxiliar.prontuario=new Prontuario();
                    Auxiliar.prontuario.setRiscos(riscos);
                    proximoFormulario();
                }
                else {
                    Toast.makeText(Formulario_Primeiro.this, "Preencha todos os campos",
                            Toast.LENGTH_LONG).show();
                }
            }


        });


    }



    public void proximoFormulario() {
        Intent intent = new Intent(this, Formulario_segundo.class);
        startActivity(intent);

    }


    public boolean validar(RadioGroup radioGroup, TextView textView) {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            textView.setError("Campo obrigatório");
            textView.setFocusable(true);
            textView.requestFocus();
            return false;

        } else {
            textView.setError(null);
            textView.setFocusable(false);
            return true;
        }


    }

    public boolean validarNumero(EditText editText) {
        String expressao = "[0-9]{1,2}";
        String teste = editText.getText().toString();
        if (teste.matches(expressao)) {
            try {
                if (Integer.parseInt(teste) > 0) return true;
                else {
                    editText.setError("Valor inválido");
                    (findViewById(R.id.textoInternado)).setFocusable(true);
                    (findViewById(R.id.textoInternado)).requestFocus();

                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;

            }

        } else {
            editText.setError("Valor inválido");
            (findViewById(R.id.textoInternado)).setFocusable(true);
            (findViewById(R.id.textoInternado)).requestFocus();
        }


        return false;

    }

}

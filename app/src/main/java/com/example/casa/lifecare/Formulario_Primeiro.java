package com.example.casa.lifecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.LinhaDeCuidado;
import com.example.casa.lifecare.entidades.Prontuario;
import com.example.casa.lifecare.entidades.Risco;
import com.example.casa.lifecare.entidades.Site;

import java.util.ArrayList;
import java.util.List;

public class Formulario_Primeiro extends AppCompatActivity {


    private boolean abreFuma = false;

    ProgressDialog load;
    List<LinhaDeCuidado>linhasDeCuidado=new ArrayList<LinhaDeCuidado>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario__primeiro);
       // Toolbar toolbar = findViewById(R.id.my_toolbar); // get the reference of Toolbar
       // setSupportActionBar(toolbar); // Setting/replace toolbar as the ActionBar
        //toolbar.setTitle("");



        final RadioGroup acamado = findViewById(R.id.radioGroupAcamado);
        final RadioGroup consulta = findViewById(R.id.radioGroupoConsulta);
        final RadioGroup cirugia = findViewById(R.id.radioGroupoCirugia);

        final RadioGroup atividade = findViewById(R.id.radioGroupoAtividadeFisica);
        final EditText atividadeVezes = findViewById(R.id.editVezesSemanaAt);
        final EditText atividadeDuracao = findViewById(R.id.editMinutosAtDia);
        final RadioGroup bebe = findViewById(R.id.radioGroupoBebe);
        final EditText bebeVezes = findViewById(R.id.editBebeVezes);
        final RadioGroup fuma = findViewById(R.id.radioGroupFuma);
        final EditText fumaVezes = findViewById(R.id.editFumaVezes);
        final RadioGroup fumarParar = findViewById(R.id.radioGroupPararFumar);




        final LinearLayout lnAtividade = findViewById(R.id.lnAtividade);
        final LinearLayout lnBebe = findViewById(R.id.lnBebe);
        final LinearLayout lnFuma = findViewById(R.id.lnFuma);


        lnAtividade.setVisibility(View.GONE);
        lnBebe.setVisibility(View.GONE);
        lnFuma.setVisibility(View.GONE);
        fumarParar.setVisibility(View.GONE);
        findViewById(R.id.textoPararFumar).setVisibility(View.GONE);
        lnAtividade.setVisibility(View.GONE);
        lnBebe.setVisibility(View.GONE);


        fuma.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (fuma.getCheckedRadioButtonId() == R.id.radioFumaSim) {
                    abreFuma = true;
                    findViewById(R.id.textoPararFumar).setVisibility(View.VISIBLE);
                    lnFuma.setVisibility(View.VISIBLE);
                    fumarParar.setVisibility(View.VISIBLE);


                } else {
                    lnFuma.setVisibility(View.GONE);
                    fumarParar.setVisibility(View.GONE);
                    findViewById(R.id.textoPararFumar).setVisibility(View.GONE);
                    abreFuma = false;

                }
            }
        });




        atividade.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (atividade.getCheckedRadioButtonId() == R.id.radioAtividadesFSim) {
                    lnAtividade.setVisibility(View.VISIBLE);


                } else {
                    lnAtividade.setVisibility(View.GONE);

                }
            }
        });

        bebe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (bebe.getCheckedRadioButtonId() == R.id.radioBebidaAcolicaSim) {
                    lnBebe.setVisibility(View.VISIBLE);


                } else {
                    lnBebe.setVisibility(View.GONE);

                }
            }
        });


        final Button proximo = findViewById(R.id.confirma);
        proximo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Risco> riscos = new ArrayList<>();
                boolean valida = true;




                if (validar(acamado, (TextView) findViewById(R.id.textoAcamado))) {
                    if (acamado.getCheckedRadioButtonId() == R.id.radioAcamadoSim) {


                        riscos.add(new Risco("ACAMADO", 200, "MODIFICAVEL"));
                        linhasDeCuidado.add(new LinhaDeCuidado("Cuidados básico com pessoas acamadas","Acamado",new Site("http://blog.nobresaude.pifferdigital.com.br/2017/12/14/pessoas-acamadas/","Acamado")));

                    }
                } else valida = false;
                if (validar(consulta, (TextView) findViewById(R.id.textoConsulta))) {
                    if (consulta.getCheckedRadioButtonId() == R.id.radioConsultasSim)
                        riscos.add(new Risco("CONSULTA",-40,"MODIFICAVEL"));
                    else {
                        riscos.add(new Risco("CONSULTA",40,"MODIFICAVEL"));
                        linhasDeCuidado.add(new LinhaDeCuidado("Mostrar os beneficions de ir ao médico regularmente","Importância de ir ao médico",new Site("http://saudenocorpo.com/importancia-de-ir-ao-medico-regularmente/","Site ir ao médico")));
                    }

                } else valida = false;

                if (validar(cirugia, (TextView) findViewById(R.id.textoCirugia))) {
                    if (consulta.getCheckedRadioButtonId() == R.id.radioCirugiaSim) {
                        riscos.add(new Risco("CIRUGIA", 30, "NAO_MODIFICAVEL"));
linhasDeCuidado.add(new LinhaDeCuidado("Cuidados de pós cirugias","Pós Ciruguia",
        new Site("http://clinicagontijo.com/7-cuidados-pos-operatorios-importantes-para-quem-fez-cirurgia-plastica/","Pós cirugia")));
                    }
                } else valida = false;



                if (validar(atividade, (TextView) findViewById(R.id.textoAtividadeFisica))) {
                    if (atividade.getCheckedRadioButtonId() == R.id.radioAtividadesFSim) {
                        if (validarNumero(atividadeVezes) && validarNumero(atividadeDuracao)) {
                            riscos.add(new Risco("ATIVIDADE",Integer.parseInt(atividadeVezes.getText().toString())*Integer.parseInt(atividadeDuracao.getText().toString())*-10,"MODIFICAVEL"));
                        } else valida = false;
                    } else{
                        riscos.add(new Risco("ATIVIDADE",100,"MODIFICAVEL"));
                        linhasDeCuidado.add(new LinhaDeCuidado("Praticar atividades físicas","Atividade Fisica",new Site("http://www.asfitness.com.br/noticias/asf314567/beneficios-da-atividade-fisica-nos-efeitos-psicologicos-e-cognitivos","Site mostrando os beneficios da atividade fisica")));

                    }

                } else valida = false;

                if (validar(bebe, (TextView) findViewById(R.id.textoBebe))) {
                    if (bebe.getCheckedRadioButtonId() == R.id.radioBebidaAcolicaSim) {
                        if (validarNumero(bebeVezes)) {
                            riscos.add(new Risco("BEBE",80,"MODIFICAVEL"));
                            linhasDeCuidado.add(new LinhaDeCuidado("Riscos do consumo exagerado de bebidas alcoolicas",
                                    "Bebida Alccolica",new Site("http://www.aconteceempetropolis.com.br/2015/03/19/excesso-consumo-de-bebidas-alcoolicas-pode-ser-fatal/","Site mostrando os riscos do consumo excessivo de bebidas")));
                        } else valida = false;
                    }

                } else valida = false;


                if (validar(fuma, (TextView) findViewById(R.id.textoFuma))) {
                    if (fuma.getCheckedRadioButtonId() == R.id.radioFumaSim) {
                        if (validarNumero(fumaVezes)) {
                            riscos.add(new Risco("FUMA", Integer.parseInt(fumaVezes.getText().toString()) * 10, "MODIFICAVEL"));
                            linhasDeCuidado.add(new LinhaDeCuidado("Conhecer os males do cigarro.","Parar de fumar",new Site("https://www.vix.com/pt/saude/543408/o-que-o-cigarro-faz-no-corpo-destruicao-no-cerebro-pulmao-e-outros-orgaos-impressiona","Site mostrando os males do cigarro")));

                        }
                        else valida = false;
                    }

                }

                if (abreFuma) {
                    if (validar(fumarParar, (TextView) findViewById(R.id.textoPararFumar))) {
                        if (fumarParar.getCheckedRadioButtonId() == R.id.radioQrPararFumarSim)
                            riscos.add(new Risco("PARAR-FUMAR",-30,"MODIFICAVEL"));
                        else riscos.add(new Risco("PARAR-FUMAR",0,"MODIFICAVEL"));
                    } else valida = false;


                }



                Log.i("2teste", valida + "");
                if (((CheckBox) findViewById(R.id.arroz)).isChecked())
                    riscos.add(new Risco("ARROZ",0,"ALIMENTACAO"));
                else riscos.add(new Risco("ARROZ",20,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.feijao)).isChecked())
                    riscos.add(new Risco("FEIJAO",0,"ALIMENTACAO"));
                else riscos.add(new Risco("FEIJAO",20,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.massa)).isChecked())
                    riscos.add(new Risco("MASSA",5,"ALIMENTACAO"));
                else riscos.add(new Risco("MASSA",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.batata)).isChecked())
                    riscos.add(new Risco("BATATA",1,"ALIMENTACAO"));
                else riscos.add(new Risco("BATATA",10,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.acucar)).isChecked())
                    riscos.add(new Risco("ACUCAR",15,"ALIMENTACAO"));
                else riscos.add(new Risco("ACUCAR",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.carne)).isChecked())
                    riscos.add(new Risco("CARNE",0,"ALIMENTACAO"));
                else riscos.add(new Risco("CARNE",20,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.ovos)).isChecked())
                    riscos.add(new Risco("OVOS",0,"ALIMENTACAO"));
                else riscos.add(new Risco("OVOS",10,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.leite)).isChecked())
                    riscos.add(new Risco("LEITE",1,"ALIMENTACAO"));
                else riscos.add(new Risco("LEITE",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.queijo)).isChecked())
                    riscos.add(new Risco("QUEIJO",1,"ALIMENTACAO"));
                else riscos.add(new Risco("QUEIJO",0,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.vegetais)).isChecked())
                    riscos.add(new Risco("VEGETAIS",0,"ALIMENTACAO"));
                else riscos.add(new Risco("VEGETAIS",20,"ALIMENTACAO"));

                if (((CheckBox) findViewById(R.id.sal)).isChecked())
                    riscos.add(new Risco("SAL",10,"ALIMENTACAO"));
                else riscos.add(new Risco("SAL",1,"ALIMENTACAO"));
                linhasDeCuidado.add(new LinhaDeCuidado("Dicas para uma alimentação saudavél","Padrão Alimentação",new Site("https://belezaesaude.com/dicas-alimentacao-saudavel/","Alimentacao")));






                for (Risco risco : riscos) {
                    Log.i("Risco", risco.getId() + "-" + risco.getTipo());
                }
                if (valida) {
                    Toast.makeText(Formulario_Primeiro.this, "Formulário preenchido corretamente",
                            Toast.LENGTH_SHORT).show();
                Auxiliar.prontuario=new Prontuario();
                    Auxiliar.prontuario.setRiscos(riscos);
                   AdicionarRiscos ar = new AdicionarRiscos();
                   ar.execute();
                }
                else {
                    Toast.makeText(Formulario_Primeiro.this, "Preencha todos os campos",
                            Toast.LENGTH_LONG).show();
                }
            }


        });


    }



    public void proximoFormulario() {
        Intent intent = new Intent(this, Principal.class);
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


                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;

            }

        } else {
            editText.setError("Valor inválido");

        }


        return false;

    }

    private  class AdicionarRiscos extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(Formulario_Primeiro.this, "Por favor Aguarde ...",
                    "Salvando dados no servidor ...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Integer doInBackground(String... params) {
            int retorno=0;
            try {
                retorno =Auxiliar.adicionarRiscos();
                retorno=Auxiliar.adicionarLinhasDeCuidado(linhasDeCuidado);
                Auxiliar.carregarProntuario();
                for(int count=0; count<linhasDeCuidado.size();count++){
                  LinhaDeCuidado  linhaApp = linhasDeCuidado.get(count);
                  LinhaDeCuidado linhaServer = Auxiliar.prontuario.getLinhasDeCuidado().get(count);

                            Log.i("teste",linhaApp.getSites().size()+"");
                            Log.i("linha",linhaApp.getTitulo());
                        if(linhaApp.getSites().size()>0){
                            Log.i("teste",linhaApp.getSites().size()+"");
                            Log.i("linha",linhaApp.getTitulo());
                            retorno=Auxiliar.adicionarSites(linhaApp.getSites().get(0),linhaServer.getId());
                        }
                    }



            } catch (Exception e) {
                e.printStackTrace();
                Log.i("Form 1",e.getMessage());
            }

            return retorno;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {

            load.dismiss();
            if((retornoHTTP==201)||(retornoHTTP==400)){
                Toast toast = Toast.makeText(Formulario_Primeiro.this, "Dados salvos com sucesso!!",Toast.LENGTH_LONG);
                toast.show();
                proximoFormulario();
            }
            else{
                Toast toast = Toast.makeText(Formulario_Primeiro.this, "Não foi possivel salvar os dados, tente mais tarde. \n Erro: "+retornoHTTP,Toast.LENGTH_LONG);
                toast.show();
            }

        }
    }


}




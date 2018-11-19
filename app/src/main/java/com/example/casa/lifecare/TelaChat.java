package com.example.casa.lifecare;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.casa.lifecare.adptador.MenssagemAdptador;
import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Medico;

public class TelaChat extends AppCompatActivity {
    MenssagemAdptador ma;
    private ProgressDialog load;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_chat);

        PegaMensagens pm = new PegaMensagens();
        pm.execute();


        final Button enviar =findViewById(R.id.botaoEnviar);
        final EditText menssagemE = findViewById(R.id.editChat);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnviarMensagens enviarMensagens = new EnviarMensagens();
                enviarMensagens.execute(((EditText)findViewById(R.id.editChat)).getText().toString());
                hideSoftKeyboard(menssagemE);
               /* if(ma!=null){
                    if(ma.getItemCount()>5)recyclerView.smoothScrollToPosition(ma.getItemCount()-1);
                }*/

            }

        });
        }



    public void hideSoftKeyboard(EditText e) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(e.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private class PegaMensagens extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(TelaChat.this, "Por favor Aguarde ...",
                    "Conectando no servidor ...");
        }


        @Override
        protected Integer doInBackground(String... params) {
            try {

              Auxiliar.carregarMenssagens();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {
            recyclerView  = (RecyclerView) findViewById(R.id.recicla);
            LinearLayoutManager  mLayoutManager = new LinearLayoutManager(TelaChat.this, LinearLayout.VERTICAL,false);
            recyclerView.setLayoutManager(mLayoutManager);
            ma=new MenssagemAdptador(Auxiliar.mensagems,TelaChat.this);
            recyclerView.setAdapter(ma);

load.dismiss();



            }

        }

    private class EnviarMensagens extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(TelaChat.this, "Por favor Aguarde ...",
                    "Conectando no servidor ...");
        }


        @Override
        protected Integer doInBackground(String... params) {
            try {
              if(Auxiliar.enviarMenssagens(params[0])) {
                  Auxiliar.carregarMenssagens();
              }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {
            recyclerView  = (RecyclerView) findViewById(R.id.recicla);
            LinearLayoutManager  mLayoutManager = new LinearLayoutManager(TelaChat.this, LinearLayout.VERTICAL,false);
            recyclerView.setLayoutManager(mLayoutManager);
            ma=new MenssagemAdptador(Auxiliar.mensagems,TelaChat.this);
            recyclerView.setAdapter(ma);
            ((EditText)findViewById(R.id.editChat)).setText("");

            load.dismiss();



        }

    }
    }



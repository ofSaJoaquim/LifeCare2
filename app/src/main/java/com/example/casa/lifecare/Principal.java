package com.example.casa.lifecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.casa.lifecare.Servicos.WebScraping;
import com.example.casa.lifecare.adptador.AdptadorNoticias;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {
AdptadorNoticias adptadorNoticias;
    RecyclerView recyclerView;
private ProgressDialog load;
    List<WebScraping> menssagens;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.button_chat:
                proximaTela(new Intent(this,TelaChat.class));
               Log.i("chat",R.id.button_chat+"");
                return true;
            case R.id.button_prontuario:
                Log.i("prontuario",R.id.button_chat+"");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        setToolbar();
        menssagens = new ArrayList<WebScraping>();
        menssagens.add(new WebScraping("https://www.iped.com.br/materias/odontologia/saude-bucal-funcionamento-organismo.html"));
        menssagens.add(new WebScraping("https://www.noticiasaominuto.com.br/lifestyle/560632/dieta-rica-em-fibras-pode-ajudar-a-controlardiabetes-tipo-2"));
        menssagens.add(new WebScraping("https://www.saudedica.com.br/como-preparar-agua-de-arroz-para-aliviar-a-diarreia/amp/"));
         Carregar carregar = new Carregar();
         carregar.execute();

        Log.i("testeRec",menssagens.toString());

    }

    private void setToolbar() {
        setSupportActionBar(((Toolbar)findViewById(R.id.toolbar_principal)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void proximaTela(Intent intent) {
         startActivity(intent);
    }
    private class Carregar extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(Principal.this, "Por favor Aguarde ...",
                    "Carregando Notícias ...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Integer doInBackground(String... params) {
            Log.i("teste","teste11133");
            try {
               menssagens.get(0).pegarMetaDados();
                menssagens.get(1).pegarMetaDados();
                menssagens.get(2).pegarMetaDados();

            } catch (Exception e) {
                Log.i("teste",e.getMessage());
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {
            recyclerView = (RecyclerView) findViewById(R.id.reclicler_principal);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(Principal.this, LinearLayout.VERTICAL,false);


            recyclerView.setLayoutManager(mLayoutManager);

           adptadorNoticias = new AdptadorNoticias(menssagens, Principal.this);
           recyclerView.setAdapter(adptadorNoticias);
            load.dismiss();
        }
    }

}

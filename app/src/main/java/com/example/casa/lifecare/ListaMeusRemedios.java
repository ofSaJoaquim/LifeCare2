package com.example.casa.lifecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.casa.lifecare.adptador.AdptadorMeusRemedios;
import com.example.casa.lifecare.adptador.AdptadorNoticias;
import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Paciente;

public class ListaMeusRemedios extends AppCompatActivity {
    AdptadorMeusRemedios adptadorMeusRemedios;
    RecyclerView recyclerView;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_meus_remedios);
        PegaMeusRemedios pegaMeusRemedios = new PegaMeusRemedios();
        pegaMeusRemedios.execute();
        setToolbar();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3, menu);
        return true;
    }

    private void setToolbar() {
        setSupportActionBar(((Toolbar)findViewById(R.id.toolbarListaRemedios)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private class PegaMeusRemedios extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(ListaMeusRemedios.this, "Por favor Aguarde ...",
                    "Conectando no servidor ...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Integer doInBackground(String... params) {
            try {
                Auxiliar.carregarMeusRemedios();
                Log.i("tamanho",Auxiliar.meusMedicamentos.size()+"");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {
            recyclerView = (RecyclerView) findViewById(R.id.recliceRemedios);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(ListaMeusRemedios.this, LinearLayout.VERTICAL, false);
            recyclerView.setLayoutManager(mLayoutManager);
            adptadorMeusRemedios = new AdptadorMeusRemedios(Auxiliar.meusMedicamentos, ListaMeusRemedios.this);
            recyclerView.setAdapter(adptadorMeusRemedios);
            load.dismiss();

        }
    }

}

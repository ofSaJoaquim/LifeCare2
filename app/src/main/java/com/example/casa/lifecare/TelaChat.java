package com.example.casa.lifecare;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.casa.lifecare.adptador.MenssagemAdptador;
import com.example.casa.lifecare.entidades.Medico;
import com.example.casa.lifecare.entidades.Menssagem;
import com.example.casa.lifecare.entidades.Paciente;

import java.util.ArrayList;
import java.util.List;

public class TelaChat extends AppCompatActivity {
    MenssagemAdptador ma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_chat);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recicla);
        LinearLayoutManager  mLayoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL,false);


        recyclerView.setLayoutManager(mLayoutManager);
        List<Menssagem> menssagens = testeConversas();
        Log.i("testeRec",menssagens.toString());
        ma = new MenssagemAdptador(menssagens, this);
        recyclerView.setAdapter(ma);
        final Button enviar =findViewById(R.id.botaoEnviar);
        final EditText menssagemE = findViewById(R.id.editChat);
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ma.insertItem(new Menssagem(new Paciente(),menssagemE.getText().toString(),"00:55"));
                ma.insertItem(new Menssagem(new Medico("Plantão"), "Toma doril que a dor sumiu", "00:55"));
                menssagemE.setText("");

                hideSoftKeyboard(menssagemE);
                recyclerView.smoothScrollToPosition(ma.getItemCount()-1);
            }

        });
        }


    private List<Menssagem> testeConversas(){
        List<Menssagem>ms=new ArrayList<Menssagem>();
        ms.add(new Menssagem(new Medico("Carlos"),"menssagem","10:30"));
        ms.add(new Menssagem(new Paciente(),"paciente","10:31"));
        ms.add(new Menssagem(new Medico("silvio"),"menssagem","10:30"));
        ms.add(new Menssagem(new Paciente(),"paciente","10:31"));
        ms.add(new Menssagem(new Medico("Santos"),"menssagem","10:30"));
        ms.add(new Menssagem(new Paciente(),"paciente","10:31"));
        return  ms;
    }
    public void hideSoftKeyboard(EditText e) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(e.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}

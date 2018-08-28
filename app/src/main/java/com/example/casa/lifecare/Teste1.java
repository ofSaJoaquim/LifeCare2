package com.example.casa.lifecare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.casa.lifecare.adptador.TimeLineAdptador;
import com.example.casa.lifecare.entidade.TimeLine;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Teste1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste1);
/*
        ListView lista = (ListView) findViewById(R.id.lista);
        List<TimeLine> linhas = todosOsCursos();
        TimeLineAdptador adapter = new TimeLineAdptador(linhas, this);
        lista.setAdapter(adapter);
    }

    /**
     * Exemplo qualquer de devolução de uma lista de cursos.
     * Para esse exemplo será considerado um hard coded.
     *
     * @return lista com todos os cursos

    private List<TimeLine> todosOsCursos() {
        return new ArrayList<>(Arrays.asList(
                new TimeLine("Você já foi no banheiro hoje?"
                        , 0x7f07005d, "Perigos da falta de fibra na alimentação"),
                new TimeLine("Você já foi no banheiro hoje?"
                        , 0x7f07005d, "Perigos da falta de fibra na alimentação"),
                new TimeLine("Você já foi no banheiro hoje?"
                        , 0x7f07005d, "Perigos da falta de fibra na alimentação")));*/
    }


}


package com.example.casa.lifecare;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_principal); // get the reference of Toolbar
        setSupportActionBar(toolbar); // Setting/replace toolbar as the ActionBar
        toolbar.setTitle("");
       ListView lista = (ListView) findViewById(R.id.lista1);
        List<TimeLine> linhas = todosOsCursos();
        TimeLineAdptador adapter = new TimeLineAdptador(linhas, this);
        lista.setAdapter(adapter);
    }

    /*
     * Exemplo qualquer de devolução de uma lista de cursos.
     * Para esse exemplo será considerado um hard coded.
     *
     * @return lista com todos os cursos*/

    private List<TimeLine> todosOsCursos() {

        return new ArrayList<>(Arrays.asList(
                new TimeLine("Depressão: sintomas, causas, tratamento e tem cura? "
                        ,R.drawable.depressao , "A depressão é um distúrbio afetivo que acompanha a humanidade ao longo de sua história. Pessoas que sofrem com distúrbios de depressão apresentam uma" +
                        " tristeza profunda, perda de interesse generalizado, falta de ânimo, de apetite, ausência de prazer e oscilações de humor que podem culminar em pensamentos suicidas."),
                new TimeLine("A importância da atividade física no dia-a-dia"
                        , R.drawable.atividadesbeneficios, "A melhor maneira de controlar o peso é a combinação de boa alimentação com exercícios físicos e não apenas um ou outro. O ideal é fazer um pouco de atividade física todos os dias, ou pelo menos três vezes por semana. Você não precisa ficar várias horas fazendo exercícios e suando sem parar. \"Pegar pesado\" é para atletas. As crianças, assim como as pessoas em geral, devem procurar uma atividade que lhes agrade," +
                        " convidar um amigo para participar... O professor de Educação Física é a pessoa ideal para orientar sobre o assunto. Ficar parado é que não dá!"),
                new TimeLine("Benefícios de uma alimentação saudável"
                        , R.drawable.mulhersaudavel, "A alimentação saudável é uma das melhores " +
                        "maneiras de garantir qualidade de vida, porque faz nosso corpo funcionar de forma adequada e também ajuda na prevenção de doenças.")));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.addRemedio:
              proximoFormulario();
                return true;
            case R.id.meusRemedios:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void proximoFormulario(){
        Intent intent = new Intent(this, CadastroRemedio.class);
        startActivity(intent);

    }
}


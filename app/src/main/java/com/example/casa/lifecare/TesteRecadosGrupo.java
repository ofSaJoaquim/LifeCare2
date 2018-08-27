package com.example.casa.lifecare;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class TesteRecadosGrupo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teste_recados_grupo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar2); // get the reference of Toolbar
        setSupportActionBar(toolbar); // Setting/replace toolbar as the ActionBar
        toolbar.setTitle("");
        final Button proximo = (Button) findViewById(R.id.button3);
        proximo.setOnClickListener(new  View.OnClickListener(){
            @Override
            public  void  onClick(View v){
               /* Toast.makeText(Formulario_Primeiro.this, "Hello World",
                        Toast.LENGTH_LONG).show();*/
                proximoFormulario();

            }
        } );

    }
    //infla o menu no toolbar teste
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }
    public void proximoFormulario(){
        Intent intent = new Intent(this, CadastroRemedio.class);
        startActivity(intent);

    }
}

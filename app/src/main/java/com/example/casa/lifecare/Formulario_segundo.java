package com.example.casa.lifecare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Formulario_segundo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_segundo);
        final Button proximo = (Button) findViewById(R.id.botaoFormulario);
        proximo.setOnClickListener(new  View.OnClickListener(){
            @Override
            public  void  onClick(View v){
               /* Toast.makeText(Formulario_Primeiro.this, "Hello World",
                        Toast.LENGTH_LONG).show();*/
                proximoFormulario();

            }
        } );
    }
    public void proximoFormulario(){
        Intent intent = new Intent(this, TesteRecadosGrupo.class);
        startActivity(intent);

    }
}

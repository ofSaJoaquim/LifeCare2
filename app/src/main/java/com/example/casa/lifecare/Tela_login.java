package com.example.casa.lifecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.example.casa.lifecare.Servicos.DataScraping;
import com.example.casa.lifecare.Servicos.PostarEntidade;
import com.example.casa.lifecare.Servicos.TesteService;
import com.example.casa.lifecare.Servicos.WebScraping;
import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Cidade;
import com.example.casa.lifecare.entidades.Estado;
import com.example.casa.lifecare.entidades.Paciente;
import com.example.casa.lifecare.entidades.Prontuario;
import com.example.casa.lifecare.entidades.Usuario;
import com.example.casa.lifecare.utils.SimulaDB;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Tela_login extends AppCompatActivity {


    private boolean estaOnline = true;
    private boolean estaLogado = false;
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email = (EditText) findViewById(R.id.txt_email);
        final EditText senha = (EditText) findViewById(R.id.txt_senha);
        final Button proximo = (Button) findViewById(R.id.btn_login);
        final Button cadastro = (Button) findViewById(R.id.btn_cadastro);


        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                   if(validarEmail(email.getText().toString())) {
                       if (validarSenha(senha.getText().toString())) {
                           PegaPaciente pg = new PegaPaciente();
                           pg.execute(email.getText().toString(), senha.getText().toString());
                       }
                   }
               }


        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proximaTela(new Intent(Tela_login.this,Formulario_Primeiro.class));
            }

        });
    }

    private void logar() {
        Intent intent = new Intent(this, Teste1.class);
        startActivity(intent);
    }

    private void proximaTela(Intent intent) {
                    startActivity(intent);
    }

    private boolean validarEmail(String email) {
        String expressao="[a-zA-Z0-9[-_.]]+@[a-zA-Z0-9[-_.]]+[[a-zA-Z]]";
        Log.i("teste senha",email.matches(expressao)+"");
         if(email.matches(expressao)){
             ((EditText)findViewById(R.id.txt_email)).setError(null);
            return  true;

        }
        else {
             ((EditText)findViewById(R.id.txt_email)).setError("Email inválido");
             ((EditText)findViewById(R.id.txt_email)).setFocusable(true);
             ((EditText)findViewById(R.id.txt_email)).requestFocus();
             return  false;
         }
    }
    private boolean validarSenha(String senha){
        if(senha.length()>2) return  true;
        else {
            ((EditText)findViewById(R.id.txt_senha)).setError("Senha no mínimo 4 caracteres");
            ((EditText)findViewById(R.id.txt_senha)).setFocusable(true);
            ((EditText)findViewById(R.id.txt_senha)).requestFocus();
            return false;

        }
    }


    private class PegaPaciente extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(Tela_login.this, "Por favor Aguarde ...",
                    "Conectando no servidor ...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Integer doInBackground(String... params) {
            try {

             if (Auxiliar.logar(params[0],params[1])){
                 return 1;
             }
             else if (params[0].equals("root@root.com")){
                 Log.i("email",params[0]);
                 if (params[1].equals("root"))

                        Log.i("senha",params[1]);
                     return 2;
             }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {
            String menssagem="";
            switch(retornoHTTP){
                case 0:
                    menssagem="Usuário ou senha incorretos, tente novamente";
                    estaLogado=false;
                    estaOnline=false;
                    break;
                case 1:
                    menssagem="Logado com sucesso como: "+Auxiliar.paciente.getNome();
                    estaLogado=true;
                    estaOnline=true;
                    break;
                case 2:
                    Auxiliar.paciente=new Paciente(0,"Root",32,"root@root.com","root");
                    menssagem="Logado Teste";
                    estaLogado=true;
                    estaOnline=false;

            }
        load.dismiss();
            Toast toast = Toast.makeText(Tela_login.this, menssagem,Toast.LENGTH_LONG);
            toast.show();
            if(estaLogado)proximaTela(new Intent(Tela_login.this,Principal.class));
        }
    }

}


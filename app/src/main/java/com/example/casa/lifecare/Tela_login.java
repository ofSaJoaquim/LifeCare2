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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.example.casa.lifecare.Servicos.PostarEntidade;
import com.example.casa.lifecare.Servicos.TesteService;
import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Cidade;
import com.example.casa.lifecare.entidades.Paciente;
import com.example.casa.lifecare.entidades.Prontuario;
import com.example.casa.lifecare.entidades.Usuario;
import com.example.casa.lifecare.utils.SimulaDB;

import java.util.ArrayList;
import java.util.List;

public class Tela_login extends AppCompatActivity {
    private Paciente paciente = null;

    private boolean estaOnline = true;
    private boolean estaLogado = false;
    private String senhaP = "";
    private ProgressDialog load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SimulaDB.usuario = new Usuario("root", "root");
        TesteService.context = this;
        //startService(new Intent(getBaseContext(), TesteService.class));
        // TesteNofiticacao.notify(this,"teste",1);
Paciente p = new Paciente();
Auxiliar.prontuario=new Prontuario(p);
        final EditText usuario = (EditText) findViewById(R.id.txt_email);
        final EditText senha = (EditText) findViewById(R.id.txt_senha);
        final Button proximo = (Button) findViewById(R.id.btn_login);
        final Button cadastro = (Button) findViewById(R.id.btn_cadastro);
        String usuarioTXT = usuario.getText().toString();
        senhaP = senha.getText().toString();
        PegaPaciente pg = new PegaPaciente();
        //pg.execute(usuarioTXT, senhaP);


        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuarioTXT = usuario.getText().toString();
                senhaP = senha.getText().toString();
                PegaPaciente pg = new PegaPaciente();
                pg.execute(usuarioTXT, senhaP);

               /*  String testes = teste();
                 usuario.setText(testes);
                if(usuarioTXT.equals(SimulaDB.usuario.getEmail())){

                    if(senhaTXT.equals(SimulaDB.usuario.getSenha())){
                        logar();
                    }
                    else{
                        Toast.makeText(Tela_login.this, "Password is wrong",
                                Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(Tela_login.this, "User doesn't exist",
                            Toast.LENGTH_LONG).show();
                }*/

            }

        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }

        });
    }

    private void logar() {
        Intent intent = new Intent(this, Teste1.class);
        startActivity(intent);
    }

    private void cadastrar() {
        //Intent intent = new Intent(this, CadastroUsuario.class);
        Intent intent = new Intent(this, Formulario_Primeiro.class);
        startActivity(intent);
    }

    private String teste() {
        if (paciente != null) return paciente.getNome();
        else return "nada";
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

             Auxiliar.logar("carlos@gmail.com","123");

            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {

        load.dismiss();
        }
    }

}


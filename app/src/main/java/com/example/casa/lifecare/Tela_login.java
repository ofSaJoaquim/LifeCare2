package com.example.casa.lifecare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.casa.lifecare.entidade.TimeLine;
import com.example.casa.lifecare.entidades.Usuario;
import com.example.casa.lifecare.utils.SimulaDB;

public class Tela_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SimulaDB.usuario = new Usuario("root", "root");

        final EditText usuario = (EditText) findViewById(R.id.txt_email);
        final EditText senha = (EditText) findViewById(R.id.txt_senha);
        final Button proximo = (Button) findViewById(R.id.btn_login);
        final Button cadastro = (Button) findViewById(R.id.btn_cadastro);

        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuarioTXT = usuario.getText().toString();
                String senhaTXT = senha.getText().toString();

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
                }

            }

        });

        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }

        });
    }

private void logar(){
    Intent intent = new Intent(this, Teste1.class);
    startActivity(intent);
}
private void cadastrar(){
    Intent intent = new Intent(this, CadastroUsuario.class);
    startActivity(intent);
}
}

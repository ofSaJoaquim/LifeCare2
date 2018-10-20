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
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.casa.lifecare.Servicos.PostarEntidade;
import com.example.casa.lifecare.entidades.Cidade;
import com.example.casa.lifecare.entidades.Paciente;




public class CadastroUsuario extends AppCompatActivity {
    ProgressDialog load;
    Paciente paciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        paciente = new Paciente();
        final EditText nome = findViewById(R.id.txtCadNome);
        final EditText senha = findViewById(R.id.txtCadSenha);
        final EditText email = findViewById(R.id.txtCadEmail);
        final RadioButton masc = findViewById(R.id.rdMasc);
        final EditText idade =  findViewById(R.id.txtCadIdade);


        final Button proximo = (Button) findViewById(R.id.cadUsuarioConfirma);
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int count=0;count<1;count++) {
                    if (nome.getText().toString().length() < 1) {
                        nome.setError("Campo obrigatório");
                        nome.setFocusable(true);
                        nome.requestFocus();
                        break;
                    } else if (!validarNome(nome.getText().toString())) {
                        nome.setError("Nome inválido");
                        nome.setFocusable(true);
                        nome.requestFocus();
                        break;
                    } else {
                        paciente.setNome(nome.getText().toString());

                    }

                    //Válida a idade
                    if (idade.getText().toString().length() < 1) {
                        idade.setError("Campo obrigatório");
                        idade.setFocusable(true);
                        idade.requestFocus();
                        break;
                    } else if (!(validarIdade(idade.getText().toString()))) {
                        idade.setError("Idade inválida");
                        idade.setFocusable(true);
                        idade.requestFocus();
                        break;
                    } else {
                        try {
                            Integer idadec = Integer.parseInt(idade.getText().toString());
                            if (idadec < -1) {
                                idade.setError("Idade não pode ser negativo");
                                idade.setFocusable(true);
                                idade.requestFocus();
                                break;
                            } else if (idadec > 150) {
                                idade.setError("Você tem 150 anos, ligue para a central");
                                idade.setFocusable(true);
                                idade.requestFocus();
                                break;
                            } else paciente.setIdade(idadec);

                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }

                    if (email.getText().toString().length() < 1) {
                        email.setError("Campo obrigatório");
                        email.setFocusable(true);
                        email.requestFocus();
                        break;
                    }
                    else if(!validarEmail(email.getText().toString())){
                        email.setError("Email inválido");
                        email.setFocusable(true);
                        email.requestFocus();
                        break;
                    }
                    else{
                        paciente.setEmail(email.getText().toString());
                    }
                    if(senha.getText().toString().length()<4){
                        email.setError("Senha no mínimo 4 caracteres");
                        email.setFocusable(true);
                        email.requestFocus();
                        break;
                    }
                    else {
                        paciente.setEmail(senha.getText().toString());
                    }
                    cadastrar();



                }

            }
        });
    }
private boolean validarNome(String nome ){
        String expressao="[a-zA-Z[-]]+[ ][a-zA-Z0-9[-][ ]]+";
        return nome.matches(expressao);

}

private boolean validarIdade(String idade){
    String expressao="[0-9]+";
    return idade.matches(expressao);
}

private boolean validarEmail(String email){
        String expressao="[a-zA-z0-9[-_.]]+@[a-zA-z0-9[-_.]]+[[a-zA-z]]";
        return email.matches(expressao);
}



    private void proximoForm(){
        Intent intent = new Intent(this, Formulario_Primeiro.class);
        startActivity(intent);
    }
    private void cadastrar(){



        PostaPaciente pp = new PostaPaciente();
       pp.execute();

    }

    private class PostaPaciente extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " +
                    Thread.currentThread().getName());
            load = ProgressDialog.show(CadastroUsuario.this, "Por favor Aguarde ...",
                    "Conectando no servidor ...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Integer doInBackground(String... params) {
            try {

                PostarEntidade pe = new PostarEntidade();
                Integer retornoHTTP=pe.postar("pacientes","nome",paciente.getNome(),"idade",paciente.getIdade()+"","email",paciente.getEmail());


                return  retornoHTTP;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {
            String menssagem="";
            if (retornoHTTP == 201) {
                menssagem="Cadastrado com sucesso";
                }
             else {

                menssagem="Não foi possivel conectar ao sistema, verifique sua Internet";
            }

            Toast toast = Toast.makeText(CadastroUsuario.this, menssagem, Toast.LENGTH_SHORT);
            toast.show();
            load.dismiss();

        }
    }
}

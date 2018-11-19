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

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.casa.lifecare.Servicos.PostarEntidade;
import com.example.casa.lifecare.Servicos.WebService;
import com.example.casa.lifecare.entidades.Auxiliar;
import com.example.casa.lifecare.entidades.Cidade;
import com.example.casa.lifecare.entidades.Estado;
import com.example.casa.lifecare.entidades.Paciente;
import com.google.gson.Gson;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;


public class CadastroUsuario extends AppCompatActivity {
    ProgressDialog load;
    Paciente paciente;
    Spinner SpEstados;
    Spinner SpCidades;
    Cidade cidade;
    Estado estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        paciente = new Paciente();
        SpEstados=findViewById(R.id.estadoEspiner);
        SpCidades=findViewById(R.id.cidadeEspiner);
        PegarEstados pe = new PegarEstados();
        pe.execute();
        final EditText nome = findViewById(R.id.txtCadNome);
        final EditText senha = findViewById(R.id.txtCadSenha);
        final EditText email = findViewById(R.id.txtCadEmail);
        final RadioButton masc = findViewById(R.id.rdMasc);
        masc.setSelected(true);

        final RadioButton fem = findViewById(R.id.rdFem);
        final RadioGroup grupo = findViewById(R.id.radioGroupCadSexo);
        masc.setSelected(true);
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
                        senha.setError("Senha no mínimo 4 caracteres");
                        senha.setFocusable(true);
                        senha.requestFocus();
                        break;
                    }
                    else {
                        paciente.setSenha(senha.getText().toString());
                    }
                    cidade.setUf(estado);

                    paciente.setCidade(cidade);
                    String sexo="F";
                    if(masc.isChecked())sexo="M";
                    paciente.setSexo(sexo);
                    cadastrar();



                }

            }
        });
        SpEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                //int pos = parent.getItemAtPosition(posicao).toString();
                int pos = parent.getSelectedItemPosition();
                //imprime um Toast na tela com o nome que foi selecionado
                if (Auxiliar.estados.length > 0) {
                    Log.i("teste50",Auxiliar.estados.length+"");

                    estado = Auxiliar.estados[pos];
                    Log.i("teste60",estado.getNome());
                    PegarCidades pc = new PegarCidades();
                    if (estado != null) pc.execute();

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
    private void voltarForm(){
        Intent intent = new Intent(this, Tela_login.class);
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

               // PostarEntidade pe = new PostarEntidade();
               // Integer retornoHTTP=pe.postar("pacientes","nome",paciente.getNome(),"idade",paciente.getIdade()+"","email",paciente.getEmail());

                //Gson gson = new Gson();
               // String parametros =gson.toJson(paciente);
                //int teste = WebService.postar(parametros);
               int retorno= Auxiliar.postarPaciente(paciente);
               if(retorno == 201)
                   Auxiliar.logar(paciente.getEmail(),paciente.getSenha());

                return  201;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {
            String menssagem="";
            if (retornoHTTP == 201) {

                load.dismiss();
                menssagem="Cadastrado com sucesso Nome: "+Auxiliar.paciente.getNome()+"--ID: "+Auxiliar.paciente.getId();
                Toast toast = Toast.makeText(CadastroUsuario.this, menssagem, Toast.LENGTH_SHORT);
                toast.show();

                proximoForm();
                }
             else {
                load.dismiss();
                menssagem=Auxiliar.falhaServidorIndisponivel;
                Toast toast = Toast.makeText(CadastroUsuario.this, menssagem, Toast.LENGTH_SHORT);
                toast.show();

                voltarForm();

            }



        }
    }

    private class PegarEstados extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
                     load = ProgressDialog.show(CadastroUsuario.this, "Por favor Aguarde ...",
                    "Conectando no servidor ...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Integer doInBackground(String... params) {
            try {
                Auxiliar.carregarEstados();


            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("Estados",WebService.httpStatus+"");

            return WebService.httpStatus;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {
            String menssagem="";
            if(retornoHTTP==201) {
                List<String> estadosNome = new ArrayList<String>();

                for (Estado estado : Auxiliar.estados) {
                    estadosNome.add(estado.getNome());
                }
                Log.i("teste20", estadosNome.toString());
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CadastroUsuario.this, android.R.layout.simple_spinner_dropdown_item, estadosNome);
                ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpEstados.setAdapter(spinnerArrayAdapter);
                load.dismiss();
            }
            if(retornoHTTP==-1){
                load.dismiss();
                menssagem=Auxiliar.falhaServidorIndisponivel;
                Toast toast = Toast.makeText(CadastroUsuario.this, menssagem, Toast.LENGTH_SHORT);
                toast.show();

                voltarForm();
            }




        }
    }

    private class PegarCidades extends AsyncTask<String, Void, Integer> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(CadastroUsuario.this, "Por favor Aguarde ...",
                    "Conectando no servidor ...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Integer doInBackground(String... params) {
            try {
                Auxiliar.carregarCidades(estado);
                Log.i("teste53",Auxiliar.cidades.length+"");

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("Cidade",WebService.httpStatus+"");
            return WebService.httpStatus;
        }

        @Override
        protected void onPostExecute(Integer retornoHTTP) {
            String menssagem="";
            if(retornoHTTP==201) {
                List<String> nomes = new ArrayList<String>();

                for (Cidade cidade : Auxiliar.cidades) {
                    nomes.add(cidade.getNome());
                }
                Log.i("teste30", nomes.toString());
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(CadastroUsuario.this, android.R.layout.simple_spinner_dropdown_item, nomes);
                ArrayAdapter<String> spinnerArrayAdapter = arrayAdapter;
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                SpCidades.setAdapter(spinnerArrayAdapter);
                cidade = Auxiliar.cidades[0];
                load.dismiss();
            }
            if(retornoHTTP==-1){
                load.dismiss();
                menssagem=Auxiliar.falhaServidorIndisponivel;
                Toast toast = Toast.makeText(CadastroUsuario.this, menssagem, Toast.LENGTH_SHORT);
                toast.show();

                voltarForm();
            }






        }
    }
}

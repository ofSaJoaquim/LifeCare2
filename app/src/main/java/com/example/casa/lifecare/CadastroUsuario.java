package com.example.casa.lifecare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.casa.lifecare.Servicos.CarregarEntidade;
import com.example.casa.lifecare.Servicos.PostarEntidade;
import com.example.casa.lifecare.entidades.Cidade;
import com.example.casa.lifecare.entidades.Paciente;
import com.example.casa.lifecare.entidades.Usuario;
import com.example.casa.lifecare.utils.SimulaDB;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CadastroUsuario extends AppCompatActivity {
    ProgressDialog load;
    Paciente paciente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        final EditText nomeCampo = (EditText) findViewById(R.id.txtCadNome);

        final EditText senhaCampo = (EditText) findViewById(R.id.txt_senha);
        final EditText emailCampo = (EditText) findViewById(R.id.txtCadEmail);
        final EditText cpfCampo = (EditText) findViewById(R.id.txtCadCPF);
        final EditText telefoneCampo = (EditText) findViewById(R.id.txtCadTelefone);


        final RadioButton masc = (RadioButton)findViewById(R.id.rdMasc);


        final RadioButton solteiro = (RadioButton)findViewById(R.id.rdSolteiro);
        final RadioButton casado = (RadioButton)findViewById(R.id.rdCasado);
        final RadioButton divorciado = (RadioButton)findViewById(R.id.rdDivorciado);
        final RadioButton separado = (RadioButton)findViewById(R.id.rdSeparado);
        final RadioButton viuvo = (RadioButton)findViewById(R.id.rdViuvo);

        final EditText dataNascimentoCampo = (EditText) findViewById(R.id.txtCadNasc);


        final Button proximo = (Button) findViewById(R.id.cadUsuarioConfirma);
        proximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Toast.makeText(Formulario_Primeiro.this, "Hello World",
                        Toast.LENGTH_LONG).show();*/
                //tem que criar uma validação antes de inserir
                String nome = nomeCampo.getText().toString();
                String usuarioLogin = emailCampo.getText().toString();
                String senha = nomeCampo.getText().toString();
                String email = emailCampo.getText().toString();
               String cpf = cpfCampo.getText().toString();
               Long telefone = Long.parseLong(telefoneCampo.getText().toString());

                Boolean sexo=false;
                if(masc.isSelected())sexo=true;
                Integer estadoCivil = 0;
                if(casado.isSelected())estadoCivil=1;
                else if(divorciado.isSelected())estadoCivil=2;
                else if(separado.isSelected())estadoCivil=3;
                else if(viuvo.isSelected())estadoCivil=4;

                Integer dataNascimento[] = converteData(dataNascimentoCampo);
                Integer diaNascimento = dataNascimento[0];
                Integer mesNascimento = dataNascimento[1];
                Integer anoNascimento = dataNascimento[2];
                // SimulaDB.usuario = new Usuario(nome,usuarioLogin,senha,email,cpf,telefone,sexo,estadoCivil,diaNascimento,mesNascimento,anoNascimento);
                Cidade cidade=null;
                cadastrar(nome,2018-dataNascimento[2],email,senha,cidade);

                //string xml adicionar
               /* Toast.makeText(CadastroUsuario.this, "Congratulations",
                        Toast.LENGTH_LONG).show();*/
                //proximoForm();

            }
        });
    }


    //metodo para testar entrada da data de nascimento, melhorar usando outra classe propria para data e consertar a tradução
    private Integer[] converteData(EditText dataorigem) {
        String dataTexto = dataorigem.getText().toString();
        Integer data[] = new Integer[3];
        if (dataTexto.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")) {
            String dataSepara[] = dataTexto.split("/");

            data[0] = Integer.parseInt(dataSepara[0]);
            data[1] = Integer.parseInt(dataSepara[1]);
            data[2] = Integer.parseInt(dataSepara[2]);
         /*   Toast.makeText(CadastroUsuario.this, data[0] + "/" + data[1] + "/" + data[2],
                    Toast.LENGTH_LONG).show();*/

            return data;
        } else {

            Integer teste[]={22,12,2012};
            return teste;
        }

    }
    private void proximoForm(){
        Intent intent = new Intent(this, Formulario_Primeiro.class);
        startActivity(intent);
    }
    private void cadastrar(String nome, Integer idade, String email, String senha, Cidade cidade){
        cidade = new Cidade(1,"Tubarão","Santa Catarina");

        paciente = new Paciente(nome,idade,email,senha,cidade);
        PostaPaciente pp = new PostaPaciente();

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

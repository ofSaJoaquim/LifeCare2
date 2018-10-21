package com.example.casa.lifecare.entidades;

import android.util.Log;

import com.example.casa.lifecare.Servicos.CarregarEntidade;
import com.example.casa.lifecare.Servicos.HttpRetorno;
import com.example.casa.lifecare.Servicos.WebService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Auxiliar {
    public static Paciente paciente;
    public static Cidade cidades[];
    public static Estado estados[];
    public static String chave;
    public static FormularioPaciente formularioPaciente;
    public static Chat chat;
    public static String servidor="https://lifecare-unisul.herokuapp.com/";


    public static void carregarEstados() {
        Gson gson = new Gson();
        estados = gson.fromJson(WebService.listarEntidades("estados"), Estado[].class);


    }

    public static void carregarCidades(Estado estado) {
        Gson gson = new Gson();
        StringBuilder parametros = new StringBuilder();
        parametros.append("estados/");
        parametros.append(estado.getId());
        parametros.append("/cidades");
        cidades = gson.fromJson(WebService.listarEntidades(parametros.toString()), Cidade[].class);


    }


    public static void logar(String email, String senha) {
       HttpRetorno httpRetorno = WebService.postar("login",true,false, "email", email,"senha",senha);
        Log.i("teste post login",httpRetorno.getHeader().get("Authorization")+"");
        chave =httpRetorno.getHeader().get("Authorization").toString();
        chave=chave.substring(8,chave.length()-1);
        Log.i("Chave:", chave);
        CarregarEntidade ce = new CarregarEntidade();
        ce.pegarEntidade();
    }

    public static int postarPaciente(Paciente paciente){
        int retorno =WebService.postar("pacientes","nome",paciente.getNome(),"idade",paciente.getIdade().toString(),"email",paciente.getEmail(),"senha",paciente.getSenha(),
                "cidadeId",paciente.getCidade().getId().toString());
        return retorno;
    }

}

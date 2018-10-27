package com.example.casa.lifecare.entidades;

import android.util.Log;

import com.example.casa.lifecare.Servicos.CarregarEntidade;
import com.example.casa.lifecare.Servicos.HttpRetorno;
import com.example.casa.lifecare.Servicos.WebService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Auxiliar {
    public static Paciente paciente;
    public static Cidade cidades[];
    public static Estado estados[];
    public static String chave;
    public static Prontuario prontuario;

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


    public static boolean logar(String email, String senha) {
        try{
       HttpRetorno httpRetorno = WebService.postar("login",true,false, "email", email,"senha",senha);
            Log.i("httpretorno",WebService.httpStatus+"");
       if(WebService.httpStatus==200) {
           Log.i("teste post login", httpRetorno.getHeader().get("Authorization") + "");

           chave = httpRetorno.getHeader().get("Authorization").toString();
           chave = chave.substring(8, chave.length() - 1);
           Log.i("Chave:", chave);

           //Tenta achar a entidade por email
           String entidade = WebService.pegarEntidade("pacientes","/",email);
           if(WebService.httpStatus==200) {
               Auxiliar.paciente = new Gson().fromJson(entidade.toString(), Paciente.class);
               return true;
           }
           //Tenta achar a entidade baixando uma lista
           else {
               Gson gson = new Gson();
               StringBuilder parametros = new StringBuilder();
               parametros.append("pacientes");
               Paciente pacientes[]= gson.fromJson(WebService.listarEntidades(parametros.toString()), Paciente[].class);
              // if(WebService.httpStatus==200) {
                   for (Paciente p : pacientes) {
                       if (p.getEmail().equals(email)) {
                           Auxiliar.paciente = p;
                           return true;
                       }
                   }
              // }

           }
       }
       return false;
       }
        catch (NullPointerException e){
            Log.i("Erro Auxiliar Logar",e.getMessage());
            return  false;
        }
    }

    public static int postarPaciente(Paciente paciente){
        int retorno =WebService.postar("pacientes","nome",paciente.getNome(),"idade",paciente.getIdade().toString(),"email",paciente.getEmail(),"senha",paciente.getSenha(),
                "cidadeId",paciente.getCidade().getId().toString());
        return retorno;
    }

}

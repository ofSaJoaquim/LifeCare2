package com.example.casa.lifecare.entidades;

import android.util.JsonReader;
import android.util.Log;

import com.example.casa.lifecare.Servicos.CarregarEntidade;
import com.example.casa.lifecare.Servicos.HttpRetorno;
import com.example.casa.lifecare.Servicos.WebService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Auxiliar {
    public static String falhaServidorIndisponivel="Servidor indisponível, verifique sua Internet e tente mais tarde!";
    public static Paciente paciente;
    public static Cidade cidades[];
    public static Estado estados[];
    public static String chave;
    public static Prontuario prontuario;
    public static List<Mensagem>mensagems;
    public static Chat chat;
    public static List<Medicamento>meusMedicamentos;

    //public  static  String  servidor="https://lifecare-unisul.herokuapp.com/";
    public static  String servidor ="http://192.168.0.2:8082/";

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
                       Log.i("Paciente",p.getNome());
                       if (p.getEmail().equals(email)) {
                           Auxiliar.paciente = p;
                           if (carregarProntuario())return true;
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
                "cidadeId",paciente.getCidade().getId().toString(),"sexo",paciente.getSexo());
        return retorno;
    }

    public static boolean criarChat(){
        /* Falta implementar testar se tem chat criado
        Gson gson = new Gson();
        StringBuilder parametros = new StringBuilder();
        parametros.append("chats");
        Chat pacientes[]= gson.fromJson(WebService.listarEntidades(parametros.toString()), Chat[].class); /*/
       return true;
    }
   public static boolean carregarMenssagens(){
        try {
           /* GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setLenient();
            Gson gson = gsonBuilder.create();*/
           Gson gson = new Gson();

            StringBuilder paramentros = new StringBuilder();
            paramentros.append("chats/");
            paramentros.append(paciente.getId());
            paramentros.append("/mensagens");
            mensagems = new ArrayList<Mensagem>();
            TypeToken<List<Mensagem>> token = new TypeToken<List<Mensagem>>() { };
            Log.i("Recber menssagem",paramentros.toString());
           mensagems = gson.fromJson(WebService.listarEntidadesTeste(paramentros.toString()), token.getType());
            for(int count=0;count<mensagems.size();count++){
                Log.i("carregar medico",count+"");
                Log.i("tamanho me",mensagems.size()+"");
                Log.i("Medicoid",mensagems.get(count).getMedicoId()+"");
                if(mensagems.get(count).getMedicoId()!=null){
                    Log.i("Medicoid",mensagems.get(count).getMedicoId()+"");
                    mensagems.get(count).pegaMedico();

                }


            }




            chat.setMensagens(mensagems);
            if(mensagems.size()>0)return true;
            return false;
        }
        catch (Exception e){
            Log.i("Auxiliar C Menssagens",e.getMessage());
            return false;
        }
   }
   public static  boolean enviarMenssagens(String mensagem){
        try {
            StringBuilder entidade = new StringBuilder();
            entidade.append("mensagens/pacientes/");
            entidade.append(paciente.getId());
            HttpRetorno httpRetorno = WebService.postar(entidade.toString(), false, false, "texto", mensagem);
            if (WebService.httpStatus == 201){

                return true;
            }
            return false;
        }catch (Exception e){
            Log.i("Aux Enviar",e.getMessage());
            return  false;

        }
   }
public static boolean carregarMeusRemedios(){
        meusMedicamentos=new ArrayList<Medicamento>();
        meusMedicamentos.add(new Medicamento(1,20,"Gardenau","ANTI DEPESSIVO",true));
    meusMedicamentos.add(new Medicamento(2,0,"Prozac","ANTI DEPESSIVO",true));
    meusMedicamentos.add(new Medicamento(3,20,"Doril","Resolve tudo",true));
    return  true;
}

public static boolean carregarProntuario(){
        boolean retorno =false;
        try {
            Gson gson = new Gson();
            StringBuilder entidade = new StringBuilder();
            entidade.append("prontuarios");
            entidade.append("/");
            entidade.append("pacientes");
            entidade.append("/");
            entidade.append(paciente.getId());
            prontuario=gson.fromJson(WebService.pegarEntidadeSimples(entidade.toString()),Prontuario.class);
            Log.i("teste pront",prontuario.getPaciente().getId().toString());
            retorno = true;
        }

        catch (Exception e){
            Log.i("CProntuario",e.getMessage());
        }
        finally {
            return retorno;
        }

}

    public static  int adicionarRiscos(){
        int retorno=0;
        try {
            for(Risco risco: prontuario.getRiscos()) {
                StringBuilder entidade = new StringBuilder();
                entidade.append("prontuarios/pacientes/");
                entidade.append(paciente.getId());
                entidade.append("/riscos");
                HttpRetorno httpRetorno = WebService.postar(entidade.toString(), false, false, "nome", risco.getNome(),"intensidade",risco.getIntensidade().toString(),"tipo",risco.getTipo());
                retorno =WebService.httpStatus;
                if(WebService.httpStatus!=201){

                    break;
            }



            }

        }catch (Exception e){
            Log.i("Aux Enviar",e.getMessage());


        }
        finally {
            return retorno;
        }
    }
}

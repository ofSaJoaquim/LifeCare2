package com.example.casa.lifecare.entidades;

import com.example.casa.lifecare.Servicos.WebService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Auxiliar {
    public static Paciente paciente;
    public static Cidade cidades[];
    public static Estado estados[];
    public static FormularioPaciente formularioPaciente;
    public static Chat chat;


    public static void carregarEstados() {
    Gson gson = new Gson();
    estados =gson.fromJson(WebService.listarEntidades("estados"),Estado[].class);


}
    public static void carregarCidades(Estado estado){
        Gson gson = new Gson();
        StringBuilder parametros = new StringBuilder();
        parametros.append("estados/");
        parametros.append(estado.getId());
        parametros.append("/cidades");
        cidades =gson.fromJson(WebService.listarEntidades(parametros.toString()),Cidade[].class);



        }
    }



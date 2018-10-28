package com.example.casa.lifecare.entidades;

import android.util.Log;

import com.example.casa.lifecare.Servicos.WebService;
import com.google.gson.Gson;

import java.util.Date;

public class Mensagem {

    private Integer id;
    private String texto;
    private Chat chat;
    private Integer medicoId;
    private Integer pacienteId;
    private Medico medico;

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Mensagem(Integer id, String texto, Integer pacienteId, Integer medicoId) {
        super();
        this.id = id;
        this.texto = texto;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
    }
    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public Integer getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Integer pacienteId) {
        this.pacienteId = pacienteId;
    }


    public String getInstante() {
        return instante;
    }

    public void setInstante(String instante) {
        this.instante = instante;
    }

    private String instante ;
    private boolean tipo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean pegaMedico(){
        if((medicoId>0)&&(medicoId!=null)) {
            Log.i("mediocid",medicoId+"");
            String entidade = WebService.pegarEntidadeSimples("medicos/"+medicoId);
            Log.i("entidade", entidade);


                medico = new Gson().fromJson(entidade.toString(), Medico.class);
            Log.i("Medico",medico.getNome());
                return true;

        }
        else medico = new Medico("Nulo");


        return  false;
    }



}
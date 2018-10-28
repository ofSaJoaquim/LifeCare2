package com.example.casa.lifecare.entidades;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Chat {

    private Integer id;
    private Paciente paciente;
    private Medico medico;
    private List<Mensagem> mensagens;

    public Chat(Paciente paciente, Medico medico) {

        this.paciente = paciente;
        this.medico = medico;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        this.mensagens = mensagens;
    }
}

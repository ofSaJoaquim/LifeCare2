package com.example.casa.lifecare.entidades;

public class Menssagem {

    private Integer id;
    private String texto;
    private Medico medico;
    private Paciente paciente;
    private String hora;
    private boolean tipo;

    public Menssagem(Paciente paciente,String texto, String hora) {
        this.texto = texto;
        this.paciente = paciente;
        this.hora = hora;
        tipo=true;
    }

    public Menssagem(Medico medico, String texto, String hora) {
        this.texto = texto;
        this.medico = medico;
        this.hora = hora;
        tipo=false;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public Menssagem() {

    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getHora() {
        return hora;
    }
}

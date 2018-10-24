package com.example.casa.lifecare.entidades;

import java.util.List;

public class Prontuario {
   private Integer id;
   private Integer score;
   private Paciente paciente;
   private List<Risco> riscos;
   private List<LinhaDeCuidado>linhasDeCuidado;
   private List<Medicamento>medicamentos;

    public Prontuario(Paciente paciente) {
        this.paciente = paciente;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Risco> getRiscos() {
        return riscos;
    }

    public void setRiscos(List<Risco> riscos) {
        this.riscos = riscos;
    }

    public List<LinhaDeCuidado> getLinhasDeCuidado() {
        return linhasDeCuidado;
    }

    public void setLinhasDeCuidado(List<LinhaDeCuidado> linhasDeCuidado) {
        this.linhasDeCuidado = linhasDeCuidado;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
}

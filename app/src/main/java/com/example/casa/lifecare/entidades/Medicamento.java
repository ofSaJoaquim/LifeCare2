package com.example.casa.lifecare.entidades;

public class Medicamento {
    private Integer id;
    private Integer qtDias;
    private String nome;
    private String tipo;
    private Boolean continuo;
    private String admDeUso;


    public Medicamento() {
    }

    public Medicamento(Integer id, Integer qtDias, String nome, String tipo, Boolean continuo) {
        this.id = id;
        this.qtDias = qtDias;
        this.nome = nome;
        this.tipo = tipo;
        this.continuo = continuo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQtDias() {
        return qtDias;
    }

    public void setQtDias(Integer qtDias) {
        this.qtDias = qtDias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getContinuo() {
        return continuo;
    }

    public void setContinuo(Boolean continuo) {
        this.continuo = continuo;
    }

    public String getAdmDeUso() {
        return admDeUso;
    }

    public void setAdmDeUso(String admDeUso) {
        this.admDeUso = admDeUso;
    }
}

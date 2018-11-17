package com.example.casa.lifecare.entidades;

public class Risco {
    private Integer id;
    private String nome;
    private Integer intensidade;
    private String tipo;

    public Risco() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(Integer intensidade) {
        this.intensidade = intensidade;
    }

    public Risco(String nome, Integer intensidade, String tipo) {

        this.nome = nome;
        this.intensidade = intensidade;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



}

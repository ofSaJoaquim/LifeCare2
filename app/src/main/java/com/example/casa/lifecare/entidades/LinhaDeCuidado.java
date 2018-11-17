package com.example.casa.lifecare.entidades;

public class LinhaDeCuidado {
    private Integer id;
    private String acoes;
    private String titulo;

    public LinhaDeCuidado() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcoes() {
        return acoes;
    }

    public void setAcoes(String acoes) {
        this.acoes = acoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}

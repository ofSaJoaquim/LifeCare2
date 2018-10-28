package com.example.casa.lifecare.entidades;

public class Medico {

    private Integer id;
    private String nome;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Medico(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Medico(String nome) {

        this.nome = nome;

    }
}

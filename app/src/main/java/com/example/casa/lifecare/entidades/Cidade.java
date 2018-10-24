package com.example.casa.lifecare.entidades;

public class Cidade {
   private Integer id;
   private String nome;
   private  Estado uf;

    public Cidade() {
    }

    public Cidade(String nome, Estado uf) {

        this.nome = nome;
        this.uf = uf;
    }

    public Cidade(Integer id, String nome, Estado uf) {
        this.id = id;
        this.nome = nome;
        this.uf = uf;
    }

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

    public Estado getUf() {
        return uf;
    }

    public void setUf(Estado uf) {
        this.uf = uf;
    }


}

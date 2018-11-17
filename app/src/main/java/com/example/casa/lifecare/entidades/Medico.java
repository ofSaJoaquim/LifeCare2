package com.example.casa.lifecare.entidades;

import java.util.List;

public class Medico {

    private Integer id;
    private String nome;
    private List<String>especialidades;

    public List<String> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<String> especialidades) {
        this.especialidades = especialidades;
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

    public Medico(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Medico(String nome) {

        this.nome = nome;

    }
}

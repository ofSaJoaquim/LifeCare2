package com.example.casa.lifecare.entidades;

import com.example.casa.lifecare.enums.Perfil;
import com.google.gson.annotations.Expose;

public class Paciente {
    private Integer id;
    private String nome;
    private Integer idade;
    private String email;
    private String senha;
    private Cidade cidade;
    private Medico medico;
    private String sexo;

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    private Perfil perfil;


    public Paciente() {
        perfil=Perfil.PACIENTE;
    }



    public Paciente(String nome, Integer idade, String email, String senha, Cidade cidade) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        this.cidade = cidade;
        perfil=Perfil.PACIENTE;
    }

    public Paciente(Integer id, String nome, Integer idade, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.senha = senha;
        perfil=Perfil.PACIENTE;
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}

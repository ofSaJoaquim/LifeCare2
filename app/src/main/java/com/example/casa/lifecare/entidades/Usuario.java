package com.example.casa.lifecare.entidades;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

public class Usuario extends SugarRecord{


    private String nome;
    private String usuario;

    public Usuario(String senha, String email) {
        this.senha = senha;
        this.email = email;
    }

    private String senha;
    private String email;
    private String cpf;
    private Long telefone;
    private Boolean sexo;
    private Integer estadoCivil;
    private Integer diaNascimento;
    private Integer mesNascimento;
    private Integer anoNascimento;

    public Integer getDiaNascimento() {
        return diaNascimento;
    }

    public void setDiaNascimento(Integer diaNascimento) {
        this.diaNascimento = diaNascimento;
    }

    public Integer getMesNascimento() {
        return mesNascimento;
    }

    public void setMesNascimento(Integer mesNascimento) {
        this.mesNascimento = mesNascimento;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public Integer getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Integer estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", usuario='" + usuario + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                ", cpf=" + cpf +
                ", telefone=" + telefone +
                ", sexo=" + sexo +
                ", estadoCivil=" + estadoCivil +
                ", diaNascimento=" + diaNascimento +
                ", mesNascimento=" + mesNascimento +
                ", anoNascimento=" + anoNascimento +
                '}';
    }

    public Usuario(String nome, String usuario, String senha, String email, String cpf, Long telefone, Boolean sexo, Integer estadoCivil, Integer diaNascimento, Integer mesNascimento, Integer anoNascimento) {
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.diaNascimento = diaNascimento;
        this.mesNascimento = mesNascimento;
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;

    }

}

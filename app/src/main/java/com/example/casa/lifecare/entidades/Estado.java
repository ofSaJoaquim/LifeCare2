package com.example.casa.lifecare.entidades;

import java.util.List;

public class Estado {

    private Integer id;
    private String nome;
    private List<Cidade> cidades;

    public Estado() {

    }

    public Estado(String nome) {
        this.nome = nome;
    }

    public Estado(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
/*
    public void pegarCidades(){

    }
    public void testar(){
        Cidade cidade=null;
        if(nome.equals("Santa Catarina")){
            cidade = new Cidade(1,"Tubarão",this);
            cidade = new Cidade(2,"Criciuma",this);
            cidade = new Cidade(3,"Jaguaruna",this);
        }
        if(nome.equals("Rio Grande do Sul")){
            cidade = new Cidade(4,"Passo Fundo",this);
            cidade = new Cidade(5,"Pelotas",this);
            cidade = new Cidade(6,"Porto Alegre",this);
        }
        if(nome.equals("Paraná")){
            cidade = new Cidade(4,"Curitiba",this);
            cidade = new Cidade(5,"Ponta Grossa",this);
            cidade = new Cidade(6,"Foz do Iguaçu",this);
        }
    }
*/



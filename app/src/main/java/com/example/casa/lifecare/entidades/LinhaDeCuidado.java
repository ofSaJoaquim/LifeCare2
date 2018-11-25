package com.example.casa.lifecare.entidades;

import java.util.ArrayList;
import java.util.List;

public class LinhaDeCuidado {
    private Integer id;
    private String acoes;
    private String titulo;
    private List<Site>sites;


    public LinhaDeCuidado() {
        sites=new ArrayList<Site>();
    }

    public LinhaDeCuidado(String acoes, String titulo) {
        this.acoes = acoes;
        this.titulo = titulo;
        this.sites = new ArrayList<Site>();
    }

    public LinhaDeCuidado(String acoes, String titulo, Site site) {
        this.acoes = acoes;
        this.titulo = titulo;
        this.sites = new ArrayList<Site>();
        sites.add(site);
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
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

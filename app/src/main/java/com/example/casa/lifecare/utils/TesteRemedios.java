package com.example.casa.lifecare.utils;

public class TesteRemedios {
    private int IdRemedio;
    private String descricao;
    private  long intervalo;
    private long ultimaAplicacao;

    public TesteRemedios() {
    }

    public TesteRemedios(int idRemedio, String descricao, long intervalo) {
        IdRemedio = idRemedio;
        this.descricao = descricao;
        this.intervalo = intervalo;
    }

    public int getIdRemedio() {

        return IdRemedio;
    }

    public void setIdRemedio(int idRemedio) {
        IdRemedio = idRemedio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(long intervalo) {
        this.intervalo = intervalo;
    }

    public long getUltimaAplicacao() {
        return ultimaAplicacao;
    }

    public void setUltimaAplicacao(long ultimaAplicacao) {
        this.ultimaAplicacao = ultimaAplicacao;
    }
}

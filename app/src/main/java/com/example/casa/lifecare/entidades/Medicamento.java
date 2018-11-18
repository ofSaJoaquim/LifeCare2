package com.example.casa.lifecare.entidades;

import android.util.Log;

public class Medicamento {
    private Integer id;
    private Integer qtDias;
    private String nome;
    private String tipo;
    private Boolean continuo;
    private String admDeUso;
    private Boolean aguardaUso=false;
    private Integer reaviso=0;
    private Integer  muliplica=10;
    private Integer maxReaviso=2;
    private Integer reavisos=0;
    private long ultimoUso=0;
    private long intervaloUso=0;

    public Integer getReaviso() {
        return reaviso;
    }

    public void setReaviso(Integer reaviso) {
        this.reaviso = reaviso;
    }

    public Integer getMuliplica() {
        return muliplica;
    }

    public void setMuliplica(Integer muliplica) {
        this.muliplica = muliplica;
    }

    public Integer getMaxReaviso() {
        return maxReaviso;
    }

    public void setMaxReaviso(Integer maxReaviso) {
        this.maxReaviso = maxReaviso;
    }

    public Integer getReavisos() {
        return reavisos;
    }

    public void setReavisos(Integer reavisos) {
        this.reavisos = reavisos;
    }

    public Boolean getAguardaUso() {
        return aguardaUso;
    }

    public void setAguardaUso(Boolean aguardaUso) {
        this.aguardaUso = aguardaUso;
    }




    public Medicamento() {
    }

    public long getUltimoUso() {
        return ultimoUso;
    }

    public void setUltimoUso(long ultimoUso) {
        this.ultimoUso = ultimoUso;
    }

    public long getIntervaloUso() {
        return intervaloUso;
    }

    public void setIntervaloUso(long intervaloUso) {
        this.intervaloUso = intervaloUso;
    }

    public Medicamento(Integer id, Integer qtDias, String nome, String tipo, Boolean continuo) {
        this.id = id;
        this.qtDias = qtDias;
        this.nome = nome;
        this.tipo = tipo;
        this.continuo = continuo;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQtDias() {
        return qtDias;
    }

    public void setQtDias(Integer qtDias) {
        this.qtDias = qtDias;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getContinuo() {
        return continuo;
    }

    public void setContinuo(Boolean continuo) {
        this.continuo = continuo;
    }

    public String getAdmDeUso() {
        return admDeUso;
    }

    public void setAdmDeUso(String admDeUso) {
        this.admDeUso = admDeUso;
    }

    public Integer proximaDose(){
       Integer retorno = (int)((intervaloUso+ultimoUso)-System.currentTimeMillis());
        //Integer retorno = (int)intervaloUso;
       retorno=retorno/1000/60;
        return retorno;

    }
    public Boolean horaDeAplicar(){
        if(ultimoUso==0)return false;
        if((intervaloUso+ultimoUso)-System.currentTimeMillis()>=0)return true;
        return false;
    }
    public String horaDose(){
        if(ultimoUso==0)return "00:00";
        Integer dose = proximaDose();
        Integer horas = (int)dose/60;
        Integer minutos =(int)dose-(horas*60);
        StringBuilder retorno=new StringBuilder();
        if(horas<10)retorno.append(0);
        retorno.append(horas);
        retorno.append(":");
        if(minutos<10)retorno.append(0);
        retorno.append(minutos);

        return retorno.toString();
    }
    public void iniciaTempo(){
        if (ultimoUso == 0) {
            ultimoUso=System.currentTimeMillis();
            intervaloUso = (long) ((24 / qtDias) * 1000 * 60 * 60);
            StringBuilder sb = new StringBuilder();
            sb.append(intervaloUso / (1000 * 60 * 60));
            aplicar();
            Log.i("Tempo",nome+";;;"+ sb.toString());
        }
    }
    public void iniciaTempoTeste(){
        if (ultimoUso == 0) {
            ultimoUso=System.currentTimeMillis();
            intervaloUso = (long) ((24 / 1100) * 1000 * 60 * 60);
            StringBuilder sb = new StringBuilder();
            sb.append(intervaloUso / (1000 * 60 * 60));
            Log.i("Tempo",nome+";;;"+ sb.toString());
        }
    }
    public void aplicar(){
        ultimoUso=System.currentTimeMillis();
        aguardaUso=false;

    }
}

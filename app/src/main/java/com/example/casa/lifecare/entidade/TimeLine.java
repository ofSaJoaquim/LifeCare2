package com.example.casa.lifecare.entidade;

public class TimeLine {

    private String titulo;
    private Integer idImagem;
    private String noticia;
    private String link;

    public TimeLine(String titulo, Integer idImagem, String noticia) {
        this.titulo = titulo;
        this.idImagem = idImagem;
        this.noticia = noticia;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(Integer idImagem) {
        this.idImagem = idImagem;
    }

    public String getNoticia() {
        return noticia;
    }

    public void setNoticia(String noticia) {
        this.noticia = noticia;
    }


}

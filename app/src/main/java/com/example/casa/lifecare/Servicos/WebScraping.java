package com.example.casa.lifecare.Servicos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebScraping {


    private String titulo;
    private String urlImagem;
    private String resumo;
    private String site;
    private Bitmap imagem;

    public WebScraping(String site) {

        this.site = site;

    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getSite() {

        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public boolean pegaUrlFoto() {
        Document documento = null;

        {
            try {
                documento = Jsoup.connect(site).get();
                Elements img = documento.getElementsByTag("img");

                for (Element linha : img) {
                    String src = linha.absUrl("src");
                    if (src.contains(".jpg")) {
                        break;
                    }
                    Log.i("Imagem", src);
// Do something with the "row" variable.

                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

    }

    public boolean pegaTitulo() {
        Document documento = null;

        {
            try {
                documento = Jsoup.connect(site).get();
                Elements headings = documento.select("h0,h1");

                for (Element linha : headings) {
                    String ti = linha.text();
                    Log.i("Titulo", ti);
                    break;
// Do something with the "row" variable.

                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

    }

    public boolean pegaResumo() {
        Document documento = null;

        {
            try {
                documento = Jsoup.connect(site).get();
                Elements headings = documento.select("h2,h3");

                for (Element linha : headings) {
                    String ti = linha.text();
                    Log.i("Resumo", ti);

// Do something with the "row" variable.

                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

    }

    public boolean pegarMetaDados() {
        Document documento = null;

        {
            try {
                Log.i("site", site);
                documento = Jsoup.connect(site).get();
                String descricao = documento.select("meta[name=description]").get(0).attr("content");

                Log.i("Desc", descricao);

                //Get keywords from document object.
                String ogTitulo = documento.select("meta[property=og:title]").first().attr("content");
                //Print keywords.
                Log.i("Titulo", ogTitulo);
                String ogImagem = documento.select("meta[property=og:image]").first().attr("content");
                //Print keywords.
                Log.i("Imagem", ogImagem);
                //valida
                if (ogTitulo.length() > 0) titulo = ogTitulo;
                else titulo = "";
                if (descricao.length() > 0) resumo = descricao;
                else resumo = "";
                CharSequence jpg = ".jpg";
                CharSequence png = ".jpg";
                if (ogImagem.contains(jpg)) urlImagem = ogImagem;
                else if (ogImagem.contains(png)) urlImagem = ogImagem;
                else urlImagem = "";
                pegarImagem();

                return true;
            } catch (IOException e) {

                e.printStackTrace();
                return false;
            }

        }

    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public boolean pegarImagem() {
        try {
            URL url = new URL(urlImagem);
            Log.i("imagem url",urlImagem);

             imagem = BitmapFactory.decodeStream(url.openConnection().getInputStream());
          return true;

    } catch (IOException e) {
       Log.i("Erro",e.getMessage());
  return false;

    }

    }
}

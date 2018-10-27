package com.example.casa.lifecare.Servicos;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DataScraping {


    private String imagem;
    private String titutlo;



    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public DataScraping() {
    }

    public String getTitutlo() {
        return titutlo;
    }

    public void setTitutlo(String titutlo) {
        this.titutlo = titutlo;
    }

    public void scraping(){

        try {

          //URL  url = new URL("https://www.afolhahoje.com/diabetes-causas-sintomas-e-tratamentos/");
            URL  url = new URL("  https://www.iped.com.br/materias/odontologia/saude-bucal-funcionamento-organismo.html");
            URLConnection conn = url.openConnection();
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String linha = br.readLine();

            while (linha != null) {

            int pos1 = linha.indexOf("<");
                //Log.i("pos1",pos1+"");
            if(pos1>-1){
                //Log.i("sub",linha.substring(pos1,pos1+3));
                if(linha.substring(pos1,pos1+3).equalsIgnoreCase("<h1")){
                    int pos2 = linha.indexOf(">");
                    int pos3 = linha.lastIndexOf("<");
                    //Log.i("sub",linha.substring(pos2,pos3));
                    titutlo = linha.substring(pos2,pos3);
                    Log.i("titulo",titutlo);
                }
                else if(linha.substring(pos1,pos1+3).equalsIgnoreCase("<h0")){
                    int pos2 = linha.indexOf(">");
                    int pos3 = linha.lastIndexOf("<");
                    //Log.i("sub",linha.substring(pos2,pos3));
                    titutlo = linha.substring(pos2,pos3);
                    Log.i("titulo",titutlo);
                }
            }
                //Log.i("corpo",linha);
               // Log.i("linha",linha);
                CharSequence cs = ".jpg";
                if(linha.contains(cs )){
                 for(int count=0;count<linha.length()-3;count++){

                     String testeJPG = linha.substring(count,count+4);
                     Log.i("testeJPG",testeJPG);
                     if (testeJPG.equalsIgnoreCase(".jpg")){

                        int count1=0;
                      for( count1=count;count1>linha.length()+4;count1--){
                          String testeConjuntoCaracter = linha.substring(count1-4,count1);
                          Log.i("TEste cate",testeConjuntoCaracter);
                          if(testeConjuntoCaracter.equalsIgnoreCase("src=\"")) break;
                        }

                        imagem=linha.substring(count1,count);
                       // Log.i("imagem",imagem);
                        break;
                     }
                 }
                }

               linha = br.readLine();


            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.i("erro",e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("erro",e.getMessage());
        }



    }


}

package com.example.casa.lifecare.Servicos;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public  class  WebService {


    public static int httpStatus=0;

    public static String listarEntidades(String entidades){
        StringBuilder lista = new StringBuilder();
        try {
            Log.i("Webservice get","Entidade:  "+entidades);
            URL url = new URL("https://lifecare-unisul.herokuapp.com/"+entidades);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();
            httpStatus=connection.getResponseCode();
            Log.i("Resposta do Servidor:",httpStatus+"");
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                lista.append(scanner.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return lista.toString();
        }
    }



    public static String pegarEntidade(String entidades, String... parametros){
        StringBuilder entidade = new StringBuilder();
        try {
            Log.i("Webservice get","Entidade:  "+entidades+"...Paramentros:"+parametros);
            URL url = new URL("https://lifecare-unisul.herokuapp.com/"+entidades+"/"+parametros);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            connection.connect();
            httpStatus=connection.getResponseCode();
            Log.i("Resposta do Servidor:",httpStatus+"");
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                entidade.append(scanner.next());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return entidade.toString();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public int postar(String entidade, String... parametros) {
        int  retorno=0;
        try {
            //String produtoJson = gson.toJson(produto);

            StringBuilder enviar = new StringBuilder();
            enviar.append("{\n");

            for(int count=0; count<parametros.length;count=count+2){

                enviar.append("\"");
                enviar.append(parametros[count]);
                enviar.append("\":");
                enviar.append("\"");
                enviar.append(parametros[count+1]);
                enviar.append("\"");
                if(count+2<parametros.length)enviar.append(",");
                enviar.append("\n");
            }
            enviar.append("}");
            Log.i("Json string:",enviar.toString());

           /* enviar.append("{");
            enviar.append("\n");
            enviar.append("\"nome\": ");
            enviar.append("\""+paciente.getNome()+"\",");
            enviar.append("\n");
            enviar.append("\"idade\": ");
            enviar.append("\""+paciente.getIdade()+"\",");
            enviar.append("\n");
            enviar.append("\"email\": ");
            enviar.append("\""+paciente.getEmail()+"\"");
            enviar.append("\n");
            enviar.append("}");*/


            Log.i("enviar post",enviar.toString());
            URL url = new URL("https://lifecare-unisul.herokuapp.com/"+entidade);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(enviar.toString().getBytes("UTF-8"));
            }
            int http_status = connection.getResponseCode();
            retorno = http_status;
            Log.i("Estatus HTTP",http_status+"");
            connection.disconnect();



        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return retorno;
        }
    }
}




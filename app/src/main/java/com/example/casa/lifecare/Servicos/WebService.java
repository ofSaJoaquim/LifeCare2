package com.example.casa.lifecare.Servicos;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.casa.lifecare.entidades.Auxiliar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
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


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static int postar(String entidade,String... parametros) {
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
            Log.i("Json string:",entidade);

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
            Log.i("dados",enviar.toString());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(enviar.toString().getBytes("UTF-8"));
                //////
                outputStream.flush();
            }
            /////
            Map<String, List<String>> map = connection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
               Log.i("Cabecalho : ","Cahve" + entry.getKey()
                        + " ,Value : " + entry.getValue());
            }


            //////
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


    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static HttpRetorno postar(String entidade,boolean header, boolean body, String... parametros) {
        HttpRetorno retorno = new HttpRetorno();
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
            URL url = new URL(Auxiliar.servidor+entidade);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            Log.i("dados",enviar.toString());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.connect();
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(enviar.toString().getBytes("UTF-8"));
                //////
                outputStream.flush();
            }
            /////

            retorno.setHeader(connection.getHeaderFields());
            if(header==true) {
                for (Map.Entry<String, List<String>> entry : retorno.getHeader().entrySet()) {
                    /*Log.i("Cabecalho : ", "Cahve" + entry.getKey()
                            + " ,Value : " + entry.getValue());*/
                }
            }
            if(body==true) {
                BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
                String ret = "";
                while ((ret = br.readLine()) != null) {
                    Log.i("retorno post", ret);
                }
            }
            //////
            int http_status = connection.getResponseCode();


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




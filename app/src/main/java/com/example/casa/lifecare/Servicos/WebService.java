package com.example.casa.lifecare.Servicos;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.casa.lifecare.entidades.Auxiliar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public  class  WebService {


    public static int httpStatus=0;

    public static String listarEntidades(String entidades){
        StringBuilder lista = new StringBuilder();
        try {
            Log.i("Webservice get","Entidade:  "+entidades);
            URL url = new URL(Auxiliar.servidor+entidades);
    Log.i("URL",url.getHost());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000);
            connection.connect();
            httpStatus=connection.getResponseCode();
            Log.i("Resposta do Servidor:",httpStatus+"");
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
               lista.append(scanner.next());


            }
            if (entidades.length()>0)httpStatus=201;
            else httpStatus=405;
        }catch (ConnectException e){
            httpStatus=-1;
            Log.i("Falha de conexão","timeout codigo: "+e.getMessage());


        }catch (MalformedURLException e) {
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
            URL url = new URL(Auxiliar.servidor+entidades+"/"+parametros);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000);
            connection.connect();
            httpStatus=connection.getResponseCode();
            Log.i("Resposta do Servidor:",httpStatus+"");
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
               entidade.append(scanner.next());


            }
        }catch (ConnectException e){
            httpStatus=-1;
            Log.i("Falha de conexão","timeout codigo: "+e.getMessage());


        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            return entidade.toString();
        }
    }

    public static String pegarEntidadeSimples(String entidades){
        StringBuilder entidade = new StringBuilder();
        try {
            Log.i("Webservice get","Entidade:  "+entidades);
            URL url = new URL(Auxiliar.servidor+entidades);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000);
            connection.connect();
            httpStatus=connection.getResponseCode();
            Log.i("Resposta do Servidor:",httpStatus+"");
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String linha = br.readLine();
            while (linha!=null) {
                // lista.append(scanner.next());

                Log.i("linha",linha);
                entidade.append(linha);
                linha=br.readLine();
            }
        }catch (ConnectException e){
            httpStatus=-1;
            Log.i("Falha de conexão","timeout codigo: "+e.getMessage());


        }
        catch (MalformedURLException e) {
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
            URL url = new URL(Auxiliar.servidor+entidade);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            Log.i("dados",enviar.toString());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
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



        }catch (ConnectException e){
            httpStatus=-1;
            Log.i("Falha de conexão","timeout codigo: "+e.getMessage());


        }
        catch (Exception e) {
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
            Log.i("entidade",entidade);
            Log.i("URL",url.getHost().toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            Log.i("dados",enviar.toString());
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
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

            httpStatus=http_status;
            Log.i("Estatus HTTP",http_status+"");
            connection.disconnect();



        } catch (ConnectException e){
            Log.i("Falha de conexão","timeout codigo: "+e.getMessage());
           httpStatus=-1;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            Log.i("Retorno com",httpStatus+"");
            return retorno;
        }
    }
    public static String listarEntidadesTeste(String entidades){
        StringBuilder lista = new StringBuilder();
        try {
            Log.i("Webservice get","Entidade:  "+entidades);
            URL url = new URL(Auxiliar.servidor+entidades);
            Log.i("URL",url.getHost());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(10000);
            connection.connect();
            httpStatus=connection.getResponseCode();
            Log.i("Resposta do Servidor:",httpStatus+"");
           // Scanner scanner = new Scanner(url.openStream());
            InputStream is = url.openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String linha = br.readLine();
            while (linha!=null) {
                // lista.append(scanner.next());

                Log.i("linha",linha);
                lista.append(linha);
                linha=br.readLine();
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static int postarVazio(String entidade) {
        int  retorno=0;
        try {
            //String produtoJson = gson.toJson(produto);



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



            URL url = new URL(Auxiliar.servidor+entidade);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("PUT");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();
           /* try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write("".getBytes("UTF-8"));
                //////
                outputStream.flush();
            }*/
            /////


            int http_status = connection.getResponseCode();
            retorno = http_status;

            Log.i("Estatus HTTP",http_status+"");
            connection.disconnect();



        }catch (ConnectException e){
            httpStatus=-1;
            Log.i("Falha de conexão","timeout codigo: "+e.getMessage());


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return retorno;
        }
    }


}




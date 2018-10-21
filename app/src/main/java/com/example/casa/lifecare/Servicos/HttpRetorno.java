package com.example.casa.lifecare.Servicos;

import java.util.List;
import java.util.Map;

public class HttpRetorno {

  private Map<String,List<String>> header;
  private String body;

    public HttpRetorno() {

    }

    public Map<String, List<String>> getHeader() {
        return header;
    }

    public void setHeader(Map<String, List<String>> header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

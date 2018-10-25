package com.example.casa.lifecare.entidades;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private List<Menssagem> menssagens;

    public Chat() {
        menssagens=new ArrayList<>();
    }

    public List<Menssagem> getMenssagens() {
        return menssagens;
    }

    public void setMenssagens(List<Menssagem> menssagens) {
        this.menssagens = menssagens;
    }

    private void listarMenssagens(){
        for(Menssagem m: menssagens){
            if(m.isTipo()) Log.i("Paciente",m.getTexto());
            else if(!m.isTipo())Log.i("Médico",m.getTexto());
        }
    }
}

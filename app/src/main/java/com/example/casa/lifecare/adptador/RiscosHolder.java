package com.example.casa.lifecare.adptador;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.casa.lifecare.R;

public class RiscosHolder extends RecyclerView.ViewHolder{
    final TextView nome;
    final TextView grau;
    final TextView tipo;
    public RiscosHolder(View itemView) {
        super(itemView);
        nome=itemView.findViewById(R.id.textoTituloRisco);
        grau=itemView.findViewById(R.id.textoIntensidadeRisco);
        tipo=itemView.findViewById(R.id.textoModificavel);
    }
}

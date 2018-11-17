package com.example.casa.lifecare.adptador;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.casa.lifecare.R;

public class LinhaCuidadoHolder extends RecyclerView.ViewHolder {
    final TextView titulo;
    final TextView descricao;

    public LinhaCuidadoHolder(View itemView) {
        super(itemView);
        this.titulo = itemView.findViewById(R.id.textTituloLinha);
        this.descricao = itemView.findViewById(R.id.textDescricaoLinha);
    }
}

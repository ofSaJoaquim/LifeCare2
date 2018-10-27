package com.example.casa.lifecare.adptador;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.casa.lifecare.R;

public class NoticiasHolder  extends RecyclerView.ViewHolder {
    final TextView titulo;
    final TextView resumo;
    final ImageView imagem;
    public NoticiasHolder(View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.titulo_timeLine);
        resumo = itemView.findViewById(R.id.resumo_timeLine);
        imagem = itemView.findViewById(R.id.imagem_timeLine);
    }
}

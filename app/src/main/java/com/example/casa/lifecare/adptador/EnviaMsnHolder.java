package com.example.casa.lifecare.adptador;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.casa.lifecare.R;

public class EnviaMsnHolder extends RecyclerView.ViewHolder {
    final TextView nome;
    final  TextView corpo;
    final TextView hora;




    public EnviaMsnHolder(View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.textMenssagemEnviada);
        corpo =itemView.findViewById(R.id.textMenssagemEnviada);
        hora = itemView.findViewById(R.id.textMenssagemEnviadaHora);

    }
}

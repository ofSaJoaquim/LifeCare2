package com.example.casa.lifecare.adptador;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.casa.lifecare.R;

public class MenssagemHolder extends RecyclerView.ViewHolder {
     final TextView nome;
    final  TextView corpo;
    final TextView hora;




    public MenssagemHolder(View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.text_message_name);
        corpo =itemView.findViewById(R.id.text_message_body);
        hora = itemView.findViewById(R.id.text_message_time);

    }
}

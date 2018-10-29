package com.example.casa.lifecare.adptador;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.casa.lifecare.R;

public class MeusRemediosHolder extends RecyclerView.ViewHolder {
    final TextView remedio;
    final TextView proxima;
    final ImageButton aplicar;
    final ImageButton cancelar;
    final ImageButton alterar;
    public MeusRemediosHolder(View itemView) {
        super(itemView);
        remedio = itemView.findViewById(R.id.textViewRemedio);
        proxima = itemView.findViewById(R.id.textViewProxima);
        aplicar = itemView.findViewById(R.id.buttonAplicarRemedio);
        cancelar = itemView.findViewById(R.id.buttonCancelarAlerta);
        alterar = itemView.findViewById(R.id.buttonAlterarRemedio);
    }
}

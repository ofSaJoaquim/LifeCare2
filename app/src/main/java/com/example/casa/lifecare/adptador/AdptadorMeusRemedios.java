package com.example.casa.lifecare.adptador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.casa.lifecare.R;
import com.example.casa.lifecare.entidades.Medicamento;

import java.util.List;

public class AdptadorMeusRemedios extends RecyclerView.Adapter {
    private List<Medicamento>meusMedicamentos;
    private Context context;

    public AdptadorMeusRemedios(List<Medicamento> meusMedicamentos, Context context) {
        this.meusMedicamentos = meusMedicamentos;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_item_remedio, parent, false);
        MeusRemediosHolder holder = new MeusRemediosHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
Medicamento remedio = meusMedicamentos.get(position);
MeusRemediosHolder remediosHolder = (MeusRemediosHolder)holder;
remediosHolder.remedio.setText(remedio.getNome());
remediosHolder.proxima.setText(remedio.getQtDias().toString());
        remediosHolder.aplicar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        remediosHolder.cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        remediosHolder.alterar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return meusMedicamentos.size();
    }
}

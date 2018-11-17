package com.example.casa.lifecare.adptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.casa.lifecare.R;
import com.example.casa.lifecare.entidades.LinhaDeCuidado;

import java.util.List;

public class AdaptadorLinhaCuidados extends RecyclerView.Adapter  {
    private List<LinhaDeCuidado>linhaDeCuidados;
    private Context context;

    public AdaptadorLinhaCuidados(List<LinhaDeCuidado> linhaDeCuidados, Context context) {
        this.linhaDeCuidados = linhaDeCuidados;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_item_linha_de_cuidado, parent, false);
        LinhaCuidadoHolder  holder = new LinhaCuidadoHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    LinhaDeCuidado linhaDeCuidado = linhaDeCuidados.get(position);
    LinhaCuidadoHolder linhaCuidadoHolder = (LinhaCuidadoHolder)holder;
    linhaCuidadoHolder.titulo.setText(linhaDeCuidado.getTitulo());
    linhaCuidadoHolder.descricao.setText(linhaDeCuidado.getAcoes());
    }

    @Override
    public int getItemCount() {
        return linhaDeCuidados.size();
    }
}

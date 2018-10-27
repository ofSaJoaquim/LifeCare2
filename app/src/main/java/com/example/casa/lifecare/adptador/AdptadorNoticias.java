package com.example.casa.lifecare.adptador;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.casa.lifecare.R;
import com.example.casa.lifecare.Servicos.WebScraping;
import com.example.casa.lifecare.entidades.Menssagem;

import java.util.List;

public class AdptadorNoticias extends RecyclerView.Adapter {
    private List<WebScraping> noticias;
    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_item_time_line, parent, false);
        NoticiasHolder holder = new NoticiasHolder(view);

        return holder;
    }

    public AdptadorNoticias(List<WebScraping> noticias, Context context) {
        this.noticias = noticias;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WebScraping we = noticias.get(position);
        NoticiasHolder nh = (NoticiasHolder)holder;
        nh.titulo.setText(we.getTitulo());
        nh.resumo.setText(we.getResumo());
        nh.imagem.setImageBitmap(we.getImagem());
    }

    @Override
    public int getItemCount() {
     return  noticias.size();
    }
}

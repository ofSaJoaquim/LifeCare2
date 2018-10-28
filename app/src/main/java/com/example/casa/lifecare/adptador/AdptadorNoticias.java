package com.example.casa.lifecare.adptador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.casa.lifecare.R;
import com.example.casa.lifecare.Servicos.WebScraping;

import java.util.List;

public class AdptadorNoticias extends RecyclerView.Adapter {
    private List<WebScraping> noticias;
    private Context context;
    WebView wv;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        WebScraping we = noticias.get(position);
        NoticiasHolder nh = (NoticiasHolder)holder;
        nh.titulo.setText(we.getTitulo());
        nh.resumo.setText(we.getResumo());
        nh.imagem.setImageBitmap(we.getImagem());
       nh.imagem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(noticias.get(position).getSite()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
     return  noticias.size();
    }
}

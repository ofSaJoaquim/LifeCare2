package com.example.casa.lifecare.adptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.casa.lifecare.R;
import com.example.casa.lifecare.entidades.Risco;

import java.util.List;

public class AdaptadorRiscos extends RecyclerView.Adapter {
    private List<Risco>riscos;
    private Context context;

    public AdaptadorRiscos(List<Risco> riscos, Context context) {
        this.riscos = riscos;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_item_risco, parent, false);
        RiscosHolder  holder = new RiscosHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       final Risco risco = riscos.get(position);
        Log.i("risco",risco.getNome());
     final   RiscosHolder riscosHolder = (RiscosHolder)holder;
        riscosHolder.nome.setText(risco.getNome());
       riscosHolder.grau.setText(risco.getIntensidade().toString());
        riscosHolder.tipo.setText(risco.getTipo());
    }

    @Override
    public int getItemCount() {

        return riscos.size();
    }
}

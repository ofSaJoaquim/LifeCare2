package com.example.casa.lifecare.adptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.casa.lifecare.R;
import com.example.casa.lifecare.entidades.Menssagem;

import java.util.List;

public class MenssagemAdptador extends RecyclerView.Adapter {
    private List<Menssagem>menssagens;
    private Context context;
    private static final int MSN_ENVIA=1;
    private static final int MSN_RECEBE=0;

    public MenssagemAdptador(List<Menssagem> menssagens) {
        this.menssagens = menssagens;
    }
    @Override
    public int getItemViewType(int position) {
       // return super.getItemViewType(position);
        if(menssagens.get(position).isTipo())return MSN_ENVIA;
        else return MSN_RECEBE;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("tipo",viewType+"");
        if(viewType==MSN_RECEBE) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.activity_item_chat, parent, false);
            MenssagemHolder holder = new MenssagemHolder(view);

            return holder;
        }
        else { View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_item_chat_enviado, parent, false);
            EnviaMsnHolder holder = new EnviaMsnHolder(view);

            return holder;

        }
    }

    public MenssagemAdptador(List<Menssagem> menssagens, Context context) {
        this.menssagens = menssagens;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
       if(menssagens.get(position).isTipo()){
           EnviaMsnHolder holder = (EnviaMsnHolder) viewHolder;
           Menssagem m = menssagens.get(position);


           holder.corpo.setText(m.getTexto());
           holder.hora.setText(m.getHora());
       }
       else {
           MenssagemHolder holder = (MenssagemHolder) viewHolder;
           Menssagem m = menssagens.get(position);

           holder.nome.setText(m.getMedico().getNome());
           holder.corpo.setText(m.getTexto());
           holder.hora.setText(m.getHora());

       }

    }

    @Override
    public int getItemCount() {
        return menssagens.size();
    }
public void insertItem(Menssagem me){
        menssagens.add(me);
    notifyItemInserted(getItemCount());
}

}

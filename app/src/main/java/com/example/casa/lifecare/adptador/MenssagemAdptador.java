package com.example.casa.lifecare.adptador;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.casa.lifecare.R;
import com.example.casa.lifecare.entidades.Mensagem;


import java.util.List;

public class MenssagemAdptador extends RecyclerView.Adapter {
    private List<Mensagem>menssagens;
    private Context context;
    private static final int MSN_ENVIA=1;
    private static final int MSN_RECEBE=0;

    public MenssagemAdptador(List<Mensagem> menssagens) {
        this.menssagens = menssagens;
    }
    @Override
    public int getItemViewType(int position) {
       // return super.getItemViewType(position);
        if(menssagens.get(position).getMedicoId()==null)return  MSN_ENVIA;
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

    public MenssagemAdptador(List<Mensagem> menssagens, Context context) {
        this.menssagens = menssagens;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
       if(menssagens.get(position).getMedicoId()==null){
           EnviaMsnHolder holder = (EnviaMsnHolder) viewHolder;
           Mensagem m = menssagens.get(position);


           holder.corpo.setText(m.getTexto());
           Log.i("corpo",m.getTexto());
           holder.hora.setText(m.getInstante());
       }
       else {
           MenssagemHolder holder = (MenssagemHolder) viewHolder;
           Mensagem m = menssagens.get(position);

           holder.nome.setText(m.getMedico().getNome());
           holder.corpo.setText(m.getTexto());
           holder.hora.setText(m.getInstante());

       }

    }

    @Override
    public int getItemCount() {
        return menssagens.size();
    }
public void insertItem(Mensagem me){
        menssagens.add(me);
    notifyItemInserted(getItemCount());
}

}

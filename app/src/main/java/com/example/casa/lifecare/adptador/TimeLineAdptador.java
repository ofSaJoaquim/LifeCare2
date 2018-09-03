package com.example.casa.lifecare.adptador;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.casa.lifecare.R;
import com.example.casa.lifecare.entidade.TimeLine;

import java.util.List;

public class TimeLineAdptador extends BaseAdapter {

private final List<TimeLine> linha;
private final Activity activity;

    public List<TimeLine> getLinha() {
        return linha;
    }

    public TimeLineAdptador(List<TimeLine> linha, Activity activity) {
        this.linha = linha;
        this.activity = activity;
    }

    @Override
    public int getCount() {
       return linha.size();
    }

    @Override
    public Object getItem(int i) {
        return linha.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = activity.getLayoutInflater().inflate(R.layout.activity_time_line_front, viewGroup,false);

       TimeLine linhaAtual = linha.get(i);

        TextView nome = (TextView)
                view.findViewById(R.id.titulo);
        TextView descricao = (TextView)
                view.findViewById(R.id.noticia);
        ImageView imagem = (ImageView) view.findViewById(R.id.imagemNoticias);

        nome.setText(linhaAtual.getTitulo()        );
        descricao.setText(linhaAtual.getNoticia());

        //Categoria categoria = curso.getCategoria();

        /*if (categoria.equals(Categoria.JAVA)) {
            imagem.setImageResource(R.drawable.java);
        } else if (categoria.equals(Categoria.ANDROID)) {
            imagem.setImageResource(R.drawable.android);
        } else if (categoria.equals(Categoria.HTML)) {
            imagem.setImageResource(R.drawable.html);
        }*/
       imagem.setImageResource(linhaAtual.getIdImagem());

       return view;


    }
}

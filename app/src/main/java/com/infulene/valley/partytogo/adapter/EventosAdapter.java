package com.infulene.valley.partytogo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.infulene.valley.partytogo.DataUtils;
import com.infulene.valley.partytogo.activities.MainActivity;
import com.infulene.valley.partytogo.R;
import com.infulene.valley.partytogo.data.Evento;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 03-Oct-17.
 */

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.MyViewHolder> {


    private List<Evento> lista_eventos = new ArrayList<>();

    private boolean isFavorite41Clicked;

    private Context context;

    private RecyclerItemClickListner mRecyclerItemClickListner;

    public EventosAdapter(Context context, List<Evento> lista_eventos) {
        this.lista_eventos = lista_eventos;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }


    public void setmRecyclerItemClickListner(RecyclerItemClickListner mRecyclerItemClickListner) {
        this.mRecyclerItemClickListner = mRecyclerItemClickListner;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.progressBarLoadingImageEvent.setVisibility(View.VISIBLE);
        final Evento evento = lista_eventos.get(position);


        holder.textView_data.setText(DataUtils.dataDoEvento(position, lista_eventos));

        holder.textView_titulo.setText(evento.getTitulo());
        holder.textView_hora.setText(DataUtils.horaEvento(position, lista_eventos));
        holder.textView_local.setText(evento.getLocal());

        Picasso.with(context)
                .load(evento.getImagem_url())
                .into(holder.imageView_evento, new ImageLoadedCallback(holder.progressBarLoadingImageEvent) {

                    @Override
                    public void onSuccess() {
                        if (holder.progressBarLoadingImageEvent != null) {
                            holder.progressBarLoadingImageEvent.setVisibility(View.GONE);
                        }
                    }
                });


        holder.imageView_favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFavorite41Clicked) {
                    holder.imageView_favourite.setImageResource(R.drawable.ic_favorite_black_24dp);
                    isFavorite41Clicked = true;

                    MainActivity.addToFavouriteList(evento.getTitulo(),evento.getLocal(),evento.getHora(),evento.getImagem_url());

                } else {
                    holder.imageView_favourite.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    isFavorite41Clicked = false;
                }
            }
        });

        holder.imageView_share_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titulo = evento.getTitulo();
                String data = DataUtils.dataDoEvento(position, lista_eventos);

                Intent intent_share_event = new Intent(Intent.ACTION_SEND);
                intent_share_event.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent_share_event.setType("text/plain");
                intent_share_event.putExtra(Intent.EXTRA_SUBJECT, "PartyToGo Eventos");
                intent_share_event.putExtra(Intent.EXTRA_TEXT, "Venha ao evento, " + titulo + " no dia " + data + "." + "\n \n" + "Faça ja sua reserva no PartyToGO.");

                Intent intent = Intent.createChooser(intent_share_event, "Partilhar Evento");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


                v.getContext().startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return lista_eventos.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView textView_titulo;
        private TextView textView_local;
        private TextView textView_hora;
        private TextView textView_data;
        private ImageView imageView_evento;
        private ImageView imageView_favourite;
        private ProgressBar progressBarLoadingImageEvent;
        private ImageView imageView_share_event;


        public MyViewHolder(View itemView) {
            super(itemView);

            textView_titulo = (TextView) itemView.findViewById(R.id.textview_titulo);
            textView_local = (TextView) itemView.findViewById(R.id.textview_local);
            textView_hora = (TextView) itemView.findViewById(R.id.textview_hora);
            imageView_evento = (ImageView) itemView.findViewById(R.id.imageView_evento);
            imageView_favourite = (ImageView) itemView.findViewById(R.id.imageView_favourite);
            progressBarLoadingImageEvent = (ProgressBar) itemView.findViewById(R.id.pb_loading_image_event);
            imageView_share_event = (ImageView) itemView.findViewById(R.id.iv_share_event);
            textView_data = (TextView) itemView.findViewById(R.id.textview_data);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mRecyclerItemClickListner != null) {

                mRecyclerItemClickListner.onClickItemListner(v, getAdapterPosition());
            }
        }
    }


    private class ImageLoadedCallback implements Callback {
        ProgressBar progressBar;

        public ImageLoadedCallback(ProgressBar progBar) {
            progressBar = progBar;
        }

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }
    }

}

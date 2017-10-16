package com.infulene.valley.partytogo.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.infulene.valley.partytogo.R;
import com.infulene.valley.partytogo.activities.FavoritosActivity;
import com.infulene.valley.partytogo.data.EventoFavoritosContract;
import com.squareup.picasso.Picasso;

/**
 * Created by user on 15-Oct-17.
 */

public class FavoritosAdapter extends RecyclerView.Adapter<FavoritosAdapter.FavoritosViewHolder> {

    private Cursor mCursor;

    private Context mContext;

    private boolean isFavoriteClicked;

    public FavoritosAdapter(Context mContext, Cursor cursor) {
        this.mCursor = cursor;
        this.mContext = mContext;
    }

    @Override
    public FavoritosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.favoritos_item, parent, false);
        FavoritosViewHolder favoritosViewHolder = new FavoritosViewHolder(view);


        return favoritosViewHolder;
    }

    @Override
    public void onBindViewHolder(final FavoritosViewHolder holder, final int position) {


        if (!mCursor.moveToPosition(position))
            return;

        String titulo = mCursor.getString(mCursor.getColumnIndex(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_TITULO_EVENTO));

        String hora = mCursor.getString(mCursor.getColumnIndex(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_HORA));

        String img_url = mCursor.getString(mCursor.getColumnIndex(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_IMG_URL));

        String local = mCursor.getString(mCursor.getColumnIndex(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_LOCAL));

        long id = mCursor.getLong(mCursor.getColumnIndex(EventoFavoritosContract.EventoFavoritosEntry._ID));

        final int isSave = mCursor.getInt(mCursor.getColumnIndex(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_GRAVADO));

        holder.itemView.setTag(id);
        holder.textView_titulo_favoritos.setText(titulo);
        holder.textView_hora_favoritos.setText(hora);
        holder.textView_local_favoritos.setText(local);

        Picasso.with(mContext)
                .load(img_url)
                .placeholder(R.drawable.image_placeholder)
                .into(holder.imageView_evento_favoritos);

        if (isSave ==0){
            holder.imageView_favourite_favoritos.setImageResource(R.drawable.ic_favorite_border_black_24dp);

        }else {
            holder.imageView_favourite_favoritos.setImageResource(R.drawable.ic_favorite_black_24dp);
        }

        holder.imageView_favourite_favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               long id = (long) holder.itemView.getTag();
                int newPosition = holder.getAdapterPosition();

                FavoritosActivity.removerFavorito(id);

                notifyItemRemoved(newPosition);

                notifyItemRangeChanged(newPosition,mCursor.getCount());

             //  notifyDataSetChanged();

                Toast.makeText(v.getContext(), "Removida com sucesso!", Toast.LENGTH_SHORT).show();

            }
        });

    }




    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    class FavoritosViewHolder extends RecyclerView.ViewHolder {

        private TextView textView_titulo_favoritos;
        private TextView textView_local_favoritos;
        private TextView textView_hora_favoritos;
        private TextView textView_data_favoritos;
        private ImageView imageView_evento_favoritos;
        private ImageView imageView_favourite_favoritos;
        private ImageView imageView_share_event_favoritos;

        public FavoritosViewHolder(View itemView) {
            super(itemView);

            textView_titulo_favoritos = (TextView) itemView.findViewById(R.id.textview_titulo_favoritos);
            textView_local_favoritos = (TextView) itemView.findViewById(R.id.textview_local_favoritos);
            textView_hora_favoritos = (TextView) itemView.findViewById(R.id.textview_hora_favoritos);
            imageView_evento_favoritos = (ImageView) itemView.findViewById(R.id.imageView_evento_favoritos);
            imageView_favourite_favoritos = (ImageView) itemView.findViewById(R.id.imageView_favourite_favoritos);

        }
    }


}

package com.infulene.valley.partytogo.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.amitshekhar.DebugDB;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.infulene.valley.partytogo.Constantes;
import com.infulene.valley.partytogo.DataUtils;
import com.infulene.valley.partytogo.R;
import com.infulene.valley.partytogo.adapter.EventosAdapter;
import com.infulene.valley.partytogo.adapter.RecyclerItemClickListner;
import com.infulene.valley.partytogo.data.Evento;
import com.infulene.valley.partytogo.data.EventoFavoritosContract;
import com.infulene.valley.partytogo.data.EventoFavoritosDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListner {


    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private EventosAdapter mEventosAdapter;
    private ProgressBar mProgressBarLoadingEvents;
    private static List<Evento> lista_eventos = new ArrayList<>();


    private static SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DebugDB.getAddressLog();

        Backendless.initApp(getApplicationContext(), Constantes.APPLICATION_ID, Constantes.API_ID);

        initViews(); // findById de todas views

        mProgressBarLoadingEvents.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.INVISIBLE);

        setSupportActionBar(mToolbar);
        DataQueryBuilder dataQueryBuilder = DataQueryBuilder.create();


        mEventosAdapter = new EventosAdapter(getApplicationContext(), lista_eventos);
        mEventosAdapter.setmRecyclerItemClickListner(this);


        Backendless.Persistence.of(Evento.class).find(dataQueryBuilder, new AsyncCallback<List<Evento>>() {
            @Override
            public void handleResponse(List<Evento> response) {

                lista_eventos.addAll(response);
                mRecyclerView.setVisibility(View.VISIBLE);
                mProgressBarLoadingEvents.setVisibility(View.INVISIBLE);


                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                mRecyclerView.setAdapter(mEventosAdapter);
                mEventosAdapter.notifyDataSetChanged();

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(MainActivity.this, "Falhou o carregamento, problemas de internet!", Toast.LENGTH_SHORT).show();
            }
        });




        EventoFavoritosDbHelper dbHelper = new EventoFavoritosDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

    }

    public void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_events);
        mRecyclerView.setHasFixedSize(true);
        mProgressBarLoadingEvents = (ProgressBar) findViewById(R.id.pb_loading_events);

    }


    @Override
    public void onClickItemListner(View view, int clicledItemIndex) {

        Intent detalhesEventoIntent = new Intent(getApplicationContext(), DetalhesEventoActivity.class);
        detalhesEventoIntent.putExtra(Constantes.CHAVE_TITULO, lista_eventos.get(clicledItemIndex).getTitulo());
        detalhesEventoIntent.putExtra(Constantes.CHAVE_IMAGE_URL, lista_eventos.get(clicledItemIndex).getImagem_url());
        detalhesEventoIntent.putExtra(Constantes.CHAVE_HORA, DataUtils.horaEvento(clicledItemIndex, lista_eventos));
        detalhesEventoIntent.putExtra(Constantes.CHAVE_DETALHES, lista_eventos.get(clicledItemIndex).getDetalhes_evento());
        detalhesEventoIntent.putExtra(Constantes.CHAVE_PRECO, lista_eventos.get(clicledItemIndex).getPreco());
        detalhesEventoIntent.putExtra(Constantes.CHAVE_DATA, DataUtils.dataDoEvento(clicledItemIndex, lista_eventos));
        startActivity(detalhesEventoIntent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        if (id == R.id.action_favoritos){
            Intent intent = new Intent(this, FavoritosActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }




    public static long addToFavouriteList(String titulo, String local, String hora, String img_url){

        ContentValues cv = new ContentValues();

        cv.put(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_TITULO_EVENTO, titulo);
        cv.put(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_LOCAL, local);
        cv.put(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_HORA, hora);
        cv.put(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_IMG_URL, img_url);
        cv.put(EventoFavoritosContract.EventoFavoritosEntry.COLUMN_GRAVADO, 1);

        return mDb.insert(EventoFavoritosContract.EventoFavoritosEntry.TABLE_NAME, null, cv);

    }
}

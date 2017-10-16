package com.infulene.valley.partytogo.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.infulene.valley.partytogo.adapter.FavoritosAdapter;
import com.infulene.valley.partytogo.R;
import com.infulene.valley.partytogo.data.EventoFavoritosContract;
import com.infulene.valley.partytogo.data.EventoFavoritosDbHelper;

public class FavoritosActivity extends AppCompatActivity {



    private Toolbar mToolbar;
    private RecyclerView mRecyclerView_favoritos;
    private FavoritosAdapter mFavoritosAdapter;

    private static SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_favoritos);
        mRecyclerView_favoritos = (RecyclerView) findViewById(R.id.recycler_view_favoritos);

        mRecyclerView_favoritos.setLayoutManager(new LinearLayoutManager(this));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        EventoFavoritosDbHelper dbHelper = new EventoFavoritosDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        Cursor cursor = getAllFavoritos();

        mFavoritosAdapter = new FavoritosAdapter(this,cursor );

        mRecyclerView_favoritos.setAdapter(mFavoritosAdapter);


    }






    public Cursor getAllFavoritos(){
        return mDb.query(
                EventoFavoritosContract.EventoFavoritosEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_TIMESTAMP
        );
    }

    public static void removerFavorito(long id){

        mDb.delete(EventoFavoritosContract.EventoFavoritosEntry.TABLE_NAME,
                EventoFavoritosContract.EventoFavoritosEntry._ID + "=" +id, null);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }
}

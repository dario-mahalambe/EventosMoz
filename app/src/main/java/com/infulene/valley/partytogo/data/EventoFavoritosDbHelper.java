package com.infulene.valley.partytogo.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 12-Oct-17.
 */

public class EventoFavoritosDbHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "eventosfavoritos.db";

    private static final int DATABASE_VERSION = 1;

    public EventoFavoritosDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        final String SQL_CREATE_EVENTOSFAVORITOS = "CREATE TABLE " + EventoFavoritosContract.EventoFavoritosEntry.TABLE_NAME + " (" +
                EventoFavoritosContract.EventoFavoritosEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_TITULO_EVENTO + " TEXT NOT NULL, " +
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_DETALHES + " TEXT, " +
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_LOCAL + " TEXT, " +
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_PRECO + " TEXT, " +
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_DATA + " TEXT, " +
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_IMG_URL + " TEXT, " +
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_HORA + " TEXT, " +
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_GRAVADO + " INTENGER, " +
                EventoFavoritosContract.EventoFavoritosEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_EVENTOSFAVORITOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EventoFavoritosContract.EventoFavoritosEntry.TABLE_NAME);
        onCreate(db);
    }
}

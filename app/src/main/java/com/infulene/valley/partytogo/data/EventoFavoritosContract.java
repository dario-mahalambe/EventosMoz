package com.infulene.valley.partytogo.data;

import android.provider.BaseColumns;

/**
 * Created by user on 12-Oct-17.
 *
 *
 */

public  class EventoFavoritosContract {

    private EventoFavoritosContract() {
    }

    public static final class EventoFavoritosEntry implements BaseColumns{

        public static final String TABLE_NAME = "eventosfavoritos";

        public static final String COLUMN_TITULO_EVENTO = "titulo";

        public static final String COLUMN_DETALHES = "detalhes";

        public static final String COLUMN_LOCAL = "local";

        public static final String COLUMN_IMG_URL = "img_url";

        public static final String COLUMN_PRECO = "preco";

        public static final String COLUMN_DATA = "data";

        public static final String COLUMN_HORA = "hora";

        public static final String COLUMN_GRAVADO = "esta_gravado";

        public static final String COLUMN_TIMESTAMP = "timestamp";

    }
}

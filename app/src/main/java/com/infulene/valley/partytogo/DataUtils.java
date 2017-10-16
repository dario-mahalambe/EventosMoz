package com.infulene.valley.partytogo;

import com.infulene.valley.partytogo.data.Evento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by user on 11-Oct-17.
 */

public class DataUtils {


    public static  String dataDoEvento(int position,  List<Evento> lista_eventos ){
        Date date_evento = lista_eventos.get(position).getData_evento();
        Locale locale = new Locale("pt","BR");
        DateFormat format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy",locale);

        return format.format(date_evento);
    }

    public static String horaEvento (int position,  List<Evento> lista_eventos){

        Calendar calendar = Calendar.getInstance();
        Date date_hora = lista_eventos.get(position).getData_evento();
        calendar.setTime(date_hora);

        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        int minuto = calendar.get(Calendar.MINUTE);

        if (minuto == 0){
            return hora+":"+minuto+"0";
        }else {
            return hora+":"+minuto;
        }

    }
}

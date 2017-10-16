package com.infulene.valley.partytogo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.infulene.valley.partytogo.Constantes;
import com.infulene.valley.partytogo.R;
import com.squareup.picasso.Picasso;


public class DetalhesEventoActivity extends AppCompatActivity {


    private TextView textView_titulo;
    private ImageView imageView_event;
    private TextView textView_hora;
    private TextView textView_event_detalhes;
    private TextView textView_preco;
    private TextView textView_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);

        textView_hora = (TextView) findViewById(R.id.tv_hora_detlahes);
        textView_titulo = (TextView) findViewById(R.id.tv_titulo_detalhes);
        imageView_event = (ImageView) findViewById(R.id.iv_event_detalhes);
        textView_event_detalhes = (TextView) findViewById(R.id.tv_detalhes_event);
        textView_preco = (TextView) findViewById(R.id.tv_preco_detalhes);
        textView_data = (TextView) findViewById(R.id.tv_data_detalhes);


        Intent intent_detalhes = getIntent();
        textView_hora.setText(intent_detalhes.getStringExtra(Constantes.CHAVE_HORA));
        textView_titulo.setText(intent_detalhes.getStringExtra(Constantes.CHAVE_TITULO));
        textView_event_detalhes.setText(intent_detalhes.getStringExtra(Constantes.CHAVE_DETALHES));
        textView_preco.setText(intent_detalhes.getStringExtra(Constantes.CHAVE_PRECO) + " MZN");
        textView_data.setText(intent_detalhes.getStringExtra(Constantes.CHAVE_DATA));

        Picasso.with(getApplicationContext())
                .load(intent_detalhes.getStringExtra(Constantes.CHAVE_IMAGE_URL))
                .into(imageView_event);


    }


}

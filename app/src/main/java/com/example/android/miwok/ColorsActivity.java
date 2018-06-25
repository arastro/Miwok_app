package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    /** Handles playback of all the sound files */
    private MediaPlayer mMediaPlayer;

    /**Este listener se activa cuando el {@link MediaPlayer} ha completado la reproducción del archivo de audio.*/
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            /*Ahora que el archivo de sonido ha terminado de reproducirse, suelte
             * los recursos del reproductor multimedia.*/
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));

        /*Se crea un adaptador que funciona como contenedor para el arraylist
        * de words*/
        WordAdapter adapter = new WordAdapter(this,words,R.color.category_colors);

        /*Se busca la lista(en este caso es listview) en la que se agregara el contenedfor*/
        ListView listView = (ListView) findViewById(R.id.listColors);

        /*Se agrega el contenedor a la lista*/
        listView.setAdapter(adapter);

        /*Metodo para agregar una accion al momento de tocar un objeto o item de la lista*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ColorsActivity.this,"Lo que sea",Toast.LENGTH_SHORT).show();
                releaseMediaPlayer();//por si no esta vacio el reproductor lo vaciamos
                mMediaPlayer = MediaPlayer.create(ColorsActivity.this,words.get(position).getAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }
        });

    }

    /**
     * Limpie el reproductor multimedia liberando sus recursos.
     */
    private void releaseMediaPlayer() {
        // Si el reproductor multimedia no es nulo, es posible que esté reproduciendo un sonido.
        if (mMediaPlayer != null) {
            /*Independientemente del estado actual del reproductor multimedia, libere
             *sus recursos porque ya no lo necesitamos.*/
            mMediaPlayer.release();

            /*Establezca el reproductor multimedia de nuevo a nulo. Para nuestro código, hemos
             *decidido que configurar el reproductor multimedia como nulo es una manera fácil de
             * decir que el reproductor multimedia no está configurado para reproducir un archivo
             * de audio en este momento.*/
            mMediaPlayer = null;
        }
    }

}

package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter wordAdapter = new WordAdapter(this, words,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.listPhrases);

        listView.setAdapter(wordAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ColorsActivity.this,"Lo que sea",Toast.LENGTH_SHORT).show();
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this,words.get(position).getAudioResourceId());
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

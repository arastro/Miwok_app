package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_family);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("father", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("older brother", "taachi", R.drawable.family_older_brother,  R.raw.family_older_brother));
        words.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother,  R.raw.family_younger_brother));
        words.add(new Word("older sister", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("younger sister", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("grandmother ", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("grandfather", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdapter adapter = new WordAdapter(this, words,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.listFamily);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ColorsActivity.this,"Lo que sea",Toast.LENGTH_SHORT).show();
                releaseMediaPlayer();
                mMediaPlayer = MediaPlayer.create(FamilyActivity.this, words.get(position).getAudioResourceId());
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

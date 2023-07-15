package com.example.miwokforyou;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Phases_Activity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange== AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK) {
                //pause playback
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                //resume playback
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                //stop playback
                releasedMediaPlayer();

            }

        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releasedMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> words = new ArrayList<Word>();
        //Word w = new Word("one","lutti");
        // words.add(w);
        words.add(new Word("where are you going ?", "wetetti", R.raw.phrase_where_are_you_going));
        words.add(new Word("what is your name ?", "chiwitta", R.raw.phrase_what_is_your_name));
        words.add(new Word("my name is", "chokokki", R.raw.phrase_my_name_is));
        words.add(new Word("how are feeling", "topissa", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("i am feeling good", "takaakki", R.raw.phrase_what_is_your_name));
        words.add(new Word("are you coming", "topoppi", R.raw.phrase_what_is_your_name));
        words.add(new Word("yes, i am coming", "kullulli", R.raw.phrase_what_is_your_name));
        words.add(new Word("i am coming", "kelilie", R.raw.phrase_what_is_your_name));
        words.add(new Word("hoe are feeling", "topissa", R.raw.phrase_what_is_your_name));
        words.add(new Word("i am feeling good", "takaakki", R.raw.phrase_what_is_your_name));
        words.add(new Word("are you coming", "topoppi", R.raw.phrase_what_is_your_name));


        WordAdapter Adapter = new WordAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releasedMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(Phases_Activity.this, word.getmAudioResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

    }
    @Override
    protected void onStop() {
        super.onStop();
        releasedMediaPlayer();
        mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
    }

    private void releasedMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
package com.example.homework_3_4;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private ImageView playView; // создание объекта картинки "Play"
    private ImageView stopView; // создание объекта картинки "Stop"
    private ImageView imageViewPicture; // создание объекта картинки для обложки
    private MediaPlayer mediaPlayer; // создание медиа-плеера
    private SeekBar  seekBar; // создание полосы прокрутки

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playView = findViewById(R.id.imageView);
        stopView = findViewById(R.id.imageViewStop);
        imageViewPicture = findViewById(R.id.imageViewPicture);
        seekBar = findViewById(R.id.seekBar);
        mediaPlayer = new MediaPlayer();

        try {

            AssetFileDescriptor descriptor = getAssets().openFd("tek_it_cafuné_speed_up.mp3");
            // обработка трека(путь к файлу,  момент воспроизведения файла, длительность трека)
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mediaPlayer.prepare(); // запуск проигрывателя

            seekBar.setMax(mediaPlayer.getDuration()); // максимальное значение seekBar = длина трека
            seekBar.setProgress(0); // значение seekBar на момент начала воспроизведения = 0

        } catch (IOException e) {
            Log.e("error", "Файл не найден");
        }

        playView.setOnClickListener(view -> {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            new SeekBarThread(mediaPlayer, seekBar).start();
        });

        stopView.setOnClickListener(view -> {
            mediaPlayer.pause();
            seekBar.setOnSeekBarChangeListener(this);
        });

        playView.setOnClickListener(view -> {
            int length = mediaPlayer.getCurrentPosition();
            mediaPlayer.seekTo(length);
            mediaPlayer.start();
            new SeekBarThread(mediaPlayer, seekBar).start();
        });

        imageViewPicture.setOnClickListener(view -> {
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            new SeekBarThread(mediaPlayer, seekBar).start();
        });

        imageViewPicture.setOnClickListener(view -> {
            mediaPlayer.pause();
            seekBar.setOnSeekBarChangeListener(this);
        });

        imageViewPicture.setOnClickListener(view -> {
            int length = mediaPlayer.getCurrentPosition();
            mediaPlayer.seekTo(length);
            mediaPlayer.start();
            new SeekBarThread(mediaPlayer, seekBar).start();
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // метод при перетаскивании ползунка по шкале
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }
            // метод при начале перетаскивания ползунка по шкале
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            // метод при завершении перетаскивания ползунка по шкале
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void songName() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    // метод освобождения используемых медиа-плеером ресурсов
    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
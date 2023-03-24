package com.example.homework_3_4;

import android.media.MediaPlayer;
import android.widget.SeekBar;

public class SeekBarThread extends Thread {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;

    public SeekBarThread(MediaPlayer mediaPlayer, SeekBar seekBar) {
        this.mediaPlayer = mediaPlayer;
        this.seekBar = seekBar;
    }

    @Override
    public void run() {
        int currentPosition = mediaPlayer.getCurrentPosition(); // считывание текущей позиции трека
        int total = mediaPlayer.getDuration(); // считывание длины трека

        while (mediaPlayer != null && mediaPlayer.isPlaying() && currentPosition < total) {
            try {
                Thread.sleep(1000);
                currentPosition = mediaPlayer.getCurrentPosition();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            seekBar.setProgress(currentPosition);
        }
    }
}

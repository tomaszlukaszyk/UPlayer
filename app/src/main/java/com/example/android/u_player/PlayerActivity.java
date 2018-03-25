package com.example.android.u_player;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    boolean mPlaybackState = true;
    String mSongTitle;
    TextView mSongTitleTextView;
    TextView mArtistNameTextView;
    TextView mSongDurationTextView;
    ImageView mAlbumCoverImageView;
    ImageView mPlayPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Get the title of desired song from previous Activity
        mSongTitle = getIntent().getStringExtra("song");

        // Find the view displaying song title and set the title of desired song
        mSongTitleTextView = findViewById(R.id.player_upper_text);
        mSongTitleTextView.setText(mSongTitle);

        // Find view displaying artist name
        mArtistNameTextView = findViewById(R.id.player_lower_text);
        // Find view displaying duration of the song
        mSongDurationTextView = findViewById(R.id.duration);
        // Find view displaying cover of the album from which the desired song comes from
        mAlbumCoverImageView = findViewById(R.id.player_album_cover);
        // Find view displaying play/pause button
        mPlayPauseButton = findViewById(R.id.play_pause);

        // Get the song title of every song from array resource file and put them in Array object
        String[] songTitle = getResources().getStringArray(R.array.song_title);
        // Get the artist name of every song from array resource file and put them in Array object
        String[] artistName = getResources().getStringArray(R.array.artist_name);
        // Get the song duration of every song from array resource file and put them in Array object
        String[] songDuration = getResources().getStringArray(R.array.duration);
        // Get the resource ID of album covers in higher resolution
        final TypedArray albumCoverBig = getResources().obtainTypedArray(R.array.album_cover_big);

        // Search the songTitle array and find index of song title equal to title from intent
        // and than set artist name, song duration and album cover from data arrays with found index
        // to corresponding views
        for (int i =0; i < songTitle.length; i++){
            if (songTitle[i].equals(mSongTitle)){
                mArtistNameTextView.setText(artistName[i]);
                mSongDurationTextView.setText(songDuration[i]);
                mAlbumCoverImageView.setImageResource(albumCoverBig.getResourceId(i, -1));
            }
        }
        albumCoverBig.recycle();

        mPlayPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPlaybackState) {
                    mPlayPauseButton.setImageResource(R.drawable.ic_play);
                    mPlaybackState = false;
                } else {
                    mPlayPauseButton.setImageResource(R.drawable.ic_pause);
                    mPlaybackState = true;
                }
            }
        });

    }
}

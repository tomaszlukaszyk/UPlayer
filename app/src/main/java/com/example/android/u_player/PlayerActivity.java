package com.example.android.u_player;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PlayerActivity extends AppCompatActivity {

    private boolean mPlaybackState = true;
    private ImageView mPlayPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // Get the title of desired song from previous Activity
        String mSongTitle = getIntent().getStringExtra("song");

        // Set the label for the activity
        setTitle(getString(R.string.player_activity, mSongTitle));

        // Find the view displaying song title and set the title of desired song
        TextView mSongTitleTextView = findViewById(R.id.player_upper_text);
        mSongTitleTextView.setText(mSongTitle);

        // Find view displaying artist name
        TextView mArtistNameTextView = findViewById(R.id.player_lower_text);
        // Find view displaying duration of the song
        TextView mSongDurationTextView = findViewById(R.id.duration);
        // Find view displaying cover of the album from which the desired song comes from
        ImageView mAlbumCoverImageView = findViewById(R.id.player_album_cover);
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
        for (int i = 0; i < songTitle.length; i++) {
            if (songTitle[i].equals(mSongTitle)) {
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

        // Set on click listener to take user back to library
        TextView mLibraryButton = findViewById(R.id.library);
        mLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}

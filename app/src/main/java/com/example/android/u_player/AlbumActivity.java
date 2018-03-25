package com.example.android.u_player;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumActivity extends AppCompatActivity {

    String mAlbumTitle;
    String mArtistName;
    ImageView mAlbumCoverImageView;
    TextView mAlbumTitleTextView;
    TextView mArtistNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        // Get the desired album title and artist name to display from AlbumFragment
        mAlbumTitle = getIntent().getStringExtra("album");
        mArtistName = getIntent().getStringExtra("artist");

        // Find view displaying cover of the album
        mAlbumCoverImageView = findViewById(R.id.album_cover);
        // Find view displaying album title and set the title from intent
        mAlbumTitleTextView = findViewById(R.id.album_upper_text);
        mAlbumTitleTextView.setText(mAlbumTitle);
        // Find view displaying artist name and set the name from intent
        mArtistNameTextView = findViewById(R.id.album_lower_text);
        mArtistNameTextView.setText(mArtistName);

        // Get the song title of every song from array resource file and put them in Array object
        String[] songTitle = getResources().getStringArray(R.array.song_title);
        // Get the album title of every song from array resource file and put them in Array object
        String[] albumTitle = getResources().getStringArray(R.array.album_title);
        // Get the resource ID of album covers for songs from array resource file and put them in TypedArray object
        final TypedArray albumCover = getResources().obtainTypedArray(R.array.album_cover_big);

        // Find resource ID for album cover corresponding to the album title and set it to the view
        for (int i = 0; i < albumTitle.length; i++) {
            if (albumTitle[i].equals(mAlbumTitle)) {
                mAlbumCoverImageView.setImageResource(albumCover.getResourceId(i, -1));
                break;
            }
        }
        albumCover.recycle();

        // Create new ArrayList to contain all songs from given album
        final ArrayList<Song> songsList = new ArrayList<>();
        // Search the albumTitle array and find indexes where album title matches to the one from intent
        // and than add song titles at this indexes to songsList array
        for (int i = 0; i < albumTitle.length; i++) {
            if (albumTitle[i].equals(mAlbumTitle)) {
                songsList.add(new Song(songTitle[i], true));
            }
        }

        // Create {@link SongAdapter} object from a custom class, that provides Views for the
        // {@link ListView} using the provided data
        // @param context The current context
        // @param songsList The list of Song objects
        SongAdapter itemsAdapter = new SongAdapter(this, songsList);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list_view, which is declared
        // in the list_view.xml layout file.
        ListView listView = findViewById(R.id.list_view);

        // Associate {@link SongAdapter} object with {@link ListView} in the layout
        listView.setAdapter(itemsAdapter);

        // Set a click listener to open PlayerActivity with selected song
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Song} object at the given position the user clicked on
                Song song = songsList.get(position);

                // Start PlayerActivity and pass the name of chosen song
                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra("song", song.getUpperText());
                startActivity(intent);
            }
        });

    }
}

package com.example.android.u_player;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArtistActivity extends AppCompatActivity {

    String mArtistName;
    TextView mArtistNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        // Get the desired artist name to display from ArtistFragment
        mArtistName = getIntent().getStringExtra("artist");

        // Set the artist name to the view
        mArtistNameTextView = findViewById(R.id.artist_name);
        mArtistNameTextView.setText(mArtistName);

        // Get the artist name of every song from array resource file and put them in Array object
        String[] artistName = getResources().getStringArray(R.array.artist_name);
        // Get the song title of every song from array resource file and put them in Array object
        String[] songTitle = getResources().getStringArray(R.array.song_title);
        // Get the album title of every song from array resource file and put them in Array object
        String[] albumTitle = getResources().getStringArray(R.array.album_title);
        // Get the resource ID of album covers for songs from array resource file and put them in TypedArray object
        final TypedArray albumCover = getResources().obtainTypedArray(R.array.album_cover);

        // Create a list of Song objects
        final ArrayList<Song> songs = new ArrayList<>();
        for (int i = 0; i < artistName.length; i++) {
            if (artistName[i].equals(mArtistName)) {
                songs.add(new Song(songTitle[i], albumTitle[i], albumCover.getResourceId(i, -1), true));
            }
        }
        albumCover.recycle();

        // Sorting
        Collections.sort(songs, new Comparator<Song>() {
            @Override
            public int compare(Song song1, Song song2) {

                return song1.getUpperText().compareTo(song2.getUpperText());
            }
        });

        // Create {@link SongAdapter} object from a custom class, that provides Views for the
        // {@link ListView} using the provided data
        // @param context The current context
        // @param songs The list of Song objects
        SongAdapter itemsAdapter = new SongAdapter(this, songs);

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
                Song song = songs.get(position);

                // Start PlayerActivity and pass the name of chosen song
                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra("song", song.getUpperText());
                startActivity(intent);
            }
        });

    }
}

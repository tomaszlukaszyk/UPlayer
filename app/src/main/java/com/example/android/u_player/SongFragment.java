package com.example.android.u_player;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {

    public SongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        // Get the song title of every song from array resource file and put them in Array object
        String[] songTitle = getResources().getStringArray(R.array.song_title);
        // Get the artist name of every song from array resource file and put them in Array object
        String[] artistName = getResources().getStringArray(R.array.artist_name);
        // Get the resource ID of album covers for songs from array resource file and put them in TypedArray object
        final TypedArray albumCover = getResources().obtainTypedArray(R.array.album_cover);

        // Create a list of Song objects
        final ArrayList<Song> songs = new ArrayList<>();
        for (int i = 0; i < songTitle.length; i++) {
            songs.add(new Song(songTitle[i], artistName[i], albumCover.getResourceId(i, -1), true));
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
        SongAdapter itemsAdapter = new SongAdapter(getActivity(), songs);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list_view, which is declared
        // in the list_view.xml layout file.
        ListView listView = rootView.findViewById(R.id.list_view);

        // Associate {@link SongAdapter} object with {@link ListView} in the layout
        listView.setAdapter(itemsAdapter);

        // Set a click listener to open PlayerActivity with selected song
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Song} object at the given position the user clicked on
                Song song = songs.get(position);

                // Start PlayerActivity and pass the name of chosen song
                Intent intent = new Intent(getActivity(), PlayerActivity.class);
                intent.putExtra("song", song.getUpperText());
                startActivity(intent);
            }
        });

        return rootView;
    }
}
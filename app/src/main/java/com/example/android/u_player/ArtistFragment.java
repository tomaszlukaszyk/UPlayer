package com.example.android.u_player;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtistFragment extends Fragment {


    public ArtistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        // Get the artist name of every song from array resource file and put them in Array object
        List<String> artistName = Arrays.asList(getResources().getStringArray(R.array.artist_name));

        // Search the artistName for individual artists and put them in artistList only once
        ArrayList<String> artistList = new ArrayList<>();
        for (String artist : artistName) {
            if (!artistList.contains(artist)) {
                artistList.add(artist);
            }
        }

        // Create new ArrayList to contain individual artists and count of their occurrences in songs
        final ArrayList<Song> artistsCount = new ArrayList<>();

        // Create a list of Song objects containing name of every artist along with the number of song by this artist
        for (String artist : artistList) {
            artistsCount.add(new Song(artist + " - " + Collections.frequency(artistName, artist) + " songs", false));
        }

        // Sorting
        Collections.sort(artistsCount, new Comparator<Song>() {
            @Override
            public int compare(Song album1, Song album2) {

                return album1.getUpperText().compareTo(album2.getUpperText());
            }
        });

        // Create {@link SongAdapter} object from a custom class, that provides Views for the
        // {@link ListView} using the provided data
        // @param context The current context
        // @param artistsCount The list of Song objects
        SongAdapter itemsAdapter = new SongAdapter(getActivity(), artistsCount);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list_view, which is declared
        // in the list_view.xml layout file.
        ListView listView = rootView.findViewById(R.id.list_view);

        // Associate {@link SongAdapter} object with {@link ListView} in the layout
        listView.setAdapter(itemsAdapter);

        // Set a click listener to open ArtistActivity with selected artist name
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Song} object at the given position the user clicked on
                Song artist = artistsCount.get(position);

                // Start ArtistActivity and pass the name of chosen artist
                Intent intent = new Intent(getActivity(), ArtistActivity.class);
                intent.putExtra("artist", artist.getUpperText().split("-")[0].trim());
                startActivity(intent);
            }
        });

        return rootView;
    }

}

package com.example.android.u_player;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
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
public class AlbumFragment extends Fragment {


    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_view, container, false);

        // Get the artist name of every song from array resource file and put them in Array object
        String[] artistName = getResources().getStringArray(R.array.artist_name);
        // Get the album title of every song from array resource file and put them in Array object
        String[] albumTitle = getResources().getStringArray(R.array.album_title);
        // Get the resource ID of album covers for songs from array resource file and put them in TypedArray object
        TypedArray albumCover = getResources().obtainTypedArray(R.array.album_cover);

        ArrayList<String> artistList = new ArrayList<>();
        ArrayList<String> albumList = new ArrayList<>();
        ArrayList<Integer> coversList = new ArrayList<>();

        // Search the albumTitle list, find individual album names and add them to albumList only once
        // and add corresponding artist to artistList, and add corresponding album cover to coversList
        for (int i = 0; i < albumTitle.length; i++) {
            if (!albumList.contains(albumTitle[i])) {
                albumList.add(albumTitle[i]);
                artistList.add(artistName[i]);
                coversList.add(albumCover.getResourceId(i, -1));
            }
        }

        // Create a list of Song objects
        final ArrayList<Song> albums = new ArrayList<Song>();
        for (int i = 0; i < albumList.size(); i++) {
            albums.add(new Song(albumList.get(i), artistList.get(i), coversList.get(i), false));
        }

        // Sorting
        Collections.sort(albums, new Comparator<Song>() {
            @Override
            public int compare(Song album1, Song album2) {

                return album1.getUpperText().compareTo(album2.getUpperText());
            }
        });

        // Create {@link SongAdapter} object from a custom class, that provides Views for the
        // {@link ListView} using the provided data
        // @param context The current context
        // @param albums The list of Song objects
        SongAdapter itemsAdapter = new SongAdapter(getActivity(), albums);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list_view, which is declared
        // in the list_view.xml layout file.
        ListView listView = rootView.findViewById(R.id.list_view);

        // Associate {@link SongAdapter} object with {@link ListView} in the layout
        listView.setAdapter(itemsAdapter);

        // Set a click listener to open AlbumActivity with selected album
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get the {@link Song} object at the given position the user clicked on
                Song album = albums.get(position);

                // Start AlbumActivity and pass the name of chosen album and corresponding artist
                Intent intent = new Intent(getActivity(), AlbumActivity.class);
                intent.putExtra("album", album.getUpperText());
                intent.putExtra("artist", album.getLowerText());
                startActivity(intent);
            }
        });

        return rootView;
    }

}

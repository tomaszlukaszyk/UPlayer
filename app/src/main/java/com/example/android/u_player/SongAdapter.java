package com.example.android.u_player;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * {@link SongAdapter} is an {@link ArrayAdapter} that can provide the layout for each list
 * based on a data source, which is a list of {@link Song} objects.
 */

public class SongAdapter extends ArrayAdapter<Song> {

    SongAdapter(Context context, ArrayList<Song> songs) {
        super(context, 0, songs);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Song} object located at this position in the list
        final Song currentSong = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID list_upper_text
        TextView songTitle = listItemView.findViewById(R.id.list_upper_text);
        // Get the upper text from the current Song object and
        // set this text on the TextView
        assert currentSong != null;
        songTitle.setText(currentSong.getUpperText());

        // Find the TextView in the list_item.xml layout with the ID list_lower_text
        TextView artistName = listItemView.findViewById(R.id.list_lower_text);
        // Get the lower text from the current Song object and
        // set this text on the TextView
        if (currentSong.hasLowerText()) {
            // make sure the text is visible
            artistName.setVisibility(View.VISIBLE);
            // set the TextView to the text specified in the current Song object
            artistName.setText(currentSong.getLowerText());
        } else {
            // otherwise hide the text
            artistName.setVisibility(View.GONE);
        }

        // Find the ImageView in the list_item.xml layout with the ID list_image_view
        ImageView coverView = listItemView.findViewById(R.id.list_image_view);
        // Get the image resource ID from the current Song object and
        // set the image to coverView
        if (currentSong.hasAlbumCover()) {
            // make sure the image is visible
            coverView.setVisibility(View.VISIBLE);
            // set the ImageView to the image resource specified in the current Song object
            coverView.setImageResource(currentSong.getAlbumCover());
        } else {
            // otherwise hide the image
            coverView.setVisibility(View.GONE);
        }

        // Find the ImageView in hte list_item.xml layout with ID play_arrow
        ImageView playArrow = listItemView.findViewById(R.id.play_arrow);
        // Check if the play arrow should be visible
        if (currentSong.getDisplayArrowIcon()) {
            playArrow.setVisibility(View.VISIBLE);
        } else {
            playArrow.setVisibility(View.GONE);
        }

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the AdapterView
        return listItemView;
    }
}

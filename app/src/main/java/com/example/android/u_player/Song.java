package com.example.android.u_player;

/**
 * Stores all information associated with single song
 */

public class Song {

    // Indicates that there is no lower text to show
    private static final String NO_LOWER_TEXT = "none";
    // Indicates that there is no album cover to show
    private static final int NO_ALBUM_COVER = -1;
    // Holds text to be displayed in upper TextView in layout
    private final String mUpperText;
    // Holds text to be displayed in lower TextView in layout
    private String mLowerText = NO_LOWER_TEXT;
    // Holds resource ID for the album cover
    private int mAlbumCover = NO_ALBUM_COVER;
    // Information whether or not to display play arrow icon
    private final boolean mDisplayPlayArrow;

    /**
     * Create new Song object
     *
     * @param upperText        is text to be displayed in upper TextView in layout
     * @param lowerText        is text to be displayed in lower TextView in layout
     * @param albumCover       is resource ID for the image file with album cover for the album from which song comes
     * @param displayPlayArrow is information whether or not to display play arrow icon
     */
    public Song(String upperText, String lowerText, int albumCover, boolean displayPlayArrow) {
        mUpperText = upperText;
        mLowerText = lowerText;
        mAlbumCover = albumCover;
        mDisplayPlayArrow = displayPlayArrow;
    }

    /**
     * Create new Song object
     *
     * @param upperText        is text to be displayed in upper TextView in layout
     * @param displayPlayArrow is information whether or not to display play arrow icon
     */
    public Song(String upperText, boolean displayPlayArrow) {
        mUpperText = upperText;
        mDisplayPlayArrow = displayPlayArrow;
    }

    // Get the text for upper TextView
    String getUpperText() {
        return mUpperText;
    }

    // Get the text for lower TextView
    String getLowerText() {
        return mLowerText;
    }

    // Returns whether or not there is lower text to display
    boolean hasLowerText() {
        return !mLowerText.equals(NO_LOWER_TEXT);
    }

    // Get the resource ID of the album cover
    int getAlbumCover() {
        return mAlbumCover;
    }

    // Returns whether or not there is album cover to display
    boolean hasAlbumCover() {
        return mAlbumCover != NO_ALBUM_COVER;
    }

    // Get the information whether or not to display play arrow icon
    boolean getDisplayArrowIcon() {
        return mDisplayPlayArrow;
    }
}

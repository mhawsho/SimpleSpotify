package se.lantzdev.android.simplespotify;

public final class Track {

    public final String mTrackName;
    public final String mArtist;
    public final String mAlbum;
    public final String mHref;

    public Track(String trackName, String artist, String album, String href) {
        mTrackName = trackName;
        mArtist = artist;
        mAlbum = album;
        mHref = href;
    }
}

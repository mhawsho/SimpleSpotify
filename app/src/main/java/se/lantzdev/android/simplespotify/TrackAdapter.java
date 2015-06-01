package se.lantzdev.android.simplespotify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class TrackAdapter extends ArrayAdapter<Track> {

    public TrackAdapter(Context context, Track[] tracks) {
        super(context,0,tracks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) throws NullPointerException {

        try {
            Track track = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_list_layout, parent, false);
            }

            TextView trackName = (TextView) convertView.findViewById(R.id.track_name);
            TextView trackArtist = (TextView) convertView.findViewById(R.id.track_artist);

            trackName.setText(track.mTrackName);
            trackArtist.setText(track.mArtist);

        } catch (NullPointerException e) {

        }

        return convertView;
    }


}
package se.lantzdev.android.simplespotify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListTrackActivity extends Activity {

    public static final String EXTRA_MESSAGE = "se.lantzdev.android.simplespotify.ShowMessageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.search_list_layout);

        final ListView searchList = (ListView) findViewById(R.id.search_list_view);
        String rawJsonResponse = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final Track[] tracks;

        try {
            tracks = getSearchResults(rawJsonResponse);

            TrackAdapter adapter = new TrackAdapter(this, tracks);

            searchList.setAdapter(adapter);

            searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Track track;
                    track = tracks[position];

                    openNewWindow(track);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private Track[] getSearchResults(String rawJsonResponse) throws JSONException {
        final Track[] tempTracks = new Track[10];

        try {
            JSONObject jsonResponse = new JSONObject(rawJsonResponse);
            JSONArray searchResult = jsonResponse.getJSONArray("tracks");

            for (int i = 0; i < tempTracks.length; i++) {
                JSONObject trackObject = searchResult.getJSONObject(i);
                tempTracks[i] = new Track(trackObject.getString("name"),
                        trackObject.getJSONArray("artists").getJSONObject(0).getString("name"),
                        trackObject.getJSONObject("album").getString("name"),
                        trackObject.getString("href"));
            }

        } catch (JSONException e) {
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
            finish();
            Log.e("MESSAGE_ACTIVITY", "Could not download info", e);
        }
        return tempTracks;
    }

    public void openNewWindow(Track track) {

        Bundle trackBundle = new Bundle();
        trackBundle.putString("trackName", track.mTrackName);
        trackBundle.putString("artist", track.mArtist);
        trackBundle.putString("album", track.mAlbum);
        trackBundle.putString("href", track.mHref);
        Intent intent = new Intent(this, TrackInfo.class);
        intent.putExtra(EXTRA_MESSAGE, trackBundle);

        startActivity(intent);

    }

}

package se.lantzdev.android.simplespotify;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import java.util.List;

public class TrackInfo extends Activity {

    private final String EXTRA = "se.lantzdev.android.simplespotify.ShowMessageActivity";
    private String URL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.track_info_layout);

        final TextView textView = (TextView) findViewById(R.id.track_info_text_view);
        final Button button = (Button) findViewById(R.id.open_spotify);
        Track track = new Track(getIntent().getBundleExtra(EXTRA).getString("trackName"),
                getIntent().getBundleExtra(EXTRA).getString("artist"),
                getIntent().getBundleExtra(EXTRA).getString("album"),
                getIntent().getBundleExtra(EXTRA).getString("href"));
        URL = track.mHref;

        textView.setText("Song: " + track.mTrackName + "\n\n" + "Artist: " + track.mArtist + "\n\n" + "Album: " + track.mAlbum);

    }

    public void openSpotify(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));

        PackageManager packageManager = getPackageManager();
        List activities = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        boolean isIntentSafe = activities.size() > 0;

        if(isIntentSafe) {
            startActivity(intent);
        } else {
            URL = URL.substring(14);
            intent.setData(Uri.parse("http://open.spotify.com/track/" + URL));
            startActivity(intent);
        }


    }
}

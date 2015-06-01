package se.lantzdev.android.simplespotify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.apache.http.Header;
import org.json.*;


public class MainActivity extends Activity {

    public static final String EXTRA_MESSAGE = "se.lantzdev.android.simplespotify.MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);
    }

    public void searchResult(View view) throws JSONException {

        SearchView searchField = (SearchView) findViewById(R.id.search_field);
        final String relativeUrl = searchField.getQuery().toString();

        if (relativeUrl.equals("")) {
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        } else {

            SpotifyRestClient.get(relativeUrl, null, new JsonHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject jsonResponse) {
                    openNewWindow(jsonResponse.toString());
                }
            });
        }
    }

    public void openNewWindow(String searchResult) {
        Intent intent = new Intent(this, ListTrackActivity.class);
        intent.putExtra(EXTRA_MESSAGE, searchResult);

        startActivity(intent);
    }
}

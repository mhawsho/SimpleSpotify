package se.lantzdev.android.simplespotify;

import android.util.Log;

import com.loopj.android.http.*;

public class SpotifyRestClient {
    private static final String BASE_URL = "http://ws.spotify.com/search/1/track.json?q=";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        Log.d("ABSOLUTE URL", getAbsoluteUrl(url));
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}

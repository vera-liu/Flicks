package com.example.vera_liu.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    public void getMovies() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get("http://www.google.com", params,
            new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // called when response HTTP status is "200 OK"
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                }
            }
        );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMovies();
    }
}

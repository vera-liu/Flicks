package com.example.vera_liu.flicks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.vera_liu.flicks.adapters.MoviesAdapter;
import com.example.vera_liu.flicks.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private String movieUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=030ab859193237f03ed76f36076a2a21";
    ArrayList<Movie> movies;
    private MoviesAdapter adapter;
    RecyclerView recyclerView;
    private void parseMovieResponse(JSONObject response) throws JSONException {
        JSONArray moviesJson = response.getJSONArray("results");
        for (int i=0; i < moviesJson.length(); i++) {
            JSONObject result = moviesJson.getJSONObject(i);
            movies.add(new Movie(result.getInt("id"), result));
        }
        adapter = new MoviesAdapter(this, movies);
        recyclerView = (RecyclerView) findViewById(R.id.moviesRcView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
    }

    public void getMovies() {
        movies = new ArrayList<Movie>();
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get(movieUrl, params,
            new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    // called when response HTTP status is "200 OK"
                    try {
                        parseMovieResponse(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("Success", response.toString());
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                    // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                    Log.d("Failed", res);
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

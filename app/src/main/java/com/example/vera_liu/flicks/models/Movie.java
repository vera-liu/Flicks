package com.example.vera_liu.flicks.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vera_liu on 3/24/17.
 */

public class Movie {
    private int id;
    private JSONObject details;
    private String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/";
    private String API = "?api_key=030ab859193237f03ed76f36076a2a21";
    private boolean popular;
    public Movie(int id, JSONObject details) {
        this.id = id;
        this.details = details;
    }
    public JSONObject getDetails() {
        return details;
    }
    public String getTitle() throws JSONException {
        return details.getString("title");
    }
    public String getPosterPath() throws JSONException {
        return IMAGE_BASE_URL + details.getString("poster_path") + API;
    }
    public String getBackdropPath() throws JSONException {
        return IMAGE_BASE_URL + details.getString("backdrop_path") + API;
    }
    public String getOverview() throws JSONException {
        return details.getString("overview");
    }
    public boolean isPopular() throws JSONException {
        return details.getInt("vote_average") > 5;
    }
}

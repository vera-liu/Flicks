package com.example.vera_liu.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.vera_liu.flicks.R;
import com.example.vera_liu.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

/**
 * Created by vera_liu on 3/25/17.
 */

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        // Your holder should contain a member variable
//        // for any view that will be set as you render a row
//        public TextView titleTextView;
//        public ImageView posterImageView;
//        public TextView overviewTextView;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            titleTextView = (TextView) itemView.findViewById(R.id.title);
//            posterImageView = (ImageView) itemView.findViewById(R.id.moviePoster);
//            overviewTextView = (TextView) itemView.findViewById(R.id.overview);
//        }
//    }
    private ArrayList<Movie> movies;
    private Context mContext;

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        this.movies = movies;
        mContext = context;
    }

    private Context getContext() {
        return mContext;
    }
    @Override
    public int getItemViewType(int position) {
        try {
            if (movies.get(position).isPopular()) {
                return 1;
            } else {
                return 0;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder;
        switch(viewType) {
            case 1:
                View v1 = inflater.inflate(R.layout.item_movie, parent, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case 0:
                View v2 = inflater.inflate(R.layout.item_movie2, parent, false);
                viewHolder = new ViewHolder2(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.item_movie, parent, false);
                viewHolder = new ViewHolder1(v);
                break;
        }
        return viewHolder;
    }
    private void configureViewHolder1(ViewHolder1 viewHolder, int position) {
        String imagePath = "";
        Movie movie = movies.get(position);
        int orientation = mContext.getResources().getConfiguration().orientation;
        if (orientation ==  Configuration.ORIENTATION_LANDSCAPE) {
            try {
                imagePath = movie.getBackdropPath();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            try {
                imagePath = movie.getPosterPath();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            viewHolder.setView(movie.getTitle(), movie.getOverview());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Picasso.with(mContext)
            .load(imagePath)
            .placeholder(R.drawable.loading)
            .transform(new RoundedCornersTransformation(10, 10))
            .into(viewHolder.getImageView());
    }

    private void configureViewHolder2(ViewHolder2 viewHolder, int position) {
        String imagePath = "";
        Movie movie = movies.get(position);
        try {
            imagePath = movie.getBackdropPath();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        int width = windowManager.getDefaultDisplay().getWidth();
        Picasso.with(mContext)
                .load(imagePath)
                .placeholder(R.drawable.loading)
                .transform(new RoundedCornersTransformation(10, 10))
                .resize(width, 0)
                .into(viewHolder.getImageView());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Movie movie = movies.get(position);
        String imagePath = "";

        switch(viewHolder.getItemViewType()) {
            case 1:
                // popular movies (backdrop for both landscape and portrait
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position);
                break;
            case 0:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            default:

        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }
}

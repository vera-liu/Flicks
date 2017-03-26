package com.example.vera_liu.flicks.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vera_liu.flicks.R;

/**
 * Created by vera_liu on 3/25/17.
 */

public class ViewHolder1 extends RecyclerView.ViewHolder {
    private TextView titleTextView;
    private ImageView posterImageView;
    private TextView overviewTextView;
    public ViewHolder1(View itemView) {
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.title);
        posterImageView = (ImageView) itemView.findViewById(R.id.moviePoster);
        overviewTextView = (TextView) itemView.findViewById(R.id.overview);
    }
    public void setView(String title, String overview) {
        titleTextView.setText(title);
        overviewTextView.setText(overview);
    }
    public ImageView getImageView() {
        return posterImageView;
    }
}

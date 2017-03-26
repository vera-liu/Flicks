package com.example.vera_liu.flicks.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.vera_liu.flicks.R;

/**
 * Created by vera_liu on 3/25/17.
 */

public class ViewHolder2 extends RecyclerView.ViewHolder {
    private ImageView posterImageView;
    public ViewHolder2(View itemView) {
        super(itemView);
        posterImageView = (ImageView) itemView.findViewById(R.id.moviePoster);
    }
    public ImageView getImageView() {
        return posterImageView;
    }
}

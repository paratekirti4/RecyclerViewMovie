package com.example.movielistactivity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movielistactivity.Model.MovieResponse;
import com.example.movielistactivity.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    Context context;
    List<MovieResponse> movieTypeList;
    LayoutInflater lf;

    public MovieAdapter(Context context, List<MovieResponse> professionTypeList) {
        this.context = context;
        this.movieTypeList = professionTypeList;
        lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_movie, viewGroup, false);
        return new MovieAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        MovieResponse movieResponse = movieTypeList.get(position);
        holder.txtTitle.setText("" + movieResponse.getTitle());
        holder.txtReleaseYear.setText("" + movieResponse.getReleaseYear());
        holder.txtRating.setText("" + movieResponse.getRating());
        holder.txtGenre.setText("" + movieResponse.getGenre());

        Picasso.with(context)
                .load(movieResponse.getImage())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return movieTypeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtRating, txtReleaseYear, txtGenre;
        ImageView image;
        LinearLayout llMovieType;

        public ViewHolder(@NonNull View view) {
            super(view);
            txtTitle = view.findViewById(R.id.txtTitle);
            txtRating = view.findViewById(R.id.txtRating);
            txtReleaseYear = view.findViewById(R.id.txtReleaseYear);
            txtGenre = view.findViewById(R.id.txtGenre);
            image = view.findViewById(R.id.image);
            llMovieType = view.findViewById(R.id.llMovieType);
        }
    }
}
package com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieCrew;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.R;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.RecyclerViewModel.MovieCastRecyclerViewHolder;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.RecyclerViewModel.MovieCrewRecyclerViewHolder;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.MyRecyclerViewViewHolder;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.ViewHolderType;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.ViewHolerType;

import java.util.ArrayList;

public class MovieCrewRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewViewHolder> {

    private Activity activity;
    private ArrayList<MovieCrew> movieCrews;

    public MovieCrewRecyclerViewAdapter(Activity activity, ArrayList<MovieCrew> movieCrews) {
        this.activity = activity;
        this.movieCrews = movieCrews;
    }

    @NonNull
    @Override
    public MyRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_profile, viewGroup, false);
        return new MovieCrewRecyclerViewHolder(view, movieCrews, activity);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewViewHolder topRatedMovieRecyclerViewHolder, int i) {
        topRatedMovieRecyclerViewHolder.setData(i);
    }

    @Override
    public int getItemCount() {
        return movieCrews.size();
    }
}

package com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.RecyclerViewModel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Activities.MovieDetailsActivity;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieSinglePagination;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.R;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.GlideApp;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.Internet;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.MyRecyclerViewViewHolder;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.Utility;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiConstants.BASE_URL_POSTER_IMAGE;

public class TopRatedMovieRecyclerViewHolder extends MyRecyclerViewViewHolder implements View.OnClickListener {

    @BindView(R.id.item_card_top_rated_movie_imageView_poster)
    ImageView imageViewPoster;
    @BindView(R.id.item_card_top_rated_movie_textView_title)
    TextView textViewTitle;
    @BindView(R.id.item_card_top_rated_movie_textView_releaseDate)
    TextView textViewReleaseDate;
    @BindView(R.id.item_card_top_rated_movie_textView_voteAverage)
    TextView textViewVoteAverage;
    @BindView(R.id.item_card_top_rated_movie_ratingBar_popularity)
    RatingBar ratingBar;
    @BindView(R.id.item_card_top_rated_movie_textView_voteCount)
    TextView textViewVoteCount;

    private ArrayList<MovieSinglePagination> movieSinglePaginationArrayList;
    private Activity activity;

    public TopRatedMovieRecyclerViewHolder(@NonNull View itemView, ArrayList<MovieSinglePagination> movieSinglePaginationArrayList, Activity activity) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.movieSinglePaginationArrayList = movieSinglePaginationArrayList;
        this.activity = activity;
    }

    @Override
    public void setData(int position) {
        super.setData(position);
        MovieSinglePagination movie = movieSinglePaginationArrayList.get(position);

        GlideApp.with(activity)
                .load(BASE_URL_POSTER_IMAGE + movie.getPosterPath())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getTitle());
        textViewReleaseDate.setText(Utility.formatDate(movie.getReleaseDate()));
        textViewVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        textViewVoteCount.setText(String.valueOf(movie.getVoteCount()));
        ratingBar.setRating(movie.getPopularity().floatValue());
    }


    @OnClick(R.id.item_card_top_rated_movie_frameLayout_mask)
    public void openDetails() {
        if (Internet.isOnline(activity)) {
            Intent intent = new Intent(activity, MovieDetailsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("movie", movieSinglePaginationArrayList.get(getAdapterPosition()));
            intent.putExtras(bundle);
            activity.startActivity(intent);
        } else {
            Toast.makeText(activity, "Please, check your network connection.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

    }
}

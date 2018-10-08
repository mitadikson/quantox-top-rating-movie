package com.develop.mita.ddimitrijevic.topratedmovie_quantox.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiClient;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiInterface;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.AppDatabase;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom.Favorites;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.Movie;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieCast;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieCredits;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieCrew;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieGenre;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieSinglePagination;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.R;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.MovieCastRecyclerViewAdapter;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.MovieCrewRecyclerViewAdapter;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.GlideApp;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.Utility;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.ViewHolerType;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiConstants.API_KEY;
import static com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiConstants.BASE_URL_POSTER_IMAGE;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.activity_movie_details_imageView_poster)
    ImageView imageViewPoster;

    @BindView(R.id.activity_movie_details_imageView_favorites)
    ImageView imageViewFavorites;

    @BindView(R.id.activity_movie_details_textView_title)
    TextView textViewTitle;

    @BindView(R.id.activity_movie_details_textView_releaseDate)
    TextView textViewReleaseDate;

    @BindView(R.id.activity_movie_details_textView_genre)
    TextView textViewGenre;

    @BindView(R.id.activity_movie_details_textView_voteAverage)
    TextView textViewVoteAverage;

    @BindView(R.id.activity_movie_details_ratingBar_textView_voteCount)
    TextView textViewVoteCount;

    @BindView(R.id.activity_movie_details_textView_description)
    TextView textViewDescription;

    @BindView(R.id.activity_movie_details_ratingBar_popularity)
    RatingBar ratingBar;

    @BindView(R.id.activity_movie_details_recyclerView_cost)
    RecyclerView recyclerViewCast;

    @BindView(R.id.activity_movie_details_recyclerView_crew)
    RecyclerView recyclerViewCrew;

    private Movie movieDetails;
    private ApiInterface apiService;
    private LinearLayoutManager linearLayoutManagerCast, linearLayoutManagerCrew;
    private MovieCastRecyclerViewAdapter recyclerViewAdapterCast;
    private MovieCrewRecyclerViewAdapter recyclerViewAdapterCrew;
    private AppDatabase db;
    private Favorites favoritesMovie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);

        if (getIntent().getExtras() != null) {
            movieDetails = (MovieSinglePagination) getIntent().getExtras().getSerializable("movie");
        } else {
            finish();
        }
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initBasicDetails(movieDetails);
    }

    private void initBasicDetails(Movie movie) {
        GlideApp.with(this)
                .load(BASE_URL_POSTER_IMAGE + movie.getPosterPath())
                .into(imageViewPoster);
        textViewTitle.setText(movie.getTitle());
        textViewReleaseDate.setText(Utility.formatDate(movie.getReleaseDate()));
        textViewVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
        textViewVoteCount.setText(String.valueOf(movie.getVoteCount()));
        ratingBar.setRating(movie.getPopularity().floatValue());
        textViewGenre.setText("");

        Call<Movie> call = apiService.getMovieDetails(String.valueOf(movieDetails.getId()), API_KEY);
        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.body() != null) {
                    movieDetails = response.body();
                    textViewDescription.setText(movieDetails.getOverview());
                    favoritesMovie = new Favorites(movieDetails);
                    imageViewFavorites.setVisibility(View.VISIBLE);
                    if (db.favoritesModel().findFavorites(favoritesMovie.getId()) != null) {
                        imageViewFavorites.setImageResource(R.drawable.ic_favorite_black_24dp);
                    } else {
                        imageViewFavorites.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    }

                    int i = 1;
                    for (MovieGenre genre : movieDetails.getGenres()) {
                        textViewGenre.append(genre.getName());
                        if (i < movieDetails.getGenres().size())
                            textViewGenre.append(", ");
                        i++;
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

        Call<MovieCredits> creditsCall = apiService.getMovieCredits(String.valueOf(movieDetails.getId()), API_KEY);
        creditsCall.enqueue(new Callback<MovieCredits>() {
            @Override
            public void onResponse(Call<MovieCredits> call, Response<MovieCredits> response) {
                if (response.body() != null) {
                    movieDetails.setMovieCredits(response.body());
                    setupCastRecyclerView();
                    setupCrewRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<MovieCredits> call, Throwable t) {

            }
        });
    }

    private void setupCastRecyclerView() {
        linearLayoutManagerCast = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        if (recyclerViewAdapterCast == null) {
            recyclerViewCast.setLayoutManager(linearLayoutManagerCast);
            recyclerViewAdapterCast = new MovieCastRecyclerViewAdapter(this, (ArrayList<MovieCast>) movieDetails.getMovieCredits().getCast());
            recyclerViewCast.setAdapter(recyclerViewAdapterCast);
            recyclerViewCast.setItemAnimator(new DefaultItemAnimator());
            recyclerViewCast.setNestedScrollingEnabled(true);
        }
    }

    private void setupCrewRecyclerView() {
        linearLayoutManagerCrew = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        if (recyclerViewAdapterCrew == null) {
            recyclerViewCrew.setLayoutManager(linearLayoutManagerCrew);
            recyclerViewAdapterCrew = new MovieCrewRecyclerViewAdapter(this, (ArrayList<MovieCrew>) movieDetails.getMovieCredits().getCrew());
            recyclerViewCrew.setAdapter(recyclerViewAdapterCrew);
            recyclerViewCrew.setItemAnimator(new DefaultItemAnimator());
            recyclerViewCrew.setNestedScrollingEnabled(true);
        }
    }

    private void init() {
        apiService = ApiClient.getClient().create(ApiInterface.class);
        db = AppDatabase.getFileDatabase(this);
    }

    @OnClick(R.id.activity_movie_details_imageView_favorites)
    public void callFavorites() {

        if (db.favoritesModel().findFavorites(favoritesMovie.getId()) != null) {
            db.favoritesModel().deleteFavorites(favoritesMovie);
            imageViewFavorites.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        } else {
            db.favoritesModel().insertFavorites(favoritesMovie);
            imageViewFavorites.setImageResource(R.drawable.ic_favorite_black_24dp);
        }
    }
}

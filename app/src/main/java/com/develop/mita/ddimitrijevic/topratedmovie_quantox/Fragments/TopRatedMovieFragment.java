package com.develop.mita.ddimitrijevic.topratedmovie_quantox.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiClient;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiInterface;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.AppDatabase;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom.MovieModelRoom;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.Movie;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieListPagination;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieSinglePagination;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Listener.OnLoadMoreListener;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.R;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.TopRatedMovieRecyclerViewAdapter;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.Internet;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api.ApiConstants.API_KEY;

public class TopRatedMovieFragment extends Fragment implements OnLoadMoreListener {

    @BindView(R.id.top_rated_movie_fragment_constraintLayout)
    ConstraintLayout constraintLayoutRoot;
    @BindView(R.id.top_rated_movie_fragment_recyclerView)
    RecyclerView recyclerViewMovieTopRated;

    private GridLayoutManager gridLayoutManager;
    private TopRatedMovieRecyclerViewAdapter topRatedMovieRecyclerViewAdapter;
    private ArrayList<MovieSinglePagination> movieSinglePaginationArrayList = new ArrayList<>();
    private ApiInterface apiService;
    private int currentPage = 0, lastPage = 10, totalItem = 10;
    private AppDatabase db;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_rated_movie_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        apiService = ApiClient.getClient().create(ApiInterface.class);
        db = AppDatabase.getFileDatabase(getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Internet.isOnline(getActivity())) {
            setupRecyclerView();
        } else {
            currentPage = 1;
            lastPage = 1;
            totalItem = 1;
            for (MovieModelRoom movieModelRoom : db.movieModel().loadAllMovies()) {
                movieSinglePaginationArrayList.add(new MovieSinglePagination(movieModelRoom));
            }
        }
    }

    private void setupRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        if (topRatedMovieRecyclerViewAdapter == null) {
            recyclerViewMovieTopRated.setLayoutManager(gridLayoutManager);
            topRatedMovieRecyclerViewAdapter = new TopRatedMovieRecyclerViewAdapter(getActivity(), movieSinglePaginationArrayList, recyclerViewMovieTopRated);
            recyclerViewMovieTopRated.setAdapter(topRatedMovieRecyclerViewAdapter);
            recyclerViewMovieTopRated.setItemAnimator(new DefaultItemAnimator());
            recyclerViewMovieTopRated.setNestedScrollingEnabled(true);

            topRatedMovieRecyclerViewAdapter.setOnLoadMoreListener(this);

            Call<MovieListPagination> call1 = apiService.getMoviePagination(API_KEY, String.valueOf(1), "en-US");
            call1.enqueue(new Callback<MovieListPagination>() {
                @Override
                public void onResponse(Call<MovieListPagination> call, Response<MovieListPagination> response) {
                    totalItem = response.body().getTotalResults();
                    currentPage = response.body().getPage();
                    lastPage = response.body().getTotalPages();

                    movieSinglePaginationArrayList.addAll(response.body().getResults());

                    for (MovieSinglePagination movie : response.body().getResults()) {
                        db.movieModel().insertMovie(new MovieModelRoom(movie));
                    }

                    topRatedMovieRecyclerViewAdapter.notifyDataSetChanged();
                    topRatedMovieRecyclerViewAdapter.setLoaded();
                }

                @Override
                public void onFailure(Call<MovieListPagination> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }


    @Override
    public void onLoadMore() {
        if (movieSinglePaginationArrayList.size() < totalItem && currentPage < lastPage) {
            //ADD LOADING CARD
            movieSinglePaginationArrayList.add(null);
            topRatedMovieRecyclerViewAdapter.notifyItemInserted(movieSinglePaginationArrayList.size() - 1);
            new Handler().postDelayed(() -> {
                //REMOVE LOADING CARD
                try {
                    movieSinglePaginationArrayList.remove(movieSinglePaginationArrayList.size() - 1);
                    topRatedMovieRecyclerViewAdapter.notifyItemRemoved(movieSinglePaginationArrayList.size());
                } catch (Exception ignored) {

                }
                currentPage += 1;
                Call<MovieListPagination> call1 = apiService.getMoviePagination(API_KEY, String.valueOf(currentPage), "en-US");
                call1.enqueue(new Callback<MovieListPagination>() {
                    @Override
                    public void onResponse(Call<MovieListPagination> call, Response<MovieListPagination> response) {
                        totalItem = response.body().getTotalResults();
                        currentPage = response.body().getPage();
                        lastPage = response.body().getTotalPages();

                        movieSinglePaginationArrayList.addAll(response.body().getResults());

                        for (MovieSinglePagination movie : response.body().getResults()) {
                            db.movieModel().insertMovie(new MovieModelRoom(movie));
                        }

                        topRatedMovieRecyclerViewAdapter.notifyDataSetChanged();
                        topRatedMovieRecyclerViewAdapter.setLoaded();
                    }

                    @Override
                    public void onFailure(Call<MovieListPagination> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }, 2000);

        }
    }
}

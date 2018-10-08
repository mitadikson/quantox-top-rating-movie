package com.develop.mita.ddimitrijevic.topratedmovie_quantox.Fragments;

import android.os.Bundle;
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

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.AppDatabase;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom.Favorites;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieSinglePagination;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.R;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.TopRatedMovieRecyclerViewAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoritesMovieFragment extends Fragment {

    @BindView(R.id.favorites_movie_fragment_constraintLayout)
    ConstraintLayout constraintLayoutRoot;
    @BindView(R.id.favorites_movie_fragment_recyclerView)
    RecyclerView recyclerViewMovieTopRated;

    private GridLayoutManager gridLayoutManager;
    private TopRatedMovieRecyclerViewAdapter topRatedMovieRecyclerViewAdapter;
    private ArrayList<MovieSinglePagination> movieSinglePaginationArrayList = new ArrayList<>();
    private AppDatabase db;

    public static FavoritesMovieFragment newInstance() {
        return new FavoritesMovieFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_movie_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        db = AppDatabase.getFileDatabase(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        movieSinglePaginationArrayList = new ArrayList<>();
        for (Favorites f : db.favoritesModel().loadAllFavorites()) {
            movieSinglePaginationArrayList.add(new MovieSinglePagination(f));
        }
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        if (topRatedMovieRecyclerViewAdapter == null) {
            recyclerViewMovieTopRated.setLayoutManager(gridLayoutManager);
            topRatedMovieRecyclerViewAdapter = new TopRatedMovieRecyclerViewAdapter(getActivity(), movieSinglePaginationArrayList, recyclerViewMovieTopRated);
            recyclerViewMovieTopRated.setAdapter(topRatedMovieRecyclerViewAdapter);
            recyclerViewMovieTopRated.setItemAnimator(new DefaultItemAnimator());
            recyclerViewMovieTopRated.setNestedScrollingEnabled(true);
        }
    }

}

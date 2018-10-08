package com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieSinglePagination;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Listener.OnLoadMoreListener;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.R;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.RecyclerViewModel.LoadingMovieViewHolder;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.RecyclerViewAdapters.RecyclerViewModel.TopRatedMovieRecyclerViewHolder;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.MyRecyclerViewViewHolder;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.ViewHolderType;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.Utility.ViewHolerType;

import java.util.ArrayList;

public class TopRatedMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewViewHolder> {

    private Activity activity;
    private ArrayList<MovieSinglePagination> movieSinglePaginationArrayList;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading = false;
    private int visibleThreshold = 2;
    private int lastVisibleItem, totalItemCount;


    public TopRatedMovieRecyclerViewAdapter(Activity activity, ArrayList<MovieSinglePagination> movieSinglePaginationArrayList, RecyclerView recyclerView) {
        this.activity = activity;
        this.movieSinglePaginationArrayList = movieSinglePaginationArrayList;

        final GridLayoutManager linearLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isLoading = true;
                }
            }
        });
    }

    @NonNull
    @Override
    public MyRecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        switch (i) {
            case ViewHolderType.MOVIE_CARD_RECYCLER_VIEW_HOLDER: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_top_rated_movie, viewGroup, false);
                return new TopRatedMovieRecyclerViewHolder(view, movieSinglePaginationArrayList, activity);
            }
            case ViewHolderType.LOADING: {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_loading, viewGroup, false);
                return new LoadingMovieViewHolder(view, activity);
            }
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewViewHolder topRatedMovieRecyclerViewHolder, int i) {
        topRatedMovieRecyclerViewHolder.setData(i);
    }

    @Override
    public int getItemCount() {
        if (movieSinglePaginationArrayList == null)
            return 0;
        return movieSinglePaginationArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (movieSinglePaginationArrayList != null) {
            ViewHolerType object = movieSinglePaginationArrayList.get(position);
            if (object != null) {
                return object.getTypeViewHolder();
            } else {
                return ViewHolderType.LOADING;
            }
        }
        return ViewHolderType.LOADING;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLoaded() {
        isLoading = false;
    }
}

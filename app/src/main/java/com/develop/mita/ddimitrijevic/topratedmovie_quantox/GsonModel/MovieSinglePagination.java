package com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom.Favorites;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom.MovieModelRoom;
import com.google.gson.annotations.SerializedName;

public class MovieSinglePagination extends Movie {

    public MovieSinglePagination() {

    }

    public MovieSinglePagination(MovieModelRoom favorites) {
        super.setId(favorites.getId());
        super.setPosterPath(favorites.getPosterPath());
        super.setReleaseDate(favorites.getReleaseDate());
        super.setTitle(favorites.getTitle());
        super.setPopularity(favorites.getPopularity());
        super.setVoteAverage(favorites.getVoteAverage());
        super.setVoteCount(favorites.getVoteCount());
        super.setOverview(favorites.getOverview());
    }

    public MovieSinglePagination(Favorites favorites) {
        super.setId(favorites.getId());
        super.setPosterPath(favorites.getPosterPath());
        super.setReleaseDate(favorites.getReleaseDate());
        super.setTitle(favorites.getTitle());
        super.setPopularity(favorites.getPopularity());
        super.setVoteAverage(favorites.getVoteAverage());
        super.setVoteCount(favorites.getVoteCount());
        super.setOverview(favorites.getOverview());
    }

    @SerializedName("genre_ids")
    private int[] genreIds;

    public int[] getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(int[] genreIds) {
        this.genreIds = genreIds;
    }
}

package com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieSinglePagination;

@Entity(primaryKeys = {"id"})
public class MovieModelRoom {

    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "popularity")
    private Double popularity;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "vote_average")
    private Double voteAverage;

    @ColumnInfo(name = "vote_count")
    private Integer voteCount;

    public MovieModelRoom() {

    }

    public MovieModelRoom(MovieSinglePagination movieSinglePagination) {
        this.id = movieSinglePagination.getId();
        this.overview = movieSinglePagination.getOverview();
        this.title = movieSinglePagination.getTitle();
        this.popularity = movieSinglePagination.getPopularity();
        this.posterPath = movieSinglePagination.getPosterPath();
        this.releaseDate = movieSinglePagination.getReleaseDate();
        this.voteAverage = movieSinglePagination.getVoteAverage();
        this.voteCount = movieSinglePagination.getVoteCount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

package com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.Movie;

@Entity(primaryKeys = {"id"})
public class Favorites {
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "poster_path")
    private String posterPath;

    @ColumnInfo(name = "release_date")
    private String releaseDate;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "popularity")
    private Double popularity;

    @ColumnInfo(name = "vote_average")
    private Double voteAverage;

    @ColumnInfo(name = "vote_count")
    private int voteCount;

    public Favorites() {

    }

    public Favorites(Movie movie) {
        id = movie.getId();
        posterPath = movie.getPosterPath();
        releaseDate = movie.getReleaseDate();
        title = movie.getTitle();
        popularity = movie.getPopularity();
        voteAverage = movie.getVoteAverage();
        voteCount = movie.getVoteCount();
        overview = movie.getOverview();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

}

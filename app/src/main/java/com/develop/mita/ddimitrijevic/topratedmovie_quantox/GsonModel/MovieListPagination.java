package com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class MovieListPagination{

    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    private List<MovieSinglePagination> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieSinglePagination> getResults() {
        return results;
    }

    public void setResults(List<MovieSinglePagination> results) {
        this.results = results;
    }

}

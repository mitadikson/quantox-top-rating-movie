package com.develop.mita.ddimitrijevic.topratedmovie_quantox.Api;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.Movie;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieCredits;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.GsonModel.MovieListPagination;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MovieListPagination> getMoviePaginationFirstPage();

    @GET("movie/top_rated")
    Call<MovieListPagination> getMoviePagination(@Query("api_key") String apiKey,
                                                 @Query("page") String page,
                                                 @Query("language") String language);

    @GET("movie/{movie_id}")
    Call<Movie> getMovieDetails(@Path("movie_id") String movieId, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/credits")
    Call<MovieCredits> getMovieCredits(@Path("movie_id") String movieId, @Query("api_key") String apiKey);

    @GET("genre/{genre_id}")
    Call<MovieCredits> getGenreMovie(@Path("genre_id") String movieId, @Query("api_key") String apiKey);

}

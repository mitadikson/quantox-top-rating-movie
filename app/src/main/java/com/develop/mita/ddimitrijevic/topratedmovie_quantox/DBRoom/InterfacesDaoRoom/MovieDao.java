package com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.InterfacesDaoRoom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom.MovieModelRoom;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM MOVIEMODELROOM")
    List<MovieModelRoom> loadAllMovies();

    @Insert(onConflict = IGNORE)
    void insertMovie(MovieModelRoom movie);

    @Query("SELECT * FROM MOVIEMODELROOM WHERE id = :id LIMIT 1")
    MovieModelRoom findMovie(int id);

    @Delete
    void deleteMovie(MovieModelRoom movie);

    @Query("DELETE FROM MOVIEMODELROOM")
    void deleteAllMovie();
}


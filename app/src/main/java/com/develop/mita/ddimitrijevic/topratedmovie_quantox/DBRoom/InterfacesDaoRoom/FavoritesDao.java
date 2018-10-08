package com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.InterfacesDaoRoom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom.Favorites;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface FavoritesDao {
    @Query("SELECT * FROM Favorites")
    List<Favorites> loadAllFavorites();

    @Insert(onConflict = IGNORE)
    void insertFavorites(Favorites favorites);

    @Query("SELECT * FROM Favorites WHERE id = :id LIMIT 1")
    Favorites findFavorites(int id);

    @Delete
    void deleteFavorites(Favorites favorites);

    @Query("DELETE FROM Favorites")
    void deleteAllFavorites();
}


package com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom.Favorites;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.EntityModelRoom.MovieModelRoom;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.InterfacesDaoRoom.FavoritesDao;
import com.develop.mita.ddimitrijevic.topratedmovie_quantox.DBRoom.InterfacesDaoRoom.MovieDao;

@Database(entities = {Favorites.class, MovieModelRoom.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract FavoritesDao favoritesModel();

    public abstract MovieDao movieModel();

    public static AppDatabase getMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static AppDatabase getFileDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "_quantox")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}

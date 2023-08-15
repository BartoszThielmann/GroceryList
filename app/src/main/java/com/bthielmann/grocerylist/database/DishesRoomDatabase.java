package com.bthielmann.grocerylist.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bthielmann.grocerylist.database.dishes.Dishes;
import com.bthielmann.grocerylist.database.dishes.DishesDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Dishes.class}, version = 1, exportSchema = false)
public abstract class DishesRoomDatabase extends RoomDatabase {

    public abstract DishesDao dishesDao();

    private static volatile DishesRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DishesRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DishesRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    DishesRoomDatabase.class, "dishes_database")
                            .createFromAsset("database/dishes.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
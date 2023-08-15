package com.bthielmann.grocerylist.database.dishes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DishesDao {
    @Query("SELECT * FROM dishes_table")
    LiveData<List<Dishes>> getAll();
}

package com.bthielmann.grocerylist.database.dishes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DishesDao {
    @Query("SELECT * FROM dishes_table")
    LiveData<List<Dishes>> getAll();

    @Insert
    public void insertDish(Dishes dish);

    @Query("DELETE FROM dishes_table WHERE dish_name = :dish_name")
    public void deleteDish(String dish_name);

}

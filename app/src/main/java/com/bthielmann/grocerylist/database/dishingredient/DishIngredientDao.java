package com.bthielmann.grocerylist.database.dishingredient;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.bthielmann.grocerylist.database.ingredients.Ingredient;

import java.util.List;

@Dao
public interface DishIngredientDao {

    @Query("SELECT * FROM dishes_ingredients_table di JOIN dishes_table d ON d.id = di.dish_id JOIN ingredients_table i ON i.id = di.ingredient_id WHERE d.dish_name = :dish_name")
    LiveData<List<Ingredient>> getIngredientsOfDish(String dish_name);
}
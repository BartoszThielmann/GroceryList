package com.bthielmann.grocerylist.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bthielmann.grocerylist.database.dishes.Dishes;
import com.bthielmann.grocerylist.database.dishes.DishesDao;
import com.bthielmann.grocerylist.database.dishingredient.DishIngredientDao;
import com.bthielmann.grocerylist.database.ingredients.Ingredient;
import com.bthielmann.grocerylist.database.ingredients.IngredientDao;

import java.util.List;

public class DishesRepository {

    private DishesDao mDishesDao;
    private DishIngredientDao mDishIngredientDao;
    private LiveData<List<Dishes>> mAllDishes;

    // Note that in order to unit test the DishesRepository,
    // Application  dependency has to be removed
    public DishesRepository(Application application) {
        DishesRoomDatabase db = DishesRoomDatabase.getDatabase(application);
        mDishesDao = db.dishesDao();
        mAllDishes = mDishesDao.getAll();
        mDishIngredientDao = db.dishIngredientDao();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Dishes>> getAllDishes() {
        return mAllDishes;
    }

    // This must be called on a non-UI thread or the app will throw an exception. Room ensures
    // that the app is not doing any long running operations on the main thread, blocking the UI.
    public void insertDish(Dishes dish) {
        DishesRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDishesDao.insertDish(dish);
        });
    }

    public void deleteDish(String dish_name) {
        DishesRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDishesDao.deleteDish(dish_name);
        });
    }

    public LiveData<List<Ingredient>> getIngredientsOfDish(String dish_name){
        // return ingredients of requested dish
        return mDishIngredientDao.getIngredientsOfDish(dish_name);
    }
}
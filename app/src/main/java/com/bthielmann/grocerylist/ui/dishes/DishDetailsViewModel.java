package com.bthielmann.grocerylist.ui.dishes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bthielmann.grocerylist.database.DishesRepository;
import com.bthielmann.grocerylist.database.dishes.Dishes;
import com.bthielmann.grocerylist.database.ingredients.Ingredient;

import java.util.List;

public class DishDetailsViewModel extends AndroidViewModel {

    private DishesRepository mRepository;

    public DishDetailsViewModel (Application application) {
        super(application);
        mRepository = new DishesRepository(application);
    }

    void deleteDish(String dish_name){ mRepository.deleteDish(dish_name);}

    LiveData<List<Ingredient>> getIngredientsOfDish(String dish_name) {
        return mRepository.getIngredientsOfDish(dish_name);
    }
}
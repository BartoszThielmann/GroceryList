package com.bthielmann.grocerylist.ui.dishes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.bthielmann.grocerylist.database.DishesRepository;
import com.bthielmann.grocerylist.database.dishes.Dishes;

public class DishDetailsViewModel extends AndroidViewModel {

    private DishesRepository mRepository;

    public DishDetailsViewModel (Application application) {
        super(application);
        mRepository = new DishesRepository(application);
    }

    void deleteDish(String dish_name){ mRepository.deleteDish(dish_name);}
}
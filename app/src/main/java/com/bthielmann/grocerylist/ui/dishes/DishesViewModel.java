package com.bthielmann.grocerylist.ui.dishes;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.bthielmann.grocerylist.database.DishesRepository;
import com.bthielmann.grocerylist.database.dishes.Dishes;

import java.util.List;

public class DishesViewModel extends AndroidViewModel {

    private DishesRepository mRepository;
    LiveData<List<Dishes>> mAllDishes;

    public DishesViewModel (Application application) {
        super(application);
        mRepository = new DishesRepository(application);
        mAllDishes = mRepository.getAllDishes();
    }

    LiveData<List<Dishes>> getAllDishes() {
        return mAllDishes;
    }

    void insertDish(Dishes dish) {
        mRepository.insertDish(dish);
    }
}
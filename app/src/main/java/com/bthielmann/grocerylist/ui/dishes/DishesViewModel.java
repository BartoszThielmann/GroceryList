package com.bthielmann.grocerylist.ui.dishes;

import androidx.lifecycle.ViewModel;
import com.bthielmann.grocerylist.database.Datasource;

public class DishesViewModel extends ViewModel {
    public String[] dishesData;

    public DishesViewModel () {
        dishesData = Datasource.loadData();
    }
}
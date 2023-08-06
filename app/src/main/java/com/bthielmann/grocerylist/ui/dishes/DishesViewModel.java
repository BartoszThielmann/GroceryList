package com.bthielmann.grocerylist.ui.dishes;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.bthielmann.grocerylist.database.Datasource;

public class DishesViewModel extends ViewModel {
    public String[] carsData;

    public DishesViewModel () {
        carsData = Datasource.loadData();
    }
}
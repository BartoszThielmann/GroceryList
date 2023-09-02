package com.bthielmann.grocerylist.ui.dishes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.bthielmann.grocerylist.R;

public class DishDetailsActivity extends AppCompatActivity {

    private DishDetailsViewModel mDishDetailsViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_details);
        TextView dishName = findViewById(R.id.dish_name);

        Intent intent = getIntent();
        String dish_name = intent.getStringExtra("dish_name");
        dishName.setText(dish_name);

        // TODO: Get ingredients from Repository

        // Get a new or existing ViewModel from the ViewModelProvider.
        mDishDetailsViewModel = new ViewModelProvider(this).get(DishDetailsViewModel.class);

        final Button removeButton = findViewById(R.id.button_remove);
        removeButton.setOnClickListener(view -> {
            mDishDetailsViewModel.deleteDish(dish_name);
            Toast.makeText(
                    getApplicationContext(),
                    "Dish removed!",
                    Toast.LENGTH_LONG).show();
            finish();
        });
    }
}
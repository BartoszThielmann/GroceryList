package com.bthielmann.grocerylist.ui.dishes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.bthielmann.grocerylist.R;

public class DishDetailsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_details);

        TextView dishName = findViewById(R.id.dish_name);
        Intent intent = getIntent();
        String dish_name = intent.getStringExtra("dish_name");
        dishName.setText(dish_name);

        // TODO: Add ViewModel to get ingredients from Repository
    }
}
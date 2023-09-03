package com.bthielmann.grocerylist.ui.dishes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.bthielmann.grocerylist.R;
import com.bthielmann.grocerylist.database.ingredients.Ingredient;

import java.util.List;

public class DishDetailsActivity extends AppCompatActivity {

    public static class RemoveDialogFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.question_remove_dish)
                    .setPositiveButton(R.string.confirm,
                            (dialogInterface, i) -> ((DishDetailsActivity)getActivity()).doPositiveClick())
                    .setNegativeButton(R.string.cancel,
                            (dialogInterface, i) -> ((DishDetailsActivity)getActivity()).doNegativeClick());
            return builder.create();
        }
    }

    private DishDetailsViewModel mDishDetailsViewModel;
    private String mDishName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish_details);
        TextView dishNameView = findViewById(R.id.dish_name);
        TextView ingredientsView = findViewById(R.id.ingredients);

        Intent intent = getIntent();
        mDishName = intent.getStringExtra("dish_name");
        dishNameView.setText(mDishName);

        // Get a new or existing ViewModel from the ViewModelProvider.
        mDishDetailsViewModel = new ViewModelProvider(this).get(DishDetailsViewModel.class);

        // Create the observer which updates the UI.
        final Observer<List<Ingredient>> ingredientsObserver = new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(@Nullable final List<Ingredient> newIngredients) {
                String ingredientsDisplay = "";
                // Update the UI, in this case, a TextView.
                for (Ingredient ingr : newIngredients){
                    ingredientsDisplay += ingr.getIngredientName() + "\n";
                }
                ingredientsView.setText(ingredientsDisplay);
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mDishDetailsViewModel.getIngredientsOfDish(mDishName)
                .observe(this, ingredientsObserver);

        final Button removeButton = findViewById(R.id.button_remove);
        removeButton.setOnClickListener(view -> {
            showRemoveDialog();
        });
    }

    private void showRemoveDialog() {
        DialogFragment removeDialog = new RemoveDialogFragment();
        removeDialog.show(getSupportFragmentManager(), "remove_dialog");
    }

    private void doPositiveClick() {
        mDishDetailsViewModel.deleteDish(mDishName);
        finish();
    }

    private void doNegativeClick() {
        Toast.makeText(
                getApplicationContext(),
                "Cancelled!",
                Toast.LENGTH_LONG).show();
    }
}
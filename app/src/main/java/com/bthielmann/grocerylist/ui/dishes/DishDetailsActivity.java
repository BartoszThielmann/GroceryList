package com.bthielmann.grocerylist.ui.dishes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import com.bthielmann.grocerylist.R;

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

        Intent intent = getIntent();
        mDishName = intent.getStringExtra("dish_name");
        dishNameView.setText(mDishName);

        // TODO: Get ingredients from Repository

        // Get a new or existing ViewModel from the ViewModelProvider.
        mDishDetailsViewModel = new ViewModelProvider(this).get(DishDetailsViewModel.class);

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
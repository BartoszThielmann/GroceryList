package com.bthielmann.grocerylist.ui.dishes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bthielmann.grocerylist.databinding.FragmentDishesBinding;

public class DishesFragment extends Fragment {

    private FragmentDishesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DishesViewModel dishesViewModel =
                new ViewModelProvider(this).get(DishesViewModel.class);

        binding = FragmentDishesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textDishes;
        dishesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
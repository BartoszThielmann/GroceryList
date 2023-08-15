package com.bthielmann.grocerylist.ui.dishes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bthielmann.grocerylist.R;
import com.bthielmann.grocerylist.databinding.FragmentDishesBinding;

public class DishesFragment extends Fragment {
    private FragmentDishesBinding binding;
    private DishesViewModel dishesViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dishesViewModel = new ViewModelProvider(this).get(DishesViewModel.class);

        // Inflate the layout for this fragment
        binding = FragmentDishesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        DishesListAdapter dishesListAdapter =
                new DishesListAdapter(new DishesListAdapter.WordDiff());

        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        // adapter instance is set to the recyclerview to inflate the items.
        recyclerView.setAdapter(dishesListAdapter);

        dishesViewModel.getAllDishes().observe(getViewLifecycleOwner(), dishes -> {
            // Update the cached copy of the words in the adapter.
            dishesListAdapter.submitList(dishes);
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
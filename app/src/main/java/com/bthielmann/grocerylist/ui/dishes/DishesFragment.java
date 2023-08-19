package com.bthielmann.grocerylist.ui.dishes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bthielmann.grocerylist.R;
import com.bthielmann.grocerylist.database.dishes.Dishes;
import com.bthielmann.grocerylist.databinding.FragmentDishesBinding;

public class DishesFragment extends Fragment {
    private FragmentDishesBinding binding;
    private DishesViewModel dishesViewModel;

    ActivityResultLauncher<Intent> mGetContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGetContent = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Dishes dish = new Dishes(data.getStringExtra(NewDishActivity.EXTRA_REPLY));
                            dishesViewModel.insertDish(dish);
                        } else {
                            Toast.makeText(
                                    getContext(),
                                    R.string.failed_to_save,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
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
            // Update the cached copy of the dishes in the adapter.
            dishesListAdapter.submitList(dishes);
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NewDishActivity.class);
                mGetContent.launch(intent);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
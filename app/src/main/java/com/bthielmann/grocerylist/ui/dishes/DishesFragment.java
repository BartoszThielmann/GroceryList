package com.bthielmann.grocerylist.ui.dishes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bthielmann.grocerylist.R;
import com.bthielmann.grocerylist.database.Datasource;
import com.bthielmann.grocerylist.databinding.FragmentDishesBinding;

public class DishesFragment extends Fragment {
    private FragmentDishesBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DishesViewModel dishesViewModel =
                new ViewModelProvider(this).get(DishesViewModel.class);

        // Inflate the layout for this fragment
        binding = FragmentDishesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        // Assign employeelist to ItemAdapter
        DishesListAdapter dishesListAdapter = new DishesListAdapter(Datasource.loadData());

        RecyclerView recyclerView
                = view.findViewById(R.id.recyclerview);
        // adapter instance is set to the
        // recyclerview to inflate the items.
        recyclerView.setAdapter(dishesListAdapter);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
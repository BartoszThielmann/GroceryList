package com.bthielmann.grocerylist.ui.dishes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bthielmann.grocerylist.R;
import com.bthielmann.grocerylist.database.dishes.Dishes;


// The RecyclerView requests views, and binds the views to their data,
// by calling methods in the adapter.
public class DishesListAdapter extends ListAdapter<Dishes, DishesListAdapter.ViewHolder> {

    // Provide pool of views for the RecyclerView to use and reuse to display data
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = (TextView) view.findViewById(R.id.textView);
        }

        public TextView getTextView() {
            return textView;
        }

        public void bind(String text) {
            textView.setText(text);
        }
    }

    public DishesListAdapter(@NonNull DiffUtil.ItemCallback<Dishes> diffCallback) {
        super(diffCallback);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dish_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Dishes current = getItem(position);
        viewHolder.bind(current.getDishName());
    }


    static class WordDiff extends DiffUtil.ItemCallback<Dishes> {

        @Override
        public boolean areItemsTheSame(@NonNull Dishes oldItem, @NonNull Dishes newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Dishes oldItem, @NonNull Dishes newItem) {
            return oldItem.getDishName().equals(newItem.getDishName());
        }
    }
}

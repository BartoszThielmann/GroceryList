package com.bthielmann.grocerylist.ui.dishes;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bthielmann.grocerylist.MainActivity;
import com.bthielmann.grocerylist.R;
import com.bthielmann.grocerylist.database.dishes.Dishes;


// The RecyclerView requests views, and binds the views to their data,
// by calling methods in the adapter.
public class DishesListAdapter extends ListAdapter<Dishes, DishesListAdapter.ViewHolder> {

    private int position;

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

        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPosition(viewHolder.getAdapterPosition());
                Intent intent = new Intent(viewHolder.textView.getContext(), DishDetailsActivity.class);
                intent.putExtra("dish_name", current.getDishName());
                viewHolder.textView.getContext().startActivity(intent);
            }
        });
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

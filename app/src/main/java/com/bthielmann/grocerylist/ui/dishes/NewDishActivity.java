package com.bthielmann.grocerylist.ui.dishes;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.bthielmann.grocerylist.R;

public class NewDishActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.disheslistsql.REPLY";
    private EditText mEditDishView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_dish);
        mEditDishView = findViewById(R.id.edit_dish);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditDishView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String dish = mEditDishView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, dish);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}

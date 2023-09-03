package com.bthielmann.grocerylist.database.ingredients;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingredients_table")
public class Ingredient {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "ingredient_name")
    private String mIngredientName;

    public Ingredient(@NonNull String mIngredientName) {
        this.mId = 0;
        this.mIngredientName = mIngredientName;
    }

    public int getId() { return mId; }

    public void setId(int mId) { this.mId = mId; }

    @NonNull
    public String getIngredientName() { return mIngredientName; }

    public void setIngredientName(@NonNull String mIngredientName) { this.mIngredientName = mIngredientName; }
}
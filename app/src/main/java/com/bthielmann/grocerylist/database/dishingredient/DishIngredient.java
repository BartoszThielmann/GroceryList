package com.bthielmann.grocerylist.database.dishingredient;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.bthielmann.grocerylist.database.dishes.Dishes;
import com.bthielmann.grocerylist.database.ingredients.Ingredient;

@Entity(tableName = "dishes_ingredients_table",
        foreignKeys = {
        @ForeignKey(entity = Dishes.class,
        parentColumns = "id",
        childColumns = "dish_id"),
        @ForeignKey(entity = Ingredient.class,
        parentColumns = "id",
        childColumns = "ingredient_id")})
public class DishIngredient {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "dish_id")
    private int mDishId;

    @NonNull
    @ColumnInfo(name = "ingredient_id")
    private int mIngredientId;

    public DishIngredient(@NonNull int mDishId, @NonNull int mIngredientId) {
        this.mId = 0;
        this.mDishId = mDishId;
        this.mIngredientId = mIngredientId;
    }

    public int getId() { return mId; }

    public void setId(int mId) { this.mId = mId; }

    public int getDishId() { return mDishId; }

    public void setDishId(int mDishId) { this.mDishId = mDishId; }

    public int getIngredientId() { return mIngredientId; }

    public void setIngredientId(int mIngredientId) { this.mIngredientId = mIngredientId; }
}
package com.bthielmann.grocerylist.database.dishes;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "dishes_table")
public class Dishes {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int mId;

    @NonNull
    @ColumnInfo(name = "dish_name")
    private String mDishName;

    public Dishes(@NonNull String mDishName) {
        this.mId = 0;
        this.mDishName = mDishName;
    }

    public int getId() { return mId; }

    public void setId(int mId) { this.mId = mId; }

    @NonNull
    public String getDishName() { return mDishName; }

    public void setDishName(@NonNull String mDishName) { this.mDishName = mDishName; }
}



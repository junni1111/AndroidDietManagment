package com.dadada.app.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "diet_log_table")
public class DietLog extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "diet_food_name")
    private String foodName;

    @ColumnInfo(name = "diet_food_count")
    private int foodCount;

    @ColumnInfo(name = "diet_food_calorie")
    private int foodCalorie;

    @ColumnInfo(name = "diet_image_path")
    private String imagePath;

    @ColumnInfo(name = "diet_address")
    private String address;

    @ColumnInfo(name = "diet_date_day")
    private String day;

    @ColumnInfo(name = "diet_date_time")
    private String time;


    public DietLog(String foodName, int foodCount, int foodCalorie, String imagePath
            , String address, String day, String time) {
        this.foodName = foodName;
        this.foodCount = foodCount;
        this.foodCalorie = foodCalorie;
        this.imagePath = imagePath;
        this.address = address;
        this.day = day;
        this.time = time;
    }

    @Ignore
    public DietLog() {
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
        notifyPropertyChanged(BR.foodName);
    }

    @Bindable
    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int foodCount) {
        this.foodCount = foodCount;
        notifyPropertyChanged(BR.foodCount);
    }

    @Bindable
    public int getFoodCalorie() {
        return foodCalorie;
    }

    public void setFoodCalorie(int foodCalorie) {
        this.foodCalorie = foodCalorie;
        notifyPropertyChanged(BR.foodCalorie);
    }

    @Bindable
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        notifyPropertyChanged(BR.imagePath);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.address);
    }

    @Bindable
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
        notifyPropertyChanged(BR.day);
    }

    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }

}


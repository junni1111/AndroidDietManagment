package com.dadada.app.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class DietParcelable implements Parcelable {
    private String imagePath;
    private String name;
    private int calorie;
    private String category;
    private String quantity;
    private String day;
    private String time;
    private String memo;
    private int rating;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public DietParcelable(String imagePath, String name, int calorie, String category, String quantity,
                          String day, String time, String memo, int rating) {
        this.imagePath = imagePath;
        this.name = name;
        this.calorie = calorie;
        this.category = category;
        this.quantity = quantity;
        this.day = day;
        this.time = time;
        this.memo = memo;
        this.rating = rating;
    }

    protected DietParcelable(Parcel in) {
        imagePath = in.readString();
        name = in.readString();
        calorie = in.readInt();
        category = in.readString();
        quantity = in.readString();
        day = in.readString();
        time = in.readString();
        rating = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imagePath);
        dest.writeString(name);
        dest.writeInt(calorie);
        dest.writeString(category);
        dest.writeString(quantity);
        dest.writeString(day);
        dest.writeString(time);
        dest.writeInt(rating);
    }

    public static final Creator<DietParcelable> CREATOR = new Creator<DietParcelable>() {
        @Override
        public DietParcelable createFromParcel(Parcel source) {
            return new DietParcelable(source);
        }

        @Override
        public DietParcelable[] newArray(int size) {
            return new DietParcelable[0];
        }
    };
}

//    @PrimaryKey(autoGenerate = true)
//    private int id;
//
//    @ColumnInfo(name = "diet_food_name")
//    private String foodName;
//
//    @ColumnInfo(name = "diet_food_count")
//    private int foodCount;
//
//    @ColumnInfo(name = "diet_food_calorie")
//    private int foodCalorie;
//
//    @ColumnInfo(name = "diet_image_path")
//    private String imagePath;
//
//    @ColumnInfo(name = "diet_address")
//    private String address;
//
//    @ColumnInfo(name = "diet_date_day")
//    private String day;
//
//
//    @ColumnInfo(name = "diet_date_time")
//    private String time;


package com.dadada.app.recyclerView;

public class Diet {
    // 음식 이름, 수량, 사진, 위치(구글 맵), 식사 시간
    String foodName;
    int foodCount;
    int foodCalorie;


    String imagePath;
    String address;
    String date;

    public String getFoodName() {
        return foodName;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getAddress() {
        return address;
    }

    public String getDate() {
        return date;
    }

    public Diet(String foodName, int foodCount, int foodCalorie, String imagePath, String address, String date) {
        this.foodName = foodName;
        this.foodCount = foodCount;
        this.foodCalorie = foodCalorie;
        this.imagePath = imagePath;
        this.address = address;
        this.date = date;
    }

    public int getFoodCalorie() {
        return foodCalorie;
    }
}

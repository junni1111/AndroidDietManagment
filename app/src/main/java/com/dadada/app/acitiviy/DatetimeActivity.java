package com.dadada.app.acitiviy;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.dadada.app.R;
import com.dadada.app.parcelable.DietParcelable;

public class DatetimeActivity extends AppCompatActivity {
    private DietParcelable data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datetime);

        data = getIntent().getParcelableExtra("data");
        Log.d("datetime", data.getImagePath());
        Log.d("datetime", data.getName());
        Log.d("datetime", data.getCategory());
        Log.d("datetime", data.getQuantity());

    }
}
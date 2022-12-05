package com.dadada.app.acitiviy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.dadada.app.R;
import com.dadada.app.parcelable.DietParcelable;

public class DatetimeActivity extends AppCompatActivity {
    private DietParcelable data;
    private Button nextBtn;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datetime);

        data = getIntent().getParcelableExtra("data");
        Log.d("datetime", data.getImagePath());
        Log.d("datetime", data.getName());
        Log.d("datetime", data.getCategory());
        Log.d("datetime", data.getQuantity());


        backBtn = findViewById(R.id.backBtn);
        nextBtn = findViewById(R.id.nextBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DatetimeActivity.this, MemoActivity.class);
                data.setDay("");
                data.setTime("");
                i.putExtra("data", data);
                startActivity(i);
            }
        });

    }
}
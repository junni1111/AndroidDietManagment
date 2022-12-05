package com.dadada.app.acitiviy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.dadada.app.R;
import com.dadada.app.parcelable.DietParcelable;

public class FinishActivity extends AppCompatActivity {
    private DietParcelable data;
    private Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        data = getIntent().getParcelableExtra("data");
        Log.d("finish", data.getImagePath());
        Log.d("finish", data.getName());
        Log.d("finish", data.getCategory());
        Log.d("finish", data.getQuantity());
        Log.d("finish", data.getDay() + " " + data.getTime());
        Log.d("finish", data.getMemo());
        Log.d("finish", "" + data.getRating());


        nextBtn = findViewById(R.id.nextBtn);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FinishActivity.this, MapActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

    }
}
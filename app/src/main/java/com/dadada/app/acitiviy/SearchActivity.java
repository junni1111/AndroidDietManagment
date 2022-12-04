package com.dadada.app.acitiviy;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dadada.app.R;
import com.dadada.app.parcelable.DietParcelable;

public class SearchActivity extends AppCompatActivity {
    private DietParcelable data;
    ImageView backBtn;
    EditText searchEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent i = getIntent();
        data = i.getParcelableExtra("data");
        Toast.makeText(this, "" + data.getImagePath(), Toast.LENGTH_SHORT).show();

        backBtn = findViewById(R.id.backBtn);
        searchEdt = findViewById(R.id.searchEdt);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 입력난에 변화가 있을 시 조치
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                // 입력이 끝났을 때 조치
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 입력하기 전에 조치
            }
        });

    }
}
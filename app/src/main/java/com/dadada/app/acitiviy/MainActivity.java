package com.dadada.app.acitiviy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dadada.app.R;
import com.dadada.app.model.DietLog;
import com.dadada.app.recyclerView.DietAdapter;
import com.dadada.app.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView view;
    private RecyclerView.LayoutManager layoutManager;
    public static DietAdapter adapter;
    public static MainActivityViewModel mainActivityViewModel;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);

        view = findViewById(R.id.RVdiet);
        adapter = new DietAdapter(this);
        setAdapter();

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        addNewDiet();

        mainActivityViewModel.getAllDietLogs().observe(this, dietLogs -> adapter.setData(dietLogs));


        btn = findViewById(R.id.BTcamera);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PhotoActivity.class);
                startActivity(i);
            }
        });
    }

    void setAdapter() {
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setHasFixedSize(true);
        view.setItemAnimator(new DefaultItemAnimator());
        view.setAdapter(adapter);
    }

    void addNewDiet() {
        Intent i = getIntent();
        if (i.getExtras() != null) {
            String currentPhotoPath = i.getStringExtra("imgPath");
            mainActivityViewModel.addNewDietLog(new DietLog("김밥", 1, 123, currentPhotoPath, "서울특별시", "2020-03-01", "09:00:00"));
        }
    }

}
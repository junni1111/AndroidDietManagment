package com.dadada.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dadada.app.model.DietLog;
import com.dadada.app.recyclerView.DietAdapter;
import com.dadada.app.viewmodel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView view;
    private RecyclerView.LayoutManager layoutManager;
    public static DietAdapter adapter;
    Button btn;


    public static MainActivityViewModel mainActivityViewModel;
    private ArrayList<List<DietLog>> dietLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.RVdiet);
        adapter = new DietAdapter(this);
        setAdapter();

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getAllDietLogs().observe(this, dietLogs -> adapter.setData(dietLogs));


        btn = findViewById(R.id.BTcamera);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(MainActivity.this, CameraActivity.class);
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

}
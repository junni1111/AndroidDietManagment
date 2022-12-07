package com.dadada.app.acitiviy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dadada.app.R;
import com.dadada.app.model.DietLogDAO;
import com.dadada.app.model.DietLogDatabase;
import com.dadada.app.recyclerView.DietAdapter;
import com.dadada.app.viewmodel.MainActivityViewModel;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private RecyclerView view;
    private RecyclerView.LayoutManager layoutManager;
    public static DietAdapter adapter;
    public static MainActivityViewModel mainActivityViewModel;
    private MaterialDatePicker datePicker;
    private String s = "";
    private DietLogDatabase db;
    private DietLogDAO dietLogDAO;

    Button btn, BTdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.RVdiet);
        adapter = new DietAdapter(this);
        setAdapter();

        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        addNewDiet();

        datePicker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Select date")
                .build();

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selectedDate) {
                TimeZone timeZoneUTC = TimeZone.getDefault();
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
                Date date = new Date(selectedDate + offsetFromUTC);

                s = simpleFormat.format(date);

                mainActivityViewModel.setInput(s);
            }
        });

        mainActivityViewModel.dietLogByDay.observe(this, dietLogs -> adapter.setData(dietLogs));


        TimeZone timeZoneUTC = TimeZone.getDefault();
        // It will be negative, so that's the -1
        int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

        // Create a date format, then a date object with our offset
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm", Locale.KOREA);
        Date date = new Date(offsetFromUTC);

        Date now = new Date();
        long oneMonthBefore = (long) now.getTime() - (long) 2592000 * 1000;
        Log.d("date", simpleFormat.format(new Date()));

        mainActivityViewModel.getCaloriesAfterDate(oneMonthBefore).observe(this, calorie -> Log.d("calorie", "" + calorie));

        btn = findViewById(R.id.BTcamera);
        BTdate = findViewById(R.id.BTdate);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MapActivity.class);
                startActivity(i);
                finish();
            }
        });

        BTdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "fragment_tag");
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
            //mainActivityViewModel.addNewDietLog(new DietLog("김밥", 1, 123, currentPhotoPath, "서울특별시", "2020-03-01", "09:00:00"));
        }
    }

}
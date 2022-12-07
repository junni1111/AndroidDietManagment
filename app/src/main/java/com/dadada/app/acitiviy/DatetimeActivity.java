package com.dadada.app.acitiviy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dadada.app.R;
import com.dadada.app.parcelable.DietParcelable;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DatetimeActivity extends AppCompatActivity {
    private DietParcelable data;
    private Button nextBtn;
    private ImageView backBtn;
    private TextView datePickerTxt, timePickerTxt;
    private MaterialDatePicker datePicker;
    private MaterialTimePicker timePicker;
    private String selectedDay = "", selectedTime = "";
    private int selectedDate;

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
        datePickerTxt = findViewById(R.id.datePickerTxt);
        timePickerTxt = findViewById(R.id.timePickerTxt);

        datePicker = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText("Select date of birth")
                .build();

        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build();

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selectedDate) {
                // user has selected a date
                // format the date and set the text of the input box to be the selected date
                // right now this format is hard-coded, this will change
                ;
                // Get the offset from our timezone and UTC.
                TimeZone timeZoneUTC = TimeZone.getDefault();
                // It will be negative, so that's the -1
                int offsetFromUTC = timeZoneUTC.getOffset(new Date().getTime()) * -1;

                // Create a date format, then a date object with our offset
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
                Date date = new Date(selectedDate + offsetFromUTC);

                datePickerTxt.setText(simpleFormat.format(date));
                selectedDay = simpleFormat.format(date);
                if (selectedTime != "" && selectedDay != "") {
                    setNextBtnSelectedMode();
                }
            }
        });

        timePicker.addOnPositiveButtonClickListener(dialog -> {
            int newHour = timePicker.getHour();
            int newMinute = timePicker.getMinute();
            timePickerTxt.setText(newHour + ":" + newMinute);
            selectedTime = newHour + ":" + newMinute;

            if (selectedTime != "" && selectedDay != "") {
                setNextBtnSelectedMode();
            }
        });

        datePickerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "fragment_tag");
            }
        });

        timePickerTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.show(getSupportFragmentManager(), "fragment_tag");
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedTime == "" || selectedDay == "") {
                    return;
                }

                Intent i = new Intent(DatetimeActivity.this, MemoActivity.class);
                data.setDay(selectedDay);
                data.setTime(selectedTime);
                i.putExtra("data", data);
                startActivity(i);
            }
        });

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setNextBtnSelectedMode() {
        nextBtn.setBackground(this.getResources().getDrawable(R.drawable.r12_primary_solid));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void setNextBtnUnSelectedMode() {
        nextBtn.setBackground(this.getResources().getDrawable(R.drawable.r12_lightgray_solid));
    }
}
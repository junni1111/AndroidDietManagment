package com.dadada.app.acitiviy;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.dadada.app.R;
import com.dadada.app.model.DietLog;
import com.dadada.app.parcelable.DietParcelable;
import com.dadada.app.viewmodel.MainActivityViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FinishActivity extends AppCompatActivity {
    private DietParcelable data;
    private Button nextBtn;
    public static MainActivityViewModel mainActivityViewModel;

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
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String from = data.getDay() + " " + data.getTime();
                SimpleDateFormat transFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");

                try {
                    Date to = transFormat.parse(from);
                    DietLog dietLog = new DietLog(data.getName(), 1, 235,
                            data.getImagePath(), "", "", data.getMemo(), data.getCategory()
                            , data.getQuantity(), data.getRating(), data.getDay(), data.getTime(), (long) to.getTime());
                    mainActivityViewModel.addNewDietLog(dietLog);


                    Intent i = new Intent(FinishActivity.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });

    }
}
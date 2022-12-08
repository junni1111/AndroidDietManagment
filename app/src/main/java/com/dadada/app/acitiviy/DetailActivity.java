package com.dadada.app.acitiviy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.dadada.app.R;
import com.dadada.app.parcelable.DietParcelable;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.willy.ratingbar.BaseRatingBar;

import java.io.File;

public class DetailActivity extends AppCompatActivity implements GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback {
    private final int LOCATION_ACCESS_CODE = 1234;
    private DietParcelable data;
    private GoogleMap mMap = null;
    private ImageView posterViewImg;
    private TextView dietTitle;
    private TextView dietDate;
    private BaseRatingBar memoryDetailRating;
    private TextView memoTxt;
    private TextView mapLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        LOCATION_ACCESS_CODE);
            }
            return;
        }

        Intent i = getIntent();
        data = i.getParcelableExtra("data");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        assert mapFragment != null;
        mapFragment.getMapAsync(DetailActivity.this);

        posterViewImg = findViewById(R.id.posterViewImg);
        dietTitle = findViewById(R.id.dietTitle);
        dietDate = findViewById(R.id.dietDate);
        memoryDetailRating = findViewById(R.id.memoryDetailRating);
        memoTxt = findViewById(R.id.memoTxt);
        mapLocation = findViewById(R.id.mapLocation);

        try {
            File file = new File(data.getImagePath());
            Glide.with(this).load(Uri.fromFile(file)).into(posterViewImg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        dietTitle.setText(data.getName());

        String date = data.getDay() + " ";
        int hour = Integer.parseInt(data.getTime().split(":")[0]);
        int minute = Integer.parseInt(data.getTime().split(":")[1]);
        if (hour < 12) {
            if (hour == 0) hour += 12;
            date += "오전 " + "" + hour + ":" + minute;
        } else {
            if (hour != 12) hour -= 12;
            date += "오후 " + "" + hour + ":" + minute;
        }
        dietDate.setText(date);

        memoryDetailRating.setRating(data.getRating());

        if (data.getMemo().equals("")) {
            memoTxt.setText("메모가 없습니다.");
        } else {
            memoTxt.setText(data.getMemo());
        }

        mapLocation.setText(data.getAddress());

    }

    @Override
    public void onMapReady(@NonNull GoogleMap map) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        LOCATION_ACCESS_CODE);
            }
            return;
        }
        mMap = map;

        map.setMyLocationEnabled(true);
        map.setOnMyLocationButtonClickListener(this);

        double lat = Double.parseDouble(data.getLatlng().split("/")[0]);
        double lng = Double.parseDouble(data.getLatlng().split("/")[1]);

        LatLng place = new LatLng(lat, lng);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(place);
        markerOptions.title("음식");
        mMap.addMarker(markerOptions);


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 18));


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_ACCESS_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(DetailActivity.this, DetailActivity.class);
                startActivity(i);
                finish();
            }
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

}
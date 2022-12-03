package com.dadada.app;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback
{
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MainActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;

        LatLng seoul = new LatLng(37.56, 126.97);

        MarkerOptions options = new MarkerOptions();
        options.position(seoul)
                .title("서울")
                .snippet("한국의 수도");
        map.addMarker(options);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 10));
    }
}
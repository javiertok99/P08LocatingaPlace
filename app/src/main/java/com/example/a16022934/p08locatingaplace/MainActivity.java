package com.example.a16022934.p08locatingaplace;

import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button north, central, east;
    Spinner locations;
    private GoogleMap map;
    LatLng poi_North, poi_Central, poi_East;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                poi_North = new LatLng(1.443389, 103.785480);
                Marker north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654\n" +
                                "Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                poi_Central = new LatLng(1.308251, 103.779699);
                Marker central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                poi_East = new LatLng(1.345245, 103.931796);
                Marker east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

                LatLng poi_SG = new LatLng(1.352083, 103.819836);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG,
                        11));

                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                }

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        if(marker.getTitle().equals("North - HQ")){
                            Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }else if(marker.getTitle().equals("Central")){
                            Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }else if(marker.getTitle().equals("East")){
                            Toast.makeText(MainActivity.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });

            }
        });

        north =  findViewById(R.id.north);
        central =  findViewById(R.id.central);
        east =  findViewById(R.id.east);
        locations = findViewById(R.id.locations);

        north.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                            15));
                }
            }
        });

        central.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                            15));
                }
            }
        });

        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                            15));
                }
            }
        });

        locations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if(item.equals("North")){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                            15));
                }else if(item.equals("Central")){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Central,
                            15));
                }else if(item.equals("East")){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                            15));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }


}

package com.sourcey.theFixApp.maps;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sourcey.theFixApp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class findTecMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;

    private String catName;
    String [] itemData;

    List<MarkerOptions> tecMarkerList = new ArrayList<>();

    private DatabaseReference mRef;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0, locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_tec_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle extras = getIntent().getExtras();
        catName = extras.getString("cat");

        mRef = FirebaseDatabase.getInstance().getReference().child("Technician").child(catName);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

                mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));

                LatLng ArielTec1 = new LatLng(32.101671, 35.169689);
                mMap.addMarker(new MarkerOptions().position(ArielTec1).title("Cellphone Tec")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                LatLng ArielTec2 = new LatLng(32.105388, 35.173101);
                mMap.addMarker(new MarkerOptions().position(ArielTec2).title("Electric Tec")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));




                float zoomLevel = 14.0f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, zoomLevel));

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            LatLng userLocation = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());

            mMap.addMarker(new MarkerOptions().position(userLocation).title("Your Location"));

            mRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot postSnap : dataSnapshot.getChildren()){
                        String techData = postSnap.getValue().toString();

                        String cleanTechData = techData.substring(1, techData.length()-1)
                                .replace("address=", "").replace("email=", "")
                                .replace("id=","").replace("mobile=","")
                                .replace("name=","").replace("password=", "")
                                .replace("points=", "");
                        itemData = cleanTechData.split(",");

                        LatLng tecLocation = new LatLng(Double.parseDouble(itemData[1]), Double.parseDouble(itemData[2]));
                        MarkerOptions tecMarker = new MarkerOptions().position(tecLocation).title(itemData[4])
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

                        tecMarkerList.add(tecMarker);

                    }

                    for (MarkerOptions marker: tecMarkerList) { mMap.addMarker(marker); }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            float zoomLevel = 14.0f; //This goes up to 21
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, zoomLevel));

        }
    }
}
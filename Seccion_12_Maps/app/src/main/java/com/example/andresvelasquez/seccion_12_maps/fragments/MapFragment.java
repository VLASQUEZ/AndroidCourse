package com.example.andresvelasquez.seccion_12_maps.fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.andresvelasquez.seccion_12_maps.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback , GoogleMap.OnMarkerDragListener,View.OnClickListener, LocationListener{

    private GoogleMap map;
    private MapView mapView;
    private List<Address> addresses;
    private Geocoder geocoder;
    private MarkerOptions marker;
    private FloatingActionButton fab;
    private Location location;
    private LocationManager locationManager;
    private Marker mMarker;
    private CameraPosition cameraZoom;

    public MapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) view.findViewById(R.id.map);
        if(mapView != null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);




        //LatLng sydney = new LatLng(4.6097100, -74.0817500);
        /*marker = new MarkerOptions();
        marker.draggable(true);
        marker.position(sydney);
        marker.title("Caja de texto del marcador");
        marker.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on));

        map.addMarker(marker);
        map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").draggable(true));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(false);




        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,0,this);

        map.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                //Ejecuta una accion cuando se hace clic sobre la ubicacion

                return false;
            }
        });
        map.animateCamera(zoom);
        map.setOnMarkerDragListener(this);

        geocoder =new  Geocoder(this.getContext(), Locale.getDefault());
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        marker.hideInfoWindow();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        try{
            double latitude = marker.getPosition().latitude;
            double longitude = marker.getPosition().longitude;

            addresses = geocoder.getFromLocation(latitude,longitude,1);
        }
        catch (IOException e){
            Toast.makeText(this.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        String address = addresses.get(0).getAddressLine(0);
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        marker.setTitle(address);
        marker.showInfoWindow();
        Toast.makeText(this.getContext(),"Address: " + address + "\n" +
                "Address: " + city + "\n" +
                "Address: " + state + "\n" +
                "Address: " + country + "\n" +
                "Address: " + postalCode + "\n"
                ,Toast.LENGTH_SHORT ).show();

    }

    @Override
    public void onClick(View v) {
        try{
            int gpsSignal = Settings.Secure.getInt(getActivity().getContentResolver(),Settings.Secure.LOCATION_MODE);

            if(gpsSignal == 0){
                //No hay señal de gps o no se encuentra activo
                showGPSInfo();
            }else{
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if(location == null)
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }
            this.location = location;

            if (this.location != null) {
                createOrUpdateMarker(location);
                zoomToLocation(location);

            }
        }
        catch (Settings.SettingNotFoundException e){

        }
    }
    private void showGPSInfo(){
        new AlertDialog.Builder(getContext())
                .setTitle("GPS Signal")
                .setMessage("You don't have GPS signal enabled. Do you like to enable the GPS?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(i);
                    }
                })
                .setNegativeButton("NO" ,null)
                .show();
    }
    private void createOrUpdateMarker(Location location){
        if(mMarker == null){
            mMarker = map.addMarker(new MarkerOptions().position(new LatLng(location.getLongitude(),location.getLatitude())));

        }else{
            mMarker.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
        }
    }

    private void zoomToLocation(Location location){
        cameraZoom = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(),location.getLongitude()))
                .zoom(15)
                .bearing(0)
                .tilt(30)
                .build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraZoom));

    }
    @Override
    public void onLocationChanged(Location location) {
        createOrUpdateMarker(location);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

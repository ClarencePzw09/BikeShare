package dk.itu.mmad.bikeshare;

import android.Manifest;
import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import dk.itu.mmad.bikeshare.R;

public class StartRideActivity extends AppCompatActivity {
    private Button mAddRide;
    private TextView mLastAdded;
    private TextView mNewWhere;
    private TextView mBikeID;
    private RideVM rideVM;
    private BikeVM bikeVM;

// Location
private static ArrayList<String> mPermissions = new ArrayList<>();
    private static final int ALL_PERMISSIONS_RESULT = 1011;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationCallback mLocationCallback;
    private double mLongitude = -1;
    private double mLatitude = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);
//        final File fileDir = this.getFilesDir();
        mLastAdded = (TextView) findViewById(R.id.last_ride);

//        updateUI();
        getLocation();
        //Button
        mAddRide = (Button) findViewById(R.id.add_button);

        //Texts
//        mNewWhat = (TextView)findViewById(R.id.what_text);
        mNewWhere = (TextView)findViewById(R.id.where_text);
        mBikeID = (TextView)findViewById(R.id.start_bike);

        rideVM = ViewModelProviders.of(this).get(RideVM.class);
        bikeVM = ViewModelProviders.of(this).get(BikeVM.class);
        //view products click event
        mAddRide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if((mNewWhere.getText().length() > 0) && (mBikeID.getText().length() > 0)){

                    String origin = mNewWhere.getText().toString().trim();
                    Date startDate = Calendar.getInstance().getTime();
                    int bike_id = Integer.parseInt(mBikeID.getText().toString().trim());


                    Bike bike = bikeVM.getBike(bike_id);
                    if(bike != null && bike.isMAvailable()){

                        Ride mLast = new Ride(origin,startDate,"",null,bike_id,1,mLongitude,mLatitude);
                        bike.setMAvailable(false);
                        rideVM.insert(mLast);
                        bikeVM.update(bike);
                        //Reset Text Fields
                        mNewWhere.setText("");
                        updateUI(mLast);
                    }else{
                        mLastAdded.setText("No such Available Bike");
                    }




//                    addRideToDB(bikeName,origin,startDate);
                }
            }
        });
    }

    private void updateUI(Ride mLast){
        mLastAdded.setText(mLast.toString());
    }
    @Override
    protected void onResume() {
        super.onResume();
        startLocationUpdates();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void getLocation(){
        mPermissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        mPermissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        ArrayList<String> permissionsToRequest = permissionsToRequest(mPermissions);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (permissionsToRequest.size() > 0) {
                requestPermissions(permissionsToRequest.toArray(
                        new String[permissionsToRequest.size()]),
                        ALL_PERMISSIONS_RESULT);
            }
        }

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) return;
                for (Location location : locationResult.getLocations()) {
                    mLongitude = location.getLongitude();
                    mLatitude = location.getLatitude();
                }
            }
        };

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }
    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Objects.requireNonNull(this).checkSelfPermission(permission) ==
                    PackageManager.PERMISSION_GRANTED;
        }
        return true;
    }

    private boolean checkPermission() {
        return (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED);
    }
    private ArrayList<String> permissionsToRequest(ArrayList<String> permissions) {
        ArrayList<String> result = new ArrayList<>();
        for (String permission : permissions)
            if (!hasPermission(permission))
                result.add(permission);
        return result;
    }
    private void startLocationUpdates() {
        if (checkPermission()) {
            return;
        }
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(5000);
        mFusedLocationProviderClient.requestLocationUpdates(locationRequest, mLocationCallback, null);
    }

    private void stopLocationUpdates() {
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }

}

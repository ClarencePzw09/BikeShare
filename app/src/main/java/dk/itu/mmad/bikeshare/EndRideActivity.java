package dk.itu.mmad.bikeshare;

import android.Manifest;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import dk.itu.mmad.bikeshare.R;

public class EndRideActivity extends AppCompatActivity {
    // Location
    private static ArrayList<String> mPermissions = new ArrayList<>();
    private static final int ALL_PERMISSIONS_RESULT = 1011;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private LocationCallback mLocationCallback;
    private double mLongitude = -1;
    private double mLatitude = -1;

    private Button mEndRide;
    private TextView mLastEnded;
    private TextView mBikeId;
    private TextView mNewWhere;

    private String message = "";

    private RideVM rideVM;
    private BikeVM bikeVM;
    private UserVM userVM;
    private User user;
//    private Ride mLast = new Ride("","",null,"",null,null);
//    private Ride ride;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_ride);
        getLocation();
        getUser();
        mLastEnded = (TextView) findViewById(R.id.last_ride);
        //Button
        mEndRide = (Button) findViewById(R.id.end_button);

        //Texts
        mBikeId = (TextView)findViewById(R.id.bike_id);
        mNewWhere = (TextView)findViewById(R.id.where_text);

        rideVM = ViewModelProviders.of(this).get(RideVM.class);
        bikeVM = ViewModelProviders.of(this).get(BikeVM.class);

        //view products click event
        mEndRide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if((mBikeId.getText().length() > 0) && (mNewWhere.getText().length() > 0)){
                    int bikeId = 0;
                    try{
                        bikeId =Integer.parseInt(mBikeId.getText().toString().trim());
                    }catch(NumberFormatException e){
                        message += " Please Enter Numbers only";
                        mLastEnded.setText(message);
                    }

                    String destination = mNewWhere.getText().toString().trim();
                    Date endDate = Calendar.getInstance().getTime();
                    Bike bike = bikeVM.getBike(bikeId);
                    if(bike != null){
                        if(bike.isMAvailable()){
                            message += "Bike Ride Has Already Ended ";
                            mLastEnded.setText(message);
                        }else{
                            List<Ride> rideList = rideVM.getRidesByBikeId(bike.getId());
                            for(int i = 0 ; i < rideList.size();i++){
                                Ride ride = rideList.get(i);
                                //check if the ride has an endlocation
                                if(ride.getEndRide().equals("")){
                                    //use this
                                    bike.setMAvailable(true);
                                    ride.setEndRide(destination);
                                    ride.setEndRideTime(endDate);
                                    ride.setMEndLatitude(mLatitude);
                                    ride.setMEndLongitude(mLongitude);
                                    ride.setCost(calculateCost(bike,ride));
                                    if(user == null){
                                        System.out.println("user is null");
                                    }
                                    if(user!= null && user.deductBalance(calculateCost(bike,ride))){
                                        updateUI(ride);
                                        userVM.update(user);
                                    }else{
                                        message+="insufficient funds";
                                        mLastEnded.setText(message);
                                    }
                                    rideVM.update(ride);
                                    bikeVM.update(bike);

                                }
                            }

                        }
                    }else{
                        message +=" No such Bike exists ";
                        mLastEnded.setText(message);
                    }
                    //Reset Text Fields
                    mBikeId.setText("");
                    mNewWhere.setText("");

                }
            }
        });
    }
    private void updateUI(Ride mLast){
        mLastEnded.setText(mLast.toString());
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

//    private Bitmap toBitmap() {
//        if (mPhotoFile == null || !mPhotoFile.exists()) {
//            return null;
//        }
//
//        return PictureUtils.getScaledBitmap(mPhotoFile.getPath(), this);
//    }

//    private void endRideToDB(String bike,String destination,Date endDate){
//        RidesDB sRidesDB = RidesDB.get(getParent());
//        sRidesDB.endRide(bike,destination,endDate);
//    }
    private Double calculateCost(Bike bike,Ride ride){
        Double cost = 0.0;
        if(ride.getEndRideTime()!= null){
//            DecimalFormat df2 = new DecimalFormat("#.##");
            long diffInMillies = ride.getEndRideTime().getTime() - ride.getStartRideTime().getTime();
            double hours = diffInMillies/1000.0/60.0/60.0;

            cost = bike.getMPricePerHr() * hours;
//            df2.format(cost);
            BigDecimal bd = new BigDecimal(cost);
            bd = bd.setScale(2, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }

        return cost;
    }
    private void getUser() {
        userVM = ViewModelProviders.of(this).get(UserVM.class);
        user = userVM.getUser(5);
        //to check if a user has been created

//        return user;
    }
}

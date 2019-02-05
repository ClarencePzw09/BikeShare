package dk.itu.mmad.bikeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.itu.mmad.bikeshare.R;

public class BikeShareActivity extends AppCompatActivity {

    private Button mAddRide;
    private Button mEndRide;

    private Ride mLast = new Ride("","","");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_share);

        //Add Button
        mAddRide = (Button) findViewById(R.id.add_ride_button);

        mAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BikeShareActivity.this,StartRideActivity.class);
                startActivity(intent);

            }
        });

        mEndRide = (Button) findViewById(R.id.end_ride_button);

        mEndRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BikeShareActivity.this,EndRideActivity.class);
                startActivity(intent);

            }
        });
    }



}

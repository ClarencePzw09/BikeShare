package dk.itu.mmad.bikeshare;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.itu.mmad.bikeshare.R;

public class StartRideActivity extends AppCompatActivity {

    private Button mAddRide;
    private TextView mLastAdded;
    private TextView mNewWhat;
    private TextView mNewWhere;
    private static RidesDB sRidesDB;


    private Ride mLast = new Ride("","","");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_ride);

        mLastAdded = (TextView) findViewById(R.id.last_ride);

        updateUI();

        //Button
        mAddRide = (Button) findViewById(R.id.add_button);

        //Texts
        mNewWhat = (TextView)findViewById(R.id.what_text);
        mNewWhere = (TextView)findViewById(R.id.where_text);

        //view products click event
        mAddRide.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if((mNewWhat.getText().length() > 0) && (mNewWhere.getText().length() > 0)){

                    String bikeName = mNewWhat.getText().toString().trim();
                    String origin = mNewWhere.getText().toString().trim();

                    mLast.setmBikeName(bikeName);
                    mLast.setmStartRide(origin);

                    //Reset Text Fields
                    mNewWhat.setText("");
                    mNewWhere.setText("");

                    updateUI();
                    addRideToDB(bikeName,origin);
                }
            }
        });


    }

    private void updateUI(){

        mLastAdded.setText(mLast.toString());
    }

    private void addRideToDB(String bike,String origin){
        RidesDB sRidesDB = RidesDB.get(getParent());
        sRidesDB.addRide(bike,origin);
    }

}

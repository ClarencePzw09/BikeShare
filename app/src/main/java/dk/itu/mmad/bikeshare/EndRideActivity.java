package dk.itu.mmad.bikeshare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dk.itu.mmad.bikeshare.R;

public class EndRideActivity extends AppCompatActivity {

    private Button mAddRide;
    private TextView mLastEnded;
    private TextView mNewWhat;
    private TextView mNewWhere;

    private Ride mLast = new Ride("","","");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_ride);

        mLastEnded = (TextView) findViewById(R.id.last_ride);

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
                    String destination = mNewWhere.getText().toString().trim();


                    mLast.setmBikeName(bikeName);
                    mLast.setmStartRide(destination);

                    //Reset Text Fields
                    mNewWhat.setText("");
                    mNewWhere.setText("");

                    updateUI();
                    endRideToDB(bikeName,destination);
                }
            }
        });


    }

    private void updateUI(){

        mLastEnded.setText(mLast.toString());
    }

    private void endRideToDB(String bike,String destination){
        RidesDB sRidesDB = RidesDB.get(getParent());
        sRidesDB.endRide(bike,destination);
    }
}

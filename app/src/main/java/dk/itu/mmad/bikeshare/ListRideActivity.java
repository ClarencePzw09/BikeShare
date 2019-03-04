package dk.itu.mmad.bikeshare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ListRideActivity extends AppCompatActivity{

    private static RidesDB sRidesDB;
    private ListView mListView;
    private RideArrayAdapter mAdapter;

    private Ride mLast = new Ride("","","");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ride);


        sRidesDB = RidesDB.get(this);
        List<Ride> values = sRidesDB.getsRidesDB();

        //Create the adapter

        mAdapter = new RideArrayAdapter(this,values);
        mListView = (ListView) findViewById(R.id.act_list_view);
        mListView.setAdapter(mAdapter);
    }
}



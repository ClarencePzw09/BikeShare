package dk.itu.mmad.bikeshare;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListRideActivity extends AppCompatActivity{

    private RecyclerView mRecyclerView;
//    private static RidesDB sRidesDB;
//    private ListView mListView;
//    private RideArrayAdapter mAdapter;
    private RideAdapter mRideAdapter;
    private RideVM mRideVM;

//    private Ride mLast = new Ride("","",null,"",null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ride);


//        sRidesDB = RidesDB.get(this);
//        List<Ride> values = sRidesDB.getsRidesDB();

        //Create the adapter

//        mAdapter = new RideArrayAdapter(this,values);

//        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);


        List<Ride> emptyList = new ArrayList<Ride>();
        mRideAdapter = new RideAdapter(this, emptyList);
        mRideVM = ViewModelProviders.of(this).get(RideVM.class);
        mRideVM.getRides().observe(this, new Observer<List<Ride>>() {
            @Override
            public void onChanged(@Nullable List<Ride> rides) {
                mRideAdapter.setRides(rides);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRideAdapter);

//        mListView = (ListView) findViewById(R.id.act_list_view);
//        mListView.setAdapter(mAdapter);

    }


    private void updateUI(){

    }
}



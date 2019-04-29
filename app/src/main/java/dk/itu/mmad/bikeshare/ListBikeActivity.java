package dk.itu.mmad.bikeshare;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListBikeActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private BikeAdapter mBikeAdapter;
    private BikeVM mBikeVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bike);

        List<Bike> emptyList = new ArrayList<Bike>();
        mBikeAdapter = new BikeAdapter(this, emptyList);
        mBikeVM = ViewModelProviders.of(this).get(BikeVM.class);
        mBikeVM.getBikes().observe(this, new Observer<List<Bike>>() {
            @Override
            public void onChanged(@Nullable List<Bike> bikes) {
                mBikeAdapter.setBikes(bikes);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.list_bike_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mBikeAdapter);


    }
}




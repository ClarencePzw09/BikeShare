package dk.itu.mmad.bikeshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class BikeShareFragment extends Fragment {
    private Button mAddRide;
    private Button mEndRide;
    private Button mListRide;
    private static RidesDB sRidesDB;
    private ListView mListView;
    private RideArrayAdapter mAdapter;


    private Ride mLast = new Ride("","","");

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_bike_share, container, false);

        mAddRide = (Button) v.findViewById(R.id.add_ride_button);
        mAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),StartRideActivity.class);
                startActivity(intent);

//                startActivityForResult(intent,0);

            }
        });

        mEndRide = (Button) v.findViewById(R.id.end_ride_button);

        mEndRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EndRideActivity.class);
                startActivity(intent);

            }
        });

        mListRide = (Button) v.findViewById(R.id.list_ride_button);

        mListRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ListRideActivity.class);
                startActivity(intent);

            }
        });

//        //Singleton to share an object between the app activities
//        sRidesDB = RidesDB.get(getActivity());
//        List<Ride> values = sRidesDB.getsRidesDB();
//
//        //Create the adapter
//
//        mAdapter = new RideArrayAdapter(getActivity(),values);
//        mListView = (ListView) v.findViewById(R.id.main_list_view);
//        mListView.setAdapter(mAdapter);
        return v;
    }



}

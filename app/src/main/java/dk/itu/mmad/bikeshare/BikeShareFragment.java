package dk.itu.mmad.bikeshare;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class BikeShareFragment extends Fragment {
    private Button mAddRide;
    private Button mEndRide;
    private Button mListRide;
    private Button mRegisterBike;
    private Button mListBike;
    private TextView mUserBalanceView;

    private UserVM userVM;
//    private static RidesDB sRidesDB;
//    private ListView mListView;
//    private RideArrayAdapter mAdapter;


//    private Ride mLast = new Ride("","","");

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_bike_share, container, false);

        mUserBalanceView = (TextView)v.findViewById(R.id.user_balance_text);

//        updateUI();
        mAddRide = (Button) v.findViewById(R.id.add_ride_button);
        mAddRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),StartRideActivity.class);
                startActivity(intent);

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
        mRegisterBike= (Button) v.findViewById(R.id.register_bike_button);

        mRegisterBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),RegisterBikeActivity.class);
                startActivity(intent);

            }
        });
        mListBike = (Button) v.findViewById(R.id.list_bike_button);

        mListBike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),ListBikeActivity.class);
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

//    private User createUser(){
//
//        userVM = ViewModelProviders.of(getActivity()).get(UserVM.class);
//
//        User user = userVM.getUser(0);
//        List<User> userList = userVM.getUsers();
//        if(userList !=null){
//            System.out.println(userList.isEmpty());
//        }
//        //to check if a user has been created
//        if(user == null){
//            User newUser = new User("Handsome",5000.0);
//            System.out.println(newUser.getBalance());
//            userVM.insert(newUser);
//            return newUser;
//        }
//        System.out.println(userVM.getUser(0));
////        System.out.println(.getId());
//        return user;
//
//    }
//    private void updateUI(){
//        User user = createUser();
//        mUserBalanceView.setText(String.valueOf(user.getBalance()));
//    }

}

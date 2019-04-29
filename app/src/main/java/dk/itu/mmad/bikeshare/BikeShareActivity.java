package dk.itu.mmad.bikeshare;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import dk.itu.mmad.bikeshare.R;

public class BikeShareActivity extends AppCompatActivity {
    private UserVM userVM;
    private TextView userBalanceView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bike_share);

        FragmentManager fm = getSupportFragmentManager();

        userVM = ViewModelProviders.of(this).get(UserVM.class);
        updateUI();


        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = new BikeShareFragment();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        User user = userVM.getUser(5);
        if(user == null){
            userVM.insert(new User(5,"handsome",5300.0));
            user=userVM.getUser(5);
            System.out.println(user.getBalance());
        }

        userBalanceView = (TextView)findViewById(R.id.user_balance_text);
        userBalanceView.setText("Your Balance: "+String.valueOf(user.getBalance()));
    }



//    private Button mAddRide;
//    private Button mEndRide;
//    private static RidesDB sRidesDB;
//    private ListView mListView;
//    private RideArrayAdapter mAdapter;
//
//
//    private Ride mLast = new Ride("","","");
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bike_share);
//
//        //Add Button
//        mAddRide = (Button) findViewById(R.id.add_ride_button);
//
//        mAddRide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(BikeShareActivity.this,StartRideActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        mEndRide = (Button) findViewById(R.id.end_ride_button);
//
//        mEndRide.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(BikeShareActivity.this,EndRideActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        //Singleton to share an object between the app activities
//        sRidesDB = RidesDB.get(this);
//        List<Ride> values = sRidesDB.getsRidesDB();
//
//        //Create the adapter
//
//        mAdapter = new RideArrayAdapter(this,values);
//        mListView = (ListView) findViewById(R.id.main_list_view);
//        mListView.setAdapter(mAdapter);
//    }



}

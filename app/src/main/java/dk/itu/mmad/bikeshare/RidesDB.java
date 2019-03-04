package dk.itu.mmad.bikeshare;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class RidesDB {

    private static RidesDB sRidesDB;
    private ArrayList<Ride> mAllRides;
    private Ride mLastRide;

    private RidesDB(Context context) {
        mLastRide = new Ride("", "", "");

        //Add some rides for testing purposes
        mAllRides = new ArrayList<>();
        mAllRides.add(new Ride("Chuck Norris bike", "ITU", "Fields"));
        mAllRides.add(new Ride("Chuck Norris bike", "Fields", "Kongen Nytorv"));
        mAllRides.add(new Ride("Jackie Chan", "Kobenhavns Lufthaven", "Signalhuset "));
//        mAllRides.add(new Ride("Chuck Norris bike", "ITU", "Fields"));
//        mAllRides.add(new Ride("Chuck Norris bike", "Fields", "Kongen Nytorv"));
//        mAllRides.add(new Ride("Jackie Chan", "Kobenhavns Lufthaven", "Signalhuset "));
//        mAllRides.add(new Ride("Chuck Norris bike", "ITU", "Fields"));
//        mAllRides.add(new Ride("Chuck Norris bike", "Fields", "Kongen Nytorv"));
//        mAllRides.add(new Ride("Jackie Chan", "Kobenhavns Lufthaven", "Signalhuset "));
//        mAllRides.add(new Ride("Chuck Norris bike", "ITU", "Fields"));



    }

    public static RidesDB get(Context context) {
        if (sRidesDB == null) {
            sRidesDB = new RidesDB(context);

        }
        return sRidesDB;
    }

    public List<Ride> getsRidesDB() {
        return mAllRides;

    }

    public void addRide(String bike,String origin){
        Ride ride = new Ride(bike,origin,"");
        mAllRides.add(ride);
        mLastRide = ride;
    }

    public void endRide(String bike,String destination){
        if(mLastRide.getmBikeName().equals(bike) && mLastRide.getmEndRide().equals("")){
            mLastRide.setmEndRide(destination);
            mLastRide = new Ride("", "", "");
        } else {
            boolean update = false;
            for(int i = mAllRides.size() -1;i >=0 && !update; i--){
                Ride ride = mAllRides.get(i);
                if(ride.getmBikeName().equals(bike) && ride.getmEndRide().equals("")){
                    ride.setmEndRide(destination);
                    update = true;
                }
            }
        }
    }
}

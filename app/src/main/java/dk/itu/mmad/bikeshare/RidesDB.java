//package dk.itu.mmad.bikeshare;
//
//import android.content.Context;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//public class RidesDB {
//
//    private static RidesDB sRidesDB;
//    private ArrayList<Ride> mAllRides;
//    private Ride mLastRide;
//
//    private RidesDB(Context context) {
//        mLastRide = new Ride("", "",null, "",null);
//
//        //Add some rides for testing purposes
//        mAllRides = new ArrayList<>();
//        mAllRides.add(new Ride("Chuck Norris bike", "ITU", setDate(2019, 0, 3, 11, 00, 00),
//                "Fields",setDate(2019, 0, 1, 11, 20, 00)));
//        mAllRides.add(new Ride("Chuck Norris bike", "Fields", setDate(2019, 0, 3, 11, 30, 00),
//                "Fields",setDate(2019, 0, 1, 11, 20, 00)));
//        mAllRides.add(new Ride("Jackie Chan", "ITU", setDate(2019, 0, 1, 11, 00, 00),
//                "Fields",setDate(2019, 0, 1, 11, 20, 00)));
//
//
//
//
//    }
//
//    private Date setDate(int year, int month, int date, int hour, int minute, int second) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, date, hour, minute, second);
//        return calendar.getTime();
//    }
//    public static RidesDB get(Context context) {
//        if (sRidesDB == null) {
//            sRidesDB = new RidesDB(context);
//
//        }
//        return sRidesDB;
//    }
//
//    public List<Ride> getsRidesDB() {
//        return mAllRides;
//
//    }
//
//    public void addRide(String bike,String origin, Date startDate){
//        Ride ride = new Ride(bike,origin,startDate,"",null);
//        mAllRides.add(ride);
//        mLastRide = ride;
//    }
//
//    public void endRide(String bike,String destination, Date endDate){
//        if(mLastRide.getBikeName().equals(bike) && mLastRide.getEndRide().equals("")){
//            mLastRide.setEndRide(destination);
//            mLastRide.setEndRideTime(endDate);
//            mLastRide = new Ride("", "",null, "",null);
//        } else {
//            boolean update = false;
//            for(int i = mAllRides.size() -1;i >=0 && !update; i--){
//                Ride ride = mAllRides.get(i);
//                if(ride.getBikeName().equals(bike) && ride.getEndRide().equals("")){
//                    ride.setEndRide(destination);
//                    update = true;
//                }
//            }
//        }
//    }
//}

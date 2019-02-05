package dk.itu.mmad.bikeshare;

import android.support.annotation.NonNull;

public class Ride {

    private String mBikeName;
    private String mStartRide;
    private String  mEndRide;

    public Ride(String bikeName,
                String startRide,
                String endRide){
        mBikeName = bikeName;
        mStartRide = startRide;
        mEndRide = endRide;
    }

    public String getmBikeName() {
        return mBikeName;
    }

    public String getmStartRide() {
        return mStartRide;
    }

    public String getmEndRide() {
        return mEndRide;
    }

    public void setmEndRide(String mEndRide) {
        this.mEndRide = mEndRide;
    }

    public void setmBikeName(String mBikeName) {
        this.mBikeName = mBikeName;
    }

    public void setmStartRide(String mStartRide) {
        this.mStartRide = mStartRide;
    }

    @NonNull
    @Override
    public String toString() {

        if((mBikeName.equals("")) && (mStartRide.equals("")) && (mEndRide.equals(""))){
            return "";
        }
        return (mBikeName + " started here: " + mStartRide + " and ends here " + mEndRide);
//        if((mBikeName.equals("")) && (mStartRide.equals(""))){
//            return "";
//        }
//        return (mBikeName + " started here: " + mStartRide);
    }


    //    @Override
//    public String toString() {
//
//    }
}

package dk.itu.mmad.bikeshare;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity(tableName = "ride")
public class Ride {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name="start_ride")
    private String mStartRide;

    @ColumnInfo(name= "end_ride")
    private String  mEndRide;

    @ColumnInfo(name ="start_time")
    @TypeConverters({Converters.class})
    private Date mStartRideTime;

    @ColumnInfo(name = "end_time")
    @TypeConverters({Converters.class})
    private Date mEndRideTime;

    @ColumnInfo(name = "bike_id")
    private int bike_id;

    @ColumnInfo(name = "user_id")
    private int user_id;

    @ColumnInfo(name= "longitude")
    private Double  mLongitude;

    @ColumnInfo(name= "latitude")
    private Double mLatitude;

    @ColumnInfo(name= "endlongitude")
    private Double  mEndLongitude;

    @ColumnInfo(name= "endlatitude")
    private Double mEndLatitude;

    @ColumnInfo(name="cost")
    private Double cost;

    public Ride(){}
    public Ride(String startRide, Date startDate, String endRide, Date endDate,int bike_id,int user_id,Double mLongitude,Double mLatitude) {
        mStartRide = startRide;
        mStartRideTime = startDate;
        mEndRide = endRide;
        mEndRideTime = endDate;
        this.bike_id = bike_id;
        this.user_id = user_id;
        this.mLongitude = mLongitude;
        this.mLatitude = mLatitude;
        this.cost = 0.0;
    }

    public int getBike_id(){return bike_id;}
    public int getUser_id(){return user_id;}
    public int getId() {
        return id;
    }

    public Double getMLatitude() {
        return mLatitude;
    }

    public Double getMLongitude() {
        return mLongitude;
    }
    public Double getMEndLongitude(){
        return mEndLongitude;
    }
    public Double getMEndLatitude(){
        return mEndLatitude;
    }
    public Double getCost(){
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setBike_id(int bike_id){this.bike_id=bike_id;}
    public void setUser_id(int user_id){this.user_id=user_id;}

    public void setMLatitude(Double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public void setMLongitude(Double mLongitude) {
        this.mLongitude = mLongitude;
    }

    public void setMEndLongitude(Double mEndLongitude){
        this.mEndLongitude=mEndLongitude;
    }
    public void setMEndLatitude(Double mEndLatitude){
        this.mEndLatitude=mEndLatitude;
    }
    public void setCost(Double cost){
        this.cost=cost;
    }
    public String getStartRide() {
        return mStartRide;
    }

    public void setStartRide(String mStartRide) {
        this.mStartRide = mStartRide;
    }

    public String getEndRide() {
        return mEndRide;
    }

    public void setEndRide(String mEndRide) {
        this.mEndRide = mEndRide;
    }

    public Date getStartRideTime() {
        return mStartRideTime;
    }

    public void setStartRideTime(Date mStartRideTime) {
        this.mStartRideTime = mStartRideTime;
    }

    public Date getEndRideTime() {
        return mEndRideTime;
    }

    public void setEndRideTime(Date mEndRideTime) {
        this.mEndRideTime = mEndRideTime;
    }

//    @Override
    public String toString() {
        String rideString = bike_id + " started here: " + mStartRide;
        if((bike_id == 0) && (mStartRide.equals("")) && (mEndRide.equals(""))){
            return "";
        }
        if(mEndRide.equals("") && mEndRideTime ==null){
            return rideString + " at " + mStartRideTime.toString();
        }else if(mStartRideTime!=null){
            return rideString + " and ended at " + mEndRide + "  at " + mEndRideTime.toString();
        }
        return "";
//        return (mBikeName + " started here: " + mStartRide);
    }

    public static class Converters{
        @TypeConverter
        public Date fromTimestamp(Long value){
            return value == null ? null:new Date(value);
        }

        @TypeConverter
        public Long dateToTimestamp(Date date){
            if(date == null){
                return null;
            }
            else{
                return date.getTime();
            }
        }
    }

//    public String getPhotoFilename(){
//        return "IMG_" + String.valueOf(getId()) + ".jpg";
//    }

}

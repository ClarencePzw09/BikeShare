package dk.itu.mmad.bikeshare;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import java.io.ByteArrayOutputStream;

@Entity(tableName = "bike")
public class Bike {
//    The (minimal) information related to a bike are:
//            (i) ID (identication of the lock); (ii)
//    type of bike; (iii) picture of the bike; and
//            (iv) price per hour. You may extend this.

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "bike_name")
    private String mBikeName;

    @ColumnInfo(name="type")
    private String mType;

    @ColumnInfo(name = "bike_picture")
    private byte[] mPicture;

    @ColumnInfo(name= "price")
    private double mPricePerHr;

    @ColumnInfo(name = "available")
    private boolean mAvailable;
    public Bike(){

    }
    public Bike(int id,String bikeName, String type, double price, Bitmap bitmap){
        this.id=id;
        mBikeName = bikeName;
        mType = type;
        mPricePerHr = price;
        mAvailable = true;

        mPicture = null;
        if (bitmap != null) {
            // Adapted from https://stackoverflow.com/a/7620610
            ByteArrayOutputStream blob = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, blob);
            mPicture = blob.toByteArray();
        }
    }

    public boolean isMAvailable() {
        return mAvailable;
    }

    public void setMAvailable(boolean mAvailable) {
        this.mAvailable = mAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMBikeName() {
        return mBikeName;
    }

    public void setMBikeName(@NonNull String mBikeName) {
        this.mBikeName = mBikeName;
    }

    public String getMType() {
        return mType;
    }

    public void setMType(String mType) {
        this.mType = mType;
    }

    public byte[] getMPicture() {
        return mPicture;
    }

    public void setMPicture(byte[] mPicture) {
        this.mPicture = mPicture;
    }

    public double getMPricePerHr() {
        return mPricePerHr;
    }

    public void setMPricePerHr(double mPricePerHr) {
        this.mPricePerHr = mPricePerHr;
    }

    public Bitmap getPicture() {
        if (mPicture == null || mPicture.length == 0) {
            return null;
        }

        return BitmapFactory.decodeByteArray(mPicture, 0, mPicture.length);
    }
}

package dk.itu.mmad.bikeshare;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities={Ride.class},version = 1,exportSchema = false)
public abstract class RideDB extends RoomDatabase {
    public abstract RideDao rideDao();
    //Volatile varaible will only have one main copy even when multiple threads update the object
    private static volatile RideDB sRideDB;

    public static RideDB get(final Context context){
        if(sRideDB == null) {


            synchronized (RideDB.class) {
                if (sRideDB == null) {
                    sRideDB = Room.databaseBuilder(context.getApplicationContext(), RideDB.class, "ride_db").build();
                }

            }
        }
        return sRideDB;
    }
}

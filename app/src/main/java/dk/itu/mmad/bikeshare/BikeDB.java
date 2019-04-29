package dk.itu.mmad.bikeshare;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities={Bike.class},version = 1,exportSchema = false)
public abstract class BikeDB extends RoomDatabase{
    public abstract BikeDao bikeDao();

    private static volatile BikeDB sBikeDB;

        public static BikeDB get(final Context context){
        if(sBikeDB == null) {


            synchronized (BikeDB.class) {
                if (sBikeDB == null) {
                    sBikeDB = Room.databaseBuilder(context.getApplicationContext(), BikeDB.class, "bike_db").build();
                }

            }
        }
        return sBikeDB;
    }


}



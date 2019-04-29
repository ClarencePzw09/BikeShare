package dk.itu.mmad.bikeshare;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities={User.class},version = 1,exportSchema = false)
public abstract class UserDB extends RoomDatabase{
    public abstract UserDao userDao();
    private static volatile UserDB sUserDB;

    public static UserDB get(final Context context){
        if(sUserDB == null) {
            synchronized (UserDB.class) {
                if (sUserDB == null) {
                    sUserDB = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "user_db").build();
                }

            }
        }
        return sUserDB;
    }
}



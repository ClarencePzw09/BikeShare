package dk.itu.mmad.bikeshare;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface BikeDao {
    @Insert
    void insert(Bike bike);

    @Update
    void update(Bike bike);

    @Delete
    void delete(Bike bike);

    @Query("SELECT * FROM bike ORDER BY id")
    LiveData<List<Bike>> getAllBikes();
//
//    @Query("SELECT * FROM ride WHERE bike_name = :bikeName AND end_time IS NULL ORDER BY start_time DESC LIMIT 1")
//    Ride getLastRide(String bikeName);
//
    @Query("SELECT * FROM bike WHERE id=:id")
    Bike getBike(int id);
//
//    @Query("DELETE FROM ride")
//    void deleteAllRides();

    @Query("SELECT * FROM bike WHERE available=:available")
    List<Bike> getAvailableBikes(boolean available);
}


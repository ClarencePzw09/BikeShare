package dk.itu.mmad.bikeshare;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RideDao {
    @Insert
    void insert(Ride ride);

    @Update
    void update(Ride ride);

    @Delete
    void delete(Ride ride);

    @Query("SELECT * FROM ride ORDER BY id")
    LiveData<List<Ride>> getAllRides();

    @Query("SELECT * FROM ride WHERE bike_id = :bikeId AND end_time IS NULL ORDER BY start_time DESC LIMIT 1")
    Ride getLastRide(int bikeId);

    @Query("SELECT * FROM ride WHERE id=:id")
    Ride getRide(int id);

    @Query("DELETE FROM ride")
    void deleteAllRides();

    @Query("SELECT * FROM ride WHERE bike_id =:bikeId")
    List<Ride> getRidesByBikeId(int bikeId);

}

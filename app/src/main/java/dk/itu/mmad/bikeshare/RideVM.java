package dk.itu.mmad.bikeshare;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class RideVM extends AndroidViewModel {
    private RideRepository mRideRepository;
    private LiveData<List<Ride>> rides;

    public RideVM(Application application){
        super(application);
        mRideRepository = new RideRepository(application);
        rides = mRideRepository.getAllRides();
    }

    public void insert(Ride ride){
        mRideRepository.insert(ride);
    }
    public LiveData<List<Ride>> getRides(){
        return rides;
    }

    public void update(Ride ride){
        mRideRepository.update(ride);
    }
    public void delete(Ride ride){
        mRideRepository.delete(ride);
    }
    public void deleteAllRides(){
        mRideRepository.deleteAllRides();
    }
    public Ride getLastRide(int bikeId){
        return mRideRepository.getLatestRide(bikeId);
    }
    public Ride getRide(int id){
        return mRideRepository.getRide(id);
    }
    public List<Ride> getRidesByBikeId(int bikeId){return mRideRepository.getRidesByBikeId(bikeId);}
//
//    public Ride getRide(String bike_name){
//
//    }

}

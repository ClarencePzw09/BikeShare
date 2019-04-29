package dk.itu.mmad.bikeshare;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class BikeVM extends AndroidViewModel {
    private BikeRepository mBikeRepository;
    private LiveData<List<Bike>> bikes;

    public BikeVM(Application application){
        super(application);
        mBikeRepository = new BikeRepository(application);
        bikes = mBikeRepository.getAllBikes();
    }

    public void insert(Bike bike){
        mBikeRepository.insert(bike);
    }
    public LiveData<List<Bike>> getBikes(){
        return bikes;
    }

    public void update(Bike bike){
        mBikeRepository.update(bike);
    }
    public void delete(Bike bike){
        mBikeRepository.delete(bike);
    }

    public Bike getBike(int id){return mBikeRepository.getBike(id);}

    public List<Bike> getAvailableBikes(){return mBikeRepository.getAvailableBikes();}

//    public void deleteAllRides(){
//        mBikeRepository.deleteAllRides();
//    }
//    public Ride getLastRide(String bikeName){
//        return mRideRepository.getLatestRide(bikeName);
//    }
//    public Ride getRide(int id){
//        return mRideRepository.getRide(id);
//    }

}



//
//    public Ride getRide(String bike_name){
//
//    }


package dk.itu.mmad.bikeshare;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class RideRepository {

    private RideDao mRideDao;
    private LiveData<List<Ride>> mRides;

    public RideRepository(Application application){
        RideDB rideDB = RideDB.get(application);
        mRideDao = rideDB.rideDao();
        mRides = mRideDao.getAllRides();
    }

    public void insert(Ride ride){
        new InsertAsyncTask(mRideDao).execute(ride);
    }

    public void delete(Ride ride){
        new DeleteAsyncTask(mRideDao).execute(ride);
    }

    public void update(Ride ride){ new UpdateAsyncTask(mRideDao).execute(ride);}

    public void deleteAllRides() {
        new DeleteAllAsyncTask(mRideDao).execute();
    }

    public Ride getLatestRide(int bikeId){
        try{
            return new SearchRideAsyncTask(mRideDao).execute(bikeId).get();

        }catch(Exception e){
            return null;
        }
    }
    public Ride getRide(int id){
        try{
            return new GetRideAsyncTask(mRideDao).execute(id).get();
        }catch(Exception e){
            return null;
        }
    }
    public LiveData<List<Ride>> getAllRides(){
        return mRides;
    }
    public List<Ride> getRidesByBikeId(int bikeId){
        try{
            return new GetRidesByBikeIdAsyncTask(mRideDao).execute(bikeId).get();

        }catch(Exception e){
            return null;
        }


    }
    private static class GetRidesByBikeIdAsyncTask extends AsyncTask<Integer,Void,List<Ride>>{
        private RideDao rideDao;

        public GetRidesByBikeIdAsyncTask(RideDao dao){
            rideDao = dao;
        }

        @Override
        protected List<Ride> doInBackground(final Integer... bikeIds){
            return rideDao.getRidesByBikeId(bikeIds[0]);
        }
    }
    private static class InsertAsyncTask extends AsyncTask<Ride,Void,Void> {
        private RideDao mRideDao;

        public InsertAsyncTask(RideDao dao) {
            mRideDao = dao;
        }

        @Override
        protected Void doInBackground(final Ride... rides) {
            mRideDao.insert(rides[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Ride,Void,Void>{
        private RideDao rideDao;

        public DeleteAsyncTask(RideDao dao){
            rideDao=dao;
        }
        @Override
        protected Void doInBackground(final Ride... rides){
            rideDao.delete(rides[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Ride,Void,Void>{
        private RideDao rideDao;

        public UpdateAsyncTask(RideDao dao){
            rideDao=dao;
        }
        @Override
        protected Void doInBackground(final Ride... rides){
            rideDao.update(rides[0]);
            return null;
        }
    }
    private static class DeleteAllAsyncTask extends AsyncTask<Ride, Void, Void> {

        private RideDao rideDao;

        public DeleteAllAsyncTask(RideDao dao) {
            rideDao = dao;
        }
        @Override
        protected Void doInBackground(final Ride... rides) {
            rideDao.deleteAllRides();
            return null;
        }
    }

    private static class SearchRideAsyncTask extends AsyncTask<Integer,Void,Ride>{
        private RideDao rideDao;

        public SearchRideAsyncTask(RideDao dao){
            rideDao = dao;
        }

        @Override
        protected Ride doInBackground(final Integer... bikeIds){
            return rideDao.getLastRide(bikeIds[0]);
        }
    }
    private static class GetRideAsyncTask extends AsyncTask<Integer,Void,Ride>{
        private RideDao rideDao;

        public GetRideAsyncTask(RideDao dao){
            rideDao = dao;
        }

        @Override
        protected Ride doInBackground(final Integer... bikeIds){
            return rideDao.getRide(bikeIds[0]);
        }
    }

}



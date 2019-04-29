package dk.itu.mmad.bikeshare;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class BikeRepository {
    private BikeDao mBikeDao;
    private LiveData<List<Bike>> mBikes;

    public BikeRepository(Application application) {
        BikeDB bikeDB = BikeDB.get(application);
        mBikeDao = bikeDB.bikeDao();
        mBikes = mBikeDao.getAllBikes();
    }

    public void insert(Bike bike) {
        new BikeRepository.InsertAsyncTask(mBikeDao).execute(bike);
    }

    public void delete(Bike bike) {
        new BikeRepository.DeleteAsyncTask(mBikeDao).execute(bike);
    }

    public void update(Bike bike) {
        new BikeRepository.UpdateAsyncTask(mBikeDao).execute(bike);
    }

    public LiveData<List<Bike>> getAllBikes() {
        return mBikes;
    }

    public Bike getBike(int bikeId) {
        try {
            return new BikeRepository.FindAsyncTask(mBikeDao).execute(bikeId).get();
        } catch (Exception e) {
            return null;
        }
    }
    public List<Bike> getAvailableBikes() {
        try {
            return mBikeDao.getAvailableBikes(true);
        } catch (Exception e) {
            return null;
        }
    }
    private static class FindAsyncTask extends AsyncTask<Integer, Void, Bike> {
        private BikeDao mBikeDao;

        public FindAsyncTask(BikeDao dao) {
            mBikeDao = dao;
        }

        @Override
        protected Bike doInBackground(final Integer... bikeId) {
            return mBikeDao.getBike(bikeId[0]);
//            mBikeDao.insert(bikes[0]);
//            return null;
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Bike, Void, Void> {
        private BikeDao mBikeDao;

        public InsertAsyncTask(BikeDao dao) {
            mBikeDao = dao;
        }

        @Override
        protected Void doInBackground(final Bike... bikes) {
            mBikeDao.insert(bikes[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Bike, Void, Void> {
        private BikeDao mBikeDao;

        public DeleteAsyncTask(BikeDao dao) {
            mBikeDao = dao;
        }

        @Override
        protected Void doInBackground(final Bike... bikes) {
            mBikeDao.delete(bikes[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Bike, Void, Void> {
        private BikeDao mBikeDao;

        public UpdateAsyncTask(BikeDao dao) {
            mBikeDao = dao;
        }

        @Override
        protected Void doInBackground(final Bike... bikes) {
            mBikeDao.update(bikes[0]);
            return null;
        }
    }
}




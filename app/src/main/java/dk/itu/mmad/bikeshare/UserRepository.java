package dk.itu.mmad.bikeshare;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class UserRepository {

    private UserDao mUserDao;
    private LiveData<List<User>> mUsers;

    public UserRepository(Application application){
        UserDB userDB = UserDB.get(application);
        mUserDao = userDB.userDao();
        mUsers= mUserDao.getAllUsers();
    }
    public User getUser(int id){
        try{
            return new UserRepository.GetUserAsyncTask(mUserDao).execute(id).get();
//            return mUserDao.getUser(id);
        }catch(Exception e){
            return null;
        }

    }
    public List<User> getUsers(){
        try{
            return mUserDao.getUsers();
        }catch(Exception e){
            return null;
        }

    }
    public LiveData<List<User>> getAllUsers() {
        return mUsers;
    }

    public void insert(User user){
        new UserRepository.InsertAsyncTask(mUserDao).execute(user);
    }
    public void delete(User user){
        new UserRepository.DeleteAsyncTask(mUserDao).execute(user);
    }

    public void update(User user){ new UserRepository.UpdateAsyncTask(mUserDao).execute(user);}

    private static class InsertAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDao mUserDao;

        public InsertAsyncTask(UserDao dao) {
            mUserDao = dao;
        }

        @Override
        protected Void doInBackground(final User... users) {
            mUserDao.insert(users[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao mUserDao;

        public DeleteAsyncTask(UserDao dao) {
            mUserDao = dao;
        }
        @Override
        protected Void doInBackground(final User... users){
            mUserDao.delete(users[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao mUserDao;

        public UpdateAsyncTask(UserDao dao) {
            mUserDao = dao;
        }
        @Override
        protected Void doInBackground(final User... users){
            mUserDao.update(users[0]);
            return null;
        }
    }
    private static class GetUserAsyncTask extends AsyncTask<Integer,Void,User>{
        private UserDao mUserDao;

        public GetUserAsyncTask(UserDao dao){
             mUserDao = dao;
        }

        @Override
        protected User doInBackground(final Integer... userIds){
            return mUserDao.getUser(userIds[0]);
        }
    }
}

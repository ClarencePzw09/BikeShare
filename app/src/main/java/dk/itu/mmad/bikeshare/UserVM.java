package dk.itu.mmad.bikeshare;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class UserVM extends AndroidViewModel {
    private UserRepository mUserRepository;
    private LiveData<List<User>> users;

    public UserVM(Application application){
        super(application);
        mUserRepository = new UserRepository(application);
        users = mUserRepository.getAllUsers();
    }
    public void insert(User user) {
        mUserRepository.insert(user);
    }
    public void update(User user){
        mUserRepository.update(user);
    }
    public void delete(User user){
        mUserRepository.delete(user);
    }
    public User getUser(int id){
        return mUserRepository.getUser(id);
    }
    public List<User> getUsers(){return mUserRepository.getUsers();}
//    public List<User> getUsers(){return mUserRepository.getUsers();}
}


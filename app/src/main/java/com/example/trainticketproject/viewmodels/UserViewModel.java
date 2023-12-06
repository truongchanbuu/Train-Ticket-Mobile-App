package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.User;
import com.example.trainticketproject.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<User> getUserById(int uid) {
        return userRepository.getUserById(uid);
    }

    public void insertMultipleUser(List<User> users) {
        userRepository.insertMultipleUser(users);
    }
}

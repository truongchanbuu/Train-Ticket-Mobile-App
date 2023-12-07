package com.example.trainticketproject.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserWithVouchers;
import com.example.trainticketproject.repositories.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private final UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    public LiveData<User> getUserById(Integer uid) {
        return userRepository.getUserById(uid);
    }

    public void insertMultipleUser(List<User> users) {
        userRepository.insertMultipleUser(users);
    }

    public void updateRewardPoint(int newRewardPoint, Integer uid) {
        userRepository.updateRewardPoint(newRewardPoint, uid);
    }

    public LiveData<List<User>> getAllUsers() {
        return userRepository.getAllUsers();
    }
}

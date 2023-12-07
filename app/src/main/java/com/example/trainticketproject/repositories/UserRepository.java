package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.UserDAO;
import com.example.trainticketproject.databases.UserRoomDatabase;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserWithVouchers;

import java.util.List;

public class UserRepository {
    private final UserDAO userDAO;

    public UserRepository(Application application) {
        UserRoomDatabase userRoomDatabase = UserRoomDatabase.getDatabase(application);
        userDAO = userRoomDatabase.userDAO();
    }

    public void insert(User user) {
        userDAO.insert(user);
    }

    public LiveData<User> getUserById(Integer uid) {
        return userDAO.getUserById(uid);
    }

    public void insertMultipleUser(List<User> users) {
        userDAO.insertMultipleUser(users);
    }

    public void updateRewardPoint(int newRewardPoint, Integer uid) {
        userDAO.updateRewardPoint(newRewardPoint, uid);
    }

    public LiveData<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }
}

package com.example.trainticketproject.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trainticketproject.daos.UserDAO;
import com.example.trainticketproject.databases.UserRoomDatabase;
import com.example.trainticketproject.models.User;

import java.util.List;

public class UserRepository {
    private final UserDAO userDAO;

    public UserRepository(Application application) {
        UserRoomDatabase userRoomDatabase = UserRoomDatabase.getDatabase(application);
        userDAO = userRoomDatabase.userDAO();
    }

    public LiveData<User> getUserById(int uid) {
        return userDAO.getUserById(uid);
    }

    public void insertMultipleUser(List<User> users) {
        userDAO.insertMultipleUser(users);
    }
}

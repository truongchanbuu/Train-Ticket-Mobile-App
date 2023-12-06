package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.trainticketproject.models.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE uid = :uid")
    LiveData<User> getUserById(int uid);

    @Insert
    void insertMultipleUser(List<User> users);
}

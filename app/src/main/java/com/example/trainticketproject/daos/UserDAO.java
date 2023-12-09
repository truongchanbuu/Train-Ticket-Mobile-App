package com.example.trainticketproject.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.trainticketproject.models.User;
import com.example.trainticketproject.models.UserWithVouchers;
import com.example.trainticketproject.models.VoucherWithUsers;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE uid = :uid")
    LiveData<User> getUserById(Long uid);

    @Insert
    void insertMultipleUser(List<User> users);

    @Query("UPDATE User SET rewardPoint = :newRewardPoint WHERE uid = :uid")
    void updateRewardPoint(int newRewardPoint, Long uid);

    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();
}

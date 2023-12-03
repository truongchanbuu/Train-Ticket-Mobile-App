package com.example.trainticketproject.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserWithPayments {
    @Embedded
    public User user;

    @Relation(parentColumn = "uid", entityColumn = "uid")
    public List<Payment> payments;
}

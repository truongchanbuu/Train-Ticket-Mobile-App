package com.example.trainticketproject.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "user_voucher"
//        primaryKeys = {"uid", "voucherId"},
//        indices = {@Index(value = {"uid", "voucherId"}, unique = true)},
//        foreignKeys = {
//                @ForeignKey(entity = User.class,
//                        parentColumns = "uid",
//                        childColumns = "uid",
//                        onDelete = ForeignKey.CASCADE),
//                @ForeignKey(entity = Voucher.class,
//                        parentColumns = "voucherId",
//                        childColumns = "voucherId",
//                        onDelete = ForeignKey.CASCADE)
//        }
        )
public class UserVoucherCrossRef {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @NonNull
    public Integer uid;
    @NonNull
    public Integer voucherId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

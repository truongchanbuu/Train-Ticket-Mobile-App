package com.example.trainticketproject.uis.Train;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.Gender;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.uis.Ticket.MyTicketActivity;
import com.example.trainticketproject.uis.Voucher.VoucherActivity;
import com.example.trainticketproject.utils.NotificationPublisher;
import com.example.trainticketproject.viewmodels.TrainViewModel;
import com.example.trainticketproject.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class TrainActivity extends AppCompatActivity {
    Long uid;
    private TrainViewModel trainViewModel;

    private TrainAdapter adapter;
    RecyclerView recyclerViewTrain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        NotificationPublisher.createNotificationChannel(this);

        trainViewModel = new ViewModelProvider(this).get(TrainViewModel.class);

        // ADD SAMPLE TRAINS
//        new Thread(() -> {
//            List<Train> trains = TrainDetailInfoActivity.createSampleTrains();
//            trainViewModel.insertMultipleTrains(trains);
//        }).start();

//        new Thread(() -> {
//            List<User> users = createSampleUser();
//            userViewModel.insertMultipleUser(users);
//        }).start();

        uid = 3L;
        recyclerViewTrain = findViewById(R.id.recyclerViewTrain);

        trainViewModel.getAllTrains().observe(this, trains -> {
            adapter = new TrainAdapter(trains, trainViewModel, uid);

            recyclerViewTrain.setAdapter(adapter);
        });

        recyclerViewTrain.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_item_my_ticket) {
            Intent myTicketIntent = new Intent(this, MyTicketActivity.class);
            myTicketIntent.putExtra("uid", uid);

            startActivity(myTicketIntent);
        } else if (item.getItemId() == R.id.menu_item_all_voucher) {
            Intent allVouchersIntent = new Intent(this, VoucherActivity.class);
            allVouchersIntent.putExtra("uid", uid);
            startActivity(allVouchersIntent);
        }

        return false;
    }

    public static List<User> createSampleUser() {
        List<User> users = new ArrayList<>();
        users.add(new User("John Doe", 25, Gender.MALE, "john@example.com", "password123", "123456789"));
        users.add(new User("Jane Doe", 28, Gender.FEMALE, "jane@example.com", "password456", "987654321"));
        User dany = new User("Danny", 22, Gender.FEMALE, "dany@example.com", "password456", "987654321");
        dany.setRewardPoint(10000);
        users.add(dany);

        return users;
    }
}
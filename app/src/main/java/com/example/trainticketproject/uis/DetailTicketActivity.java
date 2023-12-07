package com.example.trainticketproject.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.Gender;
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.models.User;
import com.example.trainticketproject.utils.DateTimeConverter;
import com.example.trainticketproject.utils.QRCodeGenerator;
import com.example.trainticketproject.viewmodels.TicketViewModel;
import com.example.trainticketproject.viewmodels.TrainViewModel;
import com.example.trainticketproject.viewmodels.UserViewModel;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailTicketActivity extends AppCompatActivity {
    TicketViewModel ticketViewModel;
    UserViewModel userViewModel;
    TrainViewModel trainViewModel;

    TextView tvTicketId, tvName, tvTrip, tvDepartureDate, tvPrice, tvTime, tvSeatNumber;
    ImageView imgETicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ticket);

        tvTicketId = findViewById(R.id.tvTicketId);
        tvName = findViewById(R.id.tvName);
        tvTrip = findViewById(R.id.tvTrip);
        tvDepartureDate = findViewById(R.id.tvDepartureDate);
        tvPrice = findViewById(R.id.tvPrice);
        tvTime = findViewById(R.id.tvTime);
        tvSeatNumber = findViewById(R.id.tvSeatNumber);
        imgETicket = findViewById(R.id.imgEticket);

        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        trainViewModel = new ViewModelProvider(this).get(TrainViewModel.class);

        int ticketId = 1;
        ticketViewModel.getTicketById(ticketId).observe(this, selectedTicket -> {
            if (selectedTicket != null) {
                updateUI(selectedTicket);

                userViewModel.getUserById(selectedTicket.getUid()).observe(this, user -> {
                    if (user != null) {
                        tvName.setText(user.getName());
                    }
                });

                trainViewModel.getTrainById(selectedTicket.getTrainId()).observe(this, train -> {
                    if (train != null) {
                        tvTrip.setText(train.getTrip());

                        String formattedDate = DateTimeConverter.formatFullDate(train.getDepartureTime());
                        tvDepartureDate.setText(formattedDate);

                        String formattedDepartureTime = null;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            formattedDepartureTime = train.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm"));
                            tvTime.setText(formattedDepartureTime);
                        }

                        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                        String priceString = numberFormat.format(train.getPrice());
                        tvPrice.setText(priceString + " VND");
                    }
                });
            }
        });
    }

    private void updateUI(Ticket selectedTicket) {
        tvTicketId.setText(String.valueOf(selectedTicket.getTicketId()));
        tvSeatNumber.setText(String.valueOf(selectedTicket.getSeatNumber()));

        QRCodeGenerator.generateQRCodeAsync(this, selectedTicket, imgETicket);
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
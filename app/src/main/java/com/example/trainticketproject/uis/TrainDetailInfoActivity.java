package com.example.trainticketproject.uis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.trainticketproject.R;
import com.example.trainticketproject.models.Status;
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.utils.DateTimeConverter;
import com.example.trainticketproject.viewmodels.TrainViewModel;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TrainDetailInfoActivity extends AppCompatActivity {
    private TrainViewModel viewModel;

    TextView tvTrip, tvDepartureStation, tvArrivalStation, tvDepartureTime, tvArrivalTime, tvTotalTime, tvPrice, tvDepartureDate, tvSeat;
    ImageButton imgBtnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_detail_info);

        imgBtnShare = findViewById(R.id.imgBtnShare);
        tvTrip = findViewById(R.id.tvTrip);
        tvDepartureStation = findViewById(R.id.tvDepartureStation);
        tvArrivalStation = findViewById(R.id.tvArrivalStation);
        tvDepartureTime = findViewById(R.id.tvDepartureTime);
        tvArrivalTime = findViewById(R.id.tvArrivalTime);
        tvTotalTime = findViewById(R.id.tvTotalTime);
        tvPrice = findViewById(R.id.tvPrice);
        tvDepartureDate = findViewById(R.id.tvDepartureDate);
        tvSeat = findViewById(R.id.tvSeat);

        viewModel = new ViewModelProvider(this).get(TrainViewModel.class);

        // Get id from intent
        int id = 1;
        viewModel.getTrainById(id).observe(this, selectedTrain -> {
            if (selectedTrain != null) {
                updateUI(selectedTrain);
                viewModel.getAvailableSeatsCount(id).observe(this, availableSeats -> {
                    int totalSeats = selectedTrain.getTotalSeats();
                    availableSeats = totalSeats - availableSeats;
                    tvSeat.setText(String.valueOf(availableSeats) + "/" + String.valueOf(totalSeats));
                });

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                    createNotificationDelayed(selectedTrain.getDepartureTime(), Duration.ofMinutes(1));
//                }
            }
        });
    }

    private void updateUI(Train selectedTrain) {
        tvTrip.setText(selectedTrain.getTrip());
        tvDepartureStation.setText(selectedTrain.getDepartureStation());
        tvArrivalStation.setText(selectedTrain.getArrivalStation());

        String formattedDepartureTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formattedDepartureTime = selectedTrain.getDepartureTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            tvDepartureTime.setText(formattedDepartureTime);
        }

        String formattedArrivalTime = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            formattedArrivalTime = selectedTrain.getArrivalTime().format(DateTimeFormatter.ofPattern("HH:mm"));
            tvArrivalTime.setText(formattedArrivalTime);
        }

        String formattedDate = DateTimeConverter.formatFullDate(selectedTrain.getDepartureTime());
        tvDepartureDate.setText(formattedDate);

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        String priceString = numberFormat.format(selectedTrain.getPrice());
        tvPrice.setText(priceString + " VND");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Duration duration = Duration.between(selectedTrain.getDepartureTime(), selectedTrain.getArrivalTime());

            long totalMinutes = duration.toMinutes();
            long hours = totalMinutes / 60;
            long remainingMinutes = totalMinutes % 60;

            tvTotalTime.setText(hours + " hours and " + remainingMinutes + " mins");
        }

        imgBtnShare.setOnClickListener(v -> {
            Intent sendIntent = new Intent(Intent.ACTION_SEND);
            String shareText = "Thông tin chuyến tàu:\n" +
                    "Trip: " + tvTrip.getText().toString()+ "\n" +
                    "Departure Station: " + tvDepartureStation.getText().toString() + "\n" +
                    "Arrival Station: " + tvArrivalStation.getText().toString() + "\n" +
                    "Departure Date: " + tvDepartureDate.getText().toString() + "\n" +
                    "Departure Time: " + tvDepartureTime.getText().toString() + "\n" +
                    "Price: " + tvPrice.getText().toString();

            sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, "Train Detail Information");
            startActivity(shareIntent);
        });
    }

    private List<Train> createSampleTrains() {
        List<Train> trains = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            trains.add(new Train("HO CHI MINH - HA NOI", "HO CHI MINH GAS STATION", "HA NOI GAS STATION", LocalDateTime.now(), LocalDateTime.now().plusHours(2),200000, 50));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            trains.add(new Train("HA NOI - HO CHI MINH", "HA NOI GAS STATION",  "HO CHI MINH GAS STATION", LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5), 200000, 40));
        }

        return trains;
    }

    private List<Ticket> createSampleTickets() {
        List<Ticket> tickets = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tickets.add(new Ticket(1, 1, 1, LocalDateTime.now(), Status.AVAILABLE));
            tickets.add(new Ticket(1, 1, 2, LocalDateTime.now(), Status.AVAILABLE));
            tickets.add(new Ticket(2, 1, 3, LocalDateTime.now(), Status.AVAILABLE));

            tickets.add(new Ticket(3, 2, 4, LocalDateTime.now(), Status.AVAILABLE));
            tickets.add(new Ticket(3, 2, 5, LocalDateTime.now(), Status.AVAILABLE));
            tickets.add(new Ticket(3, 2, 6, LocalDateTime.now(), Status.AVAILABLE));
        }

        return tickets;
    }

//    private void createNotificationDelayed(LocalDateTime departureDate, Duration delay) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            LocalDateTime notificationTime = departureDate.minus(delay);
//
//            long notificationTimeMillis = notificationTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//
//            ScheduleNotification.scheduleNotification(getApplicationContext(), "Lịch trình của bạn", "Sắp tới ngày đi rồi!", notificationTimeMillis);
//        }
//    }
}
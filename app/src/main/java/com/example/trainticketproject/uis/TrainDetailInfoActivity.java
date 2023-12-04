package com.example.trainticketproject.uis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.trainticketproject.R;
import com.example.trainticketproject.daos.TicketDAO;
import com.example.trainticketproject.daos.TrainDAO;
import com.example.trainticketproject.databases.TicketRoomDatabase;
import com.example.trainticketproject.databases.TrainRoomDatabase;
import com.example.trainticketproject.databases.UserRoomDatabase;
import com.example.trainticketproject.models.Status;
import com.example.trainticketproject.models.Ticket;
import com.example.trainticketproject.models.Train;
import com.example.trainticketproject.utils.DateTimeConverter;

import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class TrainDetailInfoActivity extends AppCompatActivity {
    private TrainDAO trainDAO;
    private TicketDAO ticketDAO;

    TextView tvTrip, tvDepartureStation, tvArrivalStation, tvDepartureTime, tvArrivalTime, tvTotalTime, tvPrice, tvDepartureDate, tvSeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_detail_info);

        tvTrip = findViewById(R.id.tvTrip);
        tvDepartureStation = findViewById(R.id.tvDepartureStation);
        tvArrivalStation = findViewById(R.id.tvArrivalStation);
        tvDepartureTime = findViewById(R.id.tvDepartureTime);
        tvArrivalTime = findViewById(R.id.tvArrivalTime);
        tvTotalTime = findViewById(R.id.tvTotalTime);
        tvPrice = findViewById(R.id.tvPrice);
        tvDepartureDate = findViewById(R.id.tvDepartureDate);
        tvSeat = findViewById(R.id.tvSeat);

        TrainRoomDatabase trainDatabase = TrainRoomDatabase.getDatabase(this);
        trainDAO = trainDatabase.trainDAO();

        TicketRoomDatabase ticketRoomDatabase = TicketRoomDatabase.getDatabase(this);
        ticketDAO = ticketRoomDatabase.ticketDAO();

        // Add sample data
//        new Thread(() -> {
//            List<Train> sampleTrains = createSampleTrains();
//            trainDAO.insertMultipleTrains(sampleTrains);
//        }).start();

//        new Thread(() -> {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                ticketDAO.insertTicket(new Ticket(1, 1, "A1", LocalDateTime.now(), Status.AVAILABLE));
//                ticketDAO.insertTicket(new Ticket(2, 1, "A2", LocalDateTime.now(), Status.AVAILABLE));
//                ticketDAO.insertTicket(new Ticket(3, 1, "B1", LocalDateTime.now(), Status.AVAILABLE));
//
//                ticketDAO.insertTicket(new Ticket(4, 2, "C1", LocalDateTime.now(), Status.AVAILABLE));
//                ticketDAO.insertTicket(new Ticket(5, 2, "C2", LocalDateTime.now(), Status.AVAILABLE));
//                ticketDAO.insertTicket(new Ticket(6, 2, "D1", LocalDateTime.now(), Status.AVAILABLE));
//            }
//        }).start();

        // Get id from intent
        int id = 1;
        new Thread(() -> {
            Train selectedTrain = trainDAO.getTrainById(id);
            Log.d("train", selectedTrain.toString());

            runOnUiThread(() -> {
                updateUI(selectedTrain);
            });
        }).start();
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

        int totalSeats = selectedTrain.getTotalSeats();

        new Thread(() -> {
            int availableTickets = ticketDAO.getAvailableTicketCountByTrainId(selectedTrain.getTrainId());
            int availableSeats = totalSeats - availableTickets;

            runOnUiThread(() -> {
                tvSeat.setText(String.valueOf(availableSeats) + "/" + String.valueOf(totalSeats));
            });
        }).start();
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
}
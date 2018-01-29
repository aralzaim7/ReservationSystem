package com.ab2018.ab2018reservationsystem;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {
    Intent intent;
    Button reservation;
    User user;
    Spinner k_harf,k_rakam;
    EditText time,date;
    String resdate="",seat="";
    MovieDBAdapter mdbAdapter;
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        intent = getIntent();
        reservation = findViewById(R.id.reservation);
        k_harf = findViewById(R.id.k_harf);
        k_rakam = findViewById(R.id.k_rakam);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        user = (User) intent.getSerializableExtra("user");
        movie = (Movie) intent.getSerializableExtra("movie");
        mdbAdapter = new MovieDBAdapter(this);


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(ReservationActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        time.setText( selectedHour + ":" + selectedMinute);
                        resdate+=" " + selectedHour + ":" + selectedMinute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });

        seat+=k_harf.getSelectedItem().toString();
        seat+=k_rakam.getSelectedItem().toString();

        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (k_harf.getSelectedItem().toString().trim().equals("") || k_rakam.getSelectedItem().toString().trim().equals("")) {
                    Toast.makeText(ReservationActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                else {
                    mdbAdapter.open();
                    mdbAdapter.insertReservation(resdate, movie.getId(), user.getId(), seat);
                    mdbAdapter.close();

                    Toast.makeText(getApplicationContext(), "Sayın " + user.getName() + " rezervasyonunuz başarılı bir şekilde tamamlanmıştır", Toast.LENGTH_LONG).show();
                }
            }
        });

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(ReservationActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                        /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;
                        date.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                        resdate+="" + selectedday + "/" + selectedmonth + "/" + selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();


            }
        });
    }
}
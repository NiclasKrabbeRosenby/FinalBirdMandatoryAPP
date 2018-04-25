package net.simplifiedlearning.firebaseauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class BirdRegister extends AppCompatActivity {

    private Calendar meetingStart = Calendar.getInstance();
    private Button dateButton;
    private Button timeButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bird_register);
        dateButton = findViewById(R.id.mainDateButton);
        timeButton = findViewById(R.id.mainTimeButton);


        mAuth = FirebaseAuth.getInstance();

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, BirdRegister.class));
        }
    }



    public void datePickButtonClicked(View view) {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                meetingStart.set(Calendar.YEAR, year);
                meetingStart.set(Calendar.MONTH, month);
                meetingStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                DateFormat dateFormat = DateFormat.getDateInstance();
                String dateString = dateFormat.format(meetingStart.getTimeInMillis());
                dateButton.setText(dateString);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDayOfMonth = calendar.get(Calendar.DATE);
        DatePickerDialog dialog = new DatePickerDialog(
                this, dateSetListener, currentYear, currentMonth, currentDayOfMonth);
        dialog.show();
    }

    public void timePickButtonClicked(View view) {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                meetingStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
                meetingStart.set(Calendar.MINUTE, minute);
                DateFormat df = DateFormat.getTimeInstance();
                String timeString = df.format(meetingStart.getTimeInMillis());
                // TODO do not print the seconds from the time (only hours and minutes)
                timeButton.setText(timeString);
            }
        };
        Calendar calendar = Calendar.getInstance();
        int currentHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int currentMinute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(this, timeSetListener, currentHourOfDay,
                currentMinute, true);
        dialog.show();
    }

    //public void goToMainActivity(View view)
    //{
    //    finish();
    //}
    }

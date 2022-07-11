package com.example.meetingscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class schedule extends AppCompatActivity implements View.OnClickListener{
    Calendar calendar;
    TextView dateView;
    int year, month, day;
    TextView time;
    int hour,min;
    DbManager db;
    Button add_meeting,main_menu;
    EditText meetagenda;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        dateView = (TextView) findViewById(R.id.textViewshowdate);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);

        time = (TextView) findViewById(R.id.textViewshowtime);
        calendar = Calendar.getInstance();

        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        db=new DbManager(this);
        add_meeting=(Button)findViewById(R.id.button_addmeeting);
        add_meeting.setOnClickListener(this);
        meetagenda=(EditText)findViewById(R.id.meetingagenda);
        main_menu=(Button)findViewById(R.id.button_mainmenu);
        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getExtras();
                String username = bundle.getString("username");
                Bundle bundle1 = new Bundle();
                bundle1.putString("username", username);
                Intent intent=new Intent(schedule.this,select.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };
    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    public void setTime(View view) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        time.setText(hourOfDay + ":" + minute);
                    }
                }, hour, min, false);
        timePickerDialog.show();
    }

    @Override
    public void onClick(View v)
    {
        String t=time.getText().toString();
        String d=dateView.getText().toString();
        String ma=meetagenda.getText().toString();
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");

        if(d.equals("")||t.equals("")||ma.equals(""))
        {
            Toast.makeText(getBaseContext(),"Please enter all the fields",Toast.LENGTH_LONG).show();
        }
        else
        {
            Boolean check=db.checkmeeting(username,d,t);
            if(check==false)
            {
                Boolean insert = db.insertMeetingData(username, d, t, ma);
                if (insert == true)
                    Toast.makeText(getBaseContext(), "Meeting Agenda added Successfully", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(getBaseContext(), "Failed to add Meeting Agenda", Toast.LENGTH_LONG).show();
            }
            else
                Toast.makeText(getBaseContext(), "Meeting already exists", Toast.LENGTH_LONG).show();
        }

    }
}

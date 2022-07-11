package com.example.meetingscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class Modify extends AppCompatActivity implements View.OnClickListener{
    CalendarView calendar;
    TextView date;
    Button search_date;
    DbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        date= (TextView) findViewById(R.id.textViewdate);
        search_date=(Button)findViewById(R.id.button_search);
        search_date.setOnClickListener(this);
        db=new DbManager(this);
        calendar = (CalendarView)findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                month=month+1;
                String  curDate = String.valueOf(dayOfMonth);
                String  Year = String.valueOf(year);
                String  Month = String.valueOf(month);

                date.setText(curDate+"/"+Month+"/"+Year);
            }
        });
    }

    @Override
    public void onClick(View v)
    {
        String d=date.getText().toString();
        Bundle bundle = getIntent().getExtras();
        String username = bundle.getString("username");
        Bundle bundle1 = new Bundle();
        bundle1.putString("username", username);
        Boolean checkdate=db.checkdate(d,username);
        if(checkdate==true)
        {
            bundle1.putString("date1",d);
            Intent intent=new Intent(this,ModifyMeeting.class);
            intent.putExtra("data",bundle1);
            startActivity(intent);
        }
        else
            Toast.makeText(getBaseContext(),"No Meetings Scheduled for this Date",Toast.LENGTH_LONG).show();
    }
}
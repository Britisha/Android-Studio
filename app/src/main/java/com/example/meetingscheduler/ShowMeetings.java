package com.example.meetingscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowMeetings extends AppCompatActivity
{
    DbManager db;
    TextView dat,time,agenda;
    Button main_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meetings);
        db=new DbManager(this);
        dat = (TextView) findViewById(R.id.textViewdate);
        time = (TextView) findViewById(R.id.textViewtime);
        agenda = (TextView) findViewById(R.id.textViewagenda);
        main_menu=(Button)findViewById(R.id.button_mainmenu);
        main_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getIntent().getBundleExtra("data");
                String username = bundle.getString("username");
                Bundle bundle1 = new Bundle();
                bundle1.putString("username", username);
                Intent intent=new Intent(ShowMeetings.this,select.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        Bundle bundle = getIntent().getBundleExtra("data");
        String username = bundle.getString("username");
        String date=bundle.getString("date1");
        ArrayList<String> rs = db.displaydate(date,username);
        ArrayList<String> t = db.displaytime(date,username);
        ArrayList<String> ag = db.displayagenda(date,username);
        int size = rs.size();
        for(int i=0;i<size;i++)
        {
            dat.setText(dat.getText()+"\n\n"+rs.get(i));
        }
        for(int i=0;i<t.size();i++)
        {
            time.setText(time.getText()+"\n\n"+t.get(i));
        }
        for(int i=0;i<ag.size();i++)
        {
            agenda.setText(agenda.getText()+"\n\n"+ag.get(i));
        }
    }
}
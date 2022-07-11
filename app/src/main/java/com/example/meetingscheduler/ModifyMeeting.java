package com.example.meetingscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifyMeeting extends AppCompatActivity
{
    DbManager db;
    TextView dat,time,agenda,mod;
    EditText modid;
    Button modify;
    int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_meeting);
        db=new DbManager(this);
        dat = (TextView) findViewById(R.id.textViewdate);
        time = (TextView) findViewById(R.id.textViewtime);
        agenda = (TextView) findViewById(R.id.textViewagenda);
        mod = (TextView) findViewById(R.id.txtViewmodify);
        modid=(EditText)findViewById(R.id.modid);
        Bundle bundle = getIntent().getBundleExtra("data");
        String username = bundle.getString("username");
        String date=bundle.getString("date1");
        modify=(Button)findViewById(R.id.btn_modmeeting);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mid = modid.getText().toString();
                if (mid.equals(""))
                    Toast.makeText(getBaseContext(), "Please enter all the fields", Toast.LENGTH_LONG).show();
                else {
                    Boolean check = db.checkid(username, mid, date);
                    if (check == true) {
                        Bundle bundle = getIntent().getBundleExtra("data");
                        String username = bundle.getString("username");
                        Bundle bundle1 = new Bundle();
                        bundle1.putString("id", mid);
                        bundle1.putString("username", username);
                        Intent intent = new Intent(ModifyMeeting.this, ModifySchedule.class);
                        intent.putExtra("data", bundle1);
                        startActivity(intent);
                    } else
                        Toast.makeText(getBaseContext(), "This Meeting Id does not exist", Toast.LENGTH_LONG).show();
                }
            }
        });
        ArrayList<String> rs = db.displaydate(date,username);
        ArrayList<String> t = db.displaytime(date,username);
        ArrayList<String> ag = db.displayagenda(date,username);
        ArrayList<String> mod1=db.displayid(username,date);
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
        for(j=0;j<size;j++)
        {
            mod.setText(mod.getText()+"\n\n"+mod1.get(j));
        }
    }
}

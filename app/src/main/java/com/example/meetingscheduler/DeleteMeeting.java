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

public class DeleteMeeting extends AppCompatActivity
{
    DbManager db;
    TextView dat,time,agenda,del;
    Button show_meeting,del_meeting;
    EditText del_id;
    int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_meeting);
        db=new DbManager(this);
        dat = (TextView) findViewById(R.id.textViewdate);
        time = (TextView) findViewById(R.id.textViewtime);
        agenda = (TextView) findViewById(R.id.textViewagenda);
        del = (TextView) findViewById(R.id.txtViewid);
        del_id=(EditText)findViewById(R.id.delid);
        Bundle bundle = getIntent().getBundleExtra("data");
        String username = bundle.getString("username");
        String date=bundle.getString("date1");
        show_meeting=(Button)findViewById(R.id.button_showmeeting);
        show_meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putString("username", username);
                Intent intent=new Intent(DeleteMeeting.this,info.class);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        del_meeting=(Button)findViewById(R.id.btn_delmeeting);
        del_meeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String del1 = del_id.getText().toString();
                if (del1.equals(""))
                    Toast.makeText(getBaseContext(),"Please enter all the fields",Toast.LENGTH_LONG).show();
                else
                {
                    Boolean check = db.checkid(username, del1, date);
                    if (check == true) {
                        Bundle bundle = getIntent().getBundleExtra("data");
                        String username = bundle.getString("username");
                        Boolean res = db.deletemeeting(username, del1);
                        if (res == true)
                            Toast.makeText(getBaseContext(), "Meeting Deleted Successfully", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getBaseContext(), "Could not Delete Meeting", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getBaseContext(), "This Meeting Id does not exist", Toast.LENGTH_LONG).show();
                }
            }
        });
        ArrayList<String> rs = db.displaydate(date,username);
        ArrayList<String> t = db.displaytime(date,username);
        ArrayList<String> ag = db.displayagenda(date,username);
        ArrayList<String> id= db.displayid(username,date);
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
        for( j=0;j<size;j++)
        {
            del.setText(del.getText()+"\n\n"+id.get(j));
        }
    }
}
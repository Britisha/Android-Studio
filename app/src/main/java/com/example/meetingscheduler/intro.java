package com.example.meetingscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class intro extends AppCompatActivity implements View.OnClickListener {
    Button btn_signup,btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        btn_signup=(Button)findViewById(R.id.button2);
        btn_signup.setOnClickListener(this);
        btn_login=(Button)findViewById(R.id.button3);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btn_signup))
        {
            Bundle bundle=new Bundle();
            Intent intent=new Intent(this,signup.class);
            intent.putExtra("data",bundle);
            startActivity(intent);
        }
        if(v.equals(btn_login))
        {
            Bundle bundle=new Bundle();
            Intent intent=new Intent(this, login.class);
            intent.putExtra("data",bundle);
            startActivity(intent);
        }
    }
    }



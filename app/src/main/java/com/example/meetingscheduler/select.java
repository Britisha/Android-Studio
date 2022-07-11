package com.example.meetingscheduler;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class select extends AppCompatActivity implements View.OnClickListener {
    Button btn_schedule,btn_info,btn_modify,btn_delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        btn_schedule=(Button)findViewById(R.id.button_schedule);
        btn_schedule.setOnClickListener(this);
        btn_info=(Button)findViewById(R.id.button_show);
        btn_info.setOnClickListener(this);
        btn_modify=(Button)findViewById(R.id.button_modify);
        btn_modify.setOnClickListener(this);
        btn_delete=(Button)findViewById(R.id.button_delete);
        btn_delete.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.equals(btn_schedule))
        {
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("username");
            Bundle bundle1 = new Bundle();
            bundle1.putString("username", username);
            Intent intent=new Intent(this,schedule.class);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
        if(v.equals(btn_info))
        {
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("username");
            Bundle bundle1 = new Bundle();
            bundle1.putString("username", username);
            Intent intent=new Intent(this,info.class);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
        if(v.equals(btn_modify))
        {
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("username");
            Bundle bundle1 = new Bundle();
            bundle1.putString("username", username);
            Intent intent=new Intent(this,Modify.class);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
        if(v.equals(btn_delete))
        {
            Bundle bundle = getIntent().getExtras();
            String username = bundle.getString("username");
            Bundle bundle1 = new Bundle();
            bundle1.putString("username", username);
            Intent intent=new Intent(this,Delete.class);
            intent.putExtras(bundle1);
            startActivity(intent);
        }
    } }
package com.example.meetingscheduler;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class login extends AppCompatActivity implements View.OnClickListener {
    EditText txtloginusername,txtloginpassword;
    Button btnlogin;
    DbManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtloginusername=(EditText)findViewById(R.id.login_username);
        txtloginpassword=(EditText)findViewById(R.id.login_password);
        btnlogin=(Button)findViewById(R.id.button_login);
        btnlogin.setOnClickListener(this);
        db=new DbManager(this);
    }
    @Override
    public void onClick(View v)
    {
        String user1=txtloginusername.getText().toString();
        String pass1=txtloginpassword.getText().toString();

        if(user1.equals("")||pass1.equals(""))
        {
            Toast.makeText(getBaseContext(),"Please enter all the fields",Toast.LENGTH_LONG).show();
        }
        else
        {
            Boolean checkuser=db.checkusernamepassword(user1,pass1);
            if(checkuser==true)
            {
                Toast.makeText(getBaseContext(),"Login Successful",Toast.LENGTH_LONG).show();
                Bundle bundle = new Bundle();
                bundle.putString("username", user1);
                Intent intent=new Intent(this,select.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
                Toast.makeText(getBaseContext(),"Login Failed",Toast.LENGTH_LONG).show();
        }
    } }
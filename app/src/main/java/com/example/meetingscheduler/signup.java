package com.example.meetingscheduler;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signup extends AppCompatActivity implements View.OnClickListener
{
    EditText txt_username,txt_password,txt_repassword;
    Button btn_signup;
    DbManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        txt_username=(EditText)findViewById(R.id.signup_username);
        txt_password=(EditText)findViewById(R.id.signup_password);
        txt_repassword=(EditText)findViewById(R.id.signup_password2);
        btn_signup=(Button)findViewById(R.id.button_signup);
        btn_signup.setOnClickListener(this);
        db=new DbManager(this);
    }
    @Override
    public void onClick(View v)
    {
        String username=txt_username.getText().toString();
        String password=txt_password.getText().toString();
        String repass=txt_repassword.getText().toString();

        if(username.equals("")||password.equals("")||repass.equals(""))
            Toast.makeText(getBaseContext(),"Please enter all the fields",Toast.LENGTH_LONG).show();
        else{
            if(password.length()>=5) {
                if (password.equals(repass)) {
                    Boolean checkuser = db.checkusername(username);
                    if (checkuser == false) {
                        Boolean insert = db.insertData(username, password);
                        if (insert == true) {
                            Toast.makeText(getBaseContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(this, login.class);
                            startActivity(intent);
                        } else
                            Toast.makeText(getBaseContext(), "Registered Failed", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getBaseContext(), "User already exists", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(getBaseContext(), "Passwords are not matching", Toast.LENGTH_LONG).show();
            } else
                Toast.makeText(getBaseContext(), "Password length should be atleast 5", Toast.LENGTH_LONG).show();
        }
    }
}
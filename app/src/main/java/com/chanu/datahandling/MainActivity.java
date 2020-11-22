package com.chanu.datahandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chanu.datahandling.Database.DBHelper;
import com.chanu.datahandling.Database.UsersMaster;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText eTxtUsername, eTxtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eTxtUsername=findViewById(R.id.editTextUsername);
        eTxtPassword=findViewById(R.id.editTextPassword);
    }

    public void addData(View view){
        DBHelper dbHelper=new DBHelper(this);
        long value =dbHelper.addInfo(eTxtUsername.getText().toString(),eTxtPassword.getText().toString());
        
        if (value > 0){
            Toast.makeText(getApplicationContext(), "data successfully inserted", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(), "data not inserted", Toast.LENGTH_SHORT).show();

    }
    public void viewAll(View view){
        DBHelper dbHelper=new DBHelper(this);

        List userNames = dbHelper.readAllInfo("username");

        Toast.makeText(getApplicationContext(), userNames.toString(), Toast.LENGTH_SHORT).show();

    }
}
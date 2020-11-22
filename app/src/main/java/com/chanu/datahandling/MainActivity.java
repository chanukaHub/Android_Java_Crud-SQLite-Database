package com.chanu.datahandling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
        if (TextUtils.isEmpty(eTxtUsername.getText().toString()) || TextUtils.isEmpty(eTxtPassword.getText().toString())  )
            Toast.makeText(getApplicationContext(), "Invalid Data Filling", Toast.LENGTH_SHORT).show();
        else {
            long value = dbHelper.addInfo(eTxtUsername.getText().toString(), eTxtPassword.getText().toString());

            if (value > 0) {
                Toast.makeText(getApplicationContext(), "data successfully inserted", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getApplicationContext(), "data not inserted", Toast.LENGTH_SHORT).show();
        }

    }
    
    public void viewAll(View view){
        DBHelper dbHelper=new DBHelper(this);

        List userNames = dbHelper.readAllInfo("username");

        Toast.makeText(getApplicationContext(), userNames.toString(), Toast.LENGTH_SHORT).show();

    }
    
    public void deleteData(View view){
        DBHelper dbHelper=new DBHelper(this);
        
        int value=dbHelper.deleteInfo(eTxtUsername.getText().toString());

        if (value > 0)
            Toast.makeText(getApplicationContext(), eTxtUsername.getText().toString()+" deleted Successfully", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Delete failed", Toast.LENGTH_SHORT).show();
    }
    
    public void updateData(View view){
        DBHelper dbHelper=new DBHelper(this);
        int value=dbHelper.updateInfo(eTxtUsername.getText().toString(),eTxtPassword.getText().toString());
        
        if (value > 0){
            Toast.makeText(getApplicationContext(), "Data updated Successfully", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getApplicationContext(), "Data not updated", Toast.LENGTH_SHORT).show();
    }

    public void signIn(View view){
        DBHelper dbHelper=new DBHelper(this);
        List userNames =dbHelper.readAllInfo("username");
        List passwords =dbHelper.readAllInfo("password");

        String username = eTxtUsername.getText().toString();
        String password =eTxtPassword.getText().toString();

        if (userNames.indexOf(username) >= 0){
            if (passwords.get(userNames.indexOf(username)).equals(password))
                Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
        }

    }


}